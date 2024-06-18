import WatchedClient from '../api/watchedClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';
import Authenticator from '../api/authenticator';

/**
 * Logic needed for the update watchlist page of the website.
 */
class UpdateWatchlist extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'submit', 'updateWatchlist', 'updateWatchlistDisplay', 'getContentTitle'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this.authenticator = new Authenticator();
        console.log("updateWatchlist constructor");
    }

    /**
     * Once the client is loaded, get the watchlist metadata.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const watchlistId = await this.client.getWatchlist(watchlistId);
    }

    /**
     * Add the header to the page and load the WatchedClient.
     */
    mount() {
        document.getElementById('get-watchlist').addEventListener('click', this.submit);
        // document.getElementById('update-watchlist').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new WatchedClient();
        this.clientLoaded();
    } 
    
    async submit(evt) {
        evt.preventDefault();
    
        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');
        
        const createButton = document.getElementById('get-watchlist');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Loading...';
    
        const watchlistId = document.getElementById('watchlist-id').value;
        console.log("watchlistId = ", watchlistId);
    
        try {
            const watchlist = await this.client.getWatchlist(watchlistId);
            if (watchlist) {
                this.dataStore.set('watchlist', watchlist);
                this.updateWatchlistDisplay(watchlist);
            }
        } catch (error) {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        } finally {
            createButton.innerText = origButtonText;
        }
    }

    /**
     * Update the watchlist display with the fetched watchlist data.
     * @param watchlist The watchlist data to display.
     */
    async updateWatchlistDisplay(watchlist) {
        const watchlistDisplay = document.getElementById('watchlist-display');
        const watchlistName = document.getElementById('watchlist-name');
        const watchlistOwner = document.getElementById('watchlist-owner');
        const contentListOG = document.getElementById('watchlist-content-size');
        const contentList = document.getElementById('content-list');

        watchlistName.innerText = watchlist.title;
        watchlistOwner.innerText = watchlist.userId;
        contentListOG.innerText = watchlist.contentSet;

        contentList.innerHTML = '';
        const contentIds = watchlist.contentSet;
        if (contentIds.size > 0) {
            contentIds.forEach(async contentId => {
                const listItem = document.createElement('li');
                listItem.innerText = await this.client.getWatchlistContent(contentId);
            });
        } else {
            contentList.innerHTML = '<li>No content found in this watchlist.</li>';
        }

        watchlistDisplay.style.display = 'block';
    }


    /**
     * Method to run when the update watchlist submit button is pressed.
     */
    async updateWatchlist(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const updateButton = document.getElementById('update-watchlist');
        const origButtonText = updateButton.innerText;
        updateButton.innerText = 'Updating...';

        const watchlistId = document.getElementById('watchlist-id').value;
        const newTitle = document.getElementById('watchlist-title').value;

        try {
            const token = await this.client.getTokenOrThrow("Only authenticated users can update watchlists.");
            await this.client.axiosClient.put(`watchlist/${id}/update`, {
                title: newTitle
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            
            const updatedWatchlist = await this.client.getWatchlist(watchlistId, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');

        });
            this.dataStore.set('watchlist', updatedWatchlist);
            this.updateWatchlistDisplay(updatedWatchlist);
            updateButton.innerText = 'Update';
            errorMessageDisplay.innerText = 'Watchlist updated successfully';
            errorMessageDisplay.classList.remove('hidden');
        } catch (error) {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        } finally {
            updateButton.innerText = origButtonText;
        }
    }

/**
 * Function to fetch content title based on content ID.
 * Replace this with your actual implementation to fetch content title.
 * @param {string} contentId The ID of the content.
 * @returns {string} The title of the content.
 */
    async getContentTitle(contentId) {
    const params = {
        TableName: 'content',
        Key: {
            'contentId': contentId
        }
    };

    try {
        const data = await dynamoDB.get(params).promise();
    
        if (!data.Item) {
            throw new Error(`Content with ID ${contentId} not found`);
        }
        
        return data.Item.title;
        } catch (error) {
            console.error('Error fetching content title:', error);
            throw error;

        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const updateWatchlist = new UpdateWatchlist();
    updateWatchlist.mount();
};

window.addEventListener('DOMContentLoaded', main);

