import WatchedClient from '../api/watchedClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view watchlist page of the website.
 */
class GetWatchlist extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'submit','submit2', 'updateWatchlistDisplay',
            'updateWatchlistsDisplay', 'redirectToUpdateWatchlist', 'updateWatchlistName'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        console.log("getWatchlist constructor");
    }

    /**
     * Once the client is loaded, get the watchlist metadata and title list.
     */
    async clientLoaded() {

    }

    /**
     * Add the header to the page and load the WatchedClient.
     */
    mount() {
        document.getElementById('get-watchlist').addEventListener('click', this.submit);
        document.getElementById('get-watchlists-for-user').addEventListener('click', this.submit2);
        document.getElementById('update-watchlist').addEventListener('click', this.updateWatchlistName);
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
    
            const watchlist = await this.client.getWatchlist(watchlistId, (error) => {
            
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
            if (watchlist) {
                createButton.innerText = 'Get Watchlist';
                console.log("watchlist = ", watchlist);
                this.dataStore.set('watchlist', watchlist);
                this.updateWatchlistDisplay(watchlist);
            }
        }

         async submit2(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('get-watchlists-for-user');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Getting Watchlists...';

        const userId = document.getElementById('user-id').value;
        console.log("userId = ", userId);

        try {
            const watchlists = await this.client.getWatchlistsForUser(userId);
            createButton.innerText = origButtonText;
            if (watchlists) {
                console.log("watchlists = ", watchlists);
                this.dataStore.set('watchlists', watchlists);
                this.updateWatchlistsDisplay(watchlists);
            }
        } catch (error) {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
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
        const watchlistID = document.getElementById('watchlist-id');
        const watchlistIdDisplay = document.getElementById('watchlist-id-display');
        const contentList = document.getElementById('content-list');

        watchlistName.innerText = watchlist.title;
        watchlistOwner.innerText = watchlist.userId;
        contentListOG.innerText = watchlist.contentSet;
        watchlistIdDisplay.innerText = watchlist.id;

        contentList.innerHTML = '';
        const contentIds = watchlist.contentSet;
        if (contentIds.size > 0) {
            contentIds.forEach(async contentId => {
                const listItem = document.createElement('li');
                listItem.innerText = await this.client.getWatchlistContent(contentId);
            });
        } 
        watchlistDisplay.style.display = 'block';
    }

    /**
     * Update the watchlists display with the fetched watchlist data.
     * @param watchlist The watchlists data to display.
     */
    async updateWatchlistsDisplay(watchlists) {
        const watchlistsDisplay = document.getElementById('watchlists-display');
        const watchlistsNames = document.getElementById('watchlists-names');
        const watchlistsOwner = document.getElementById('watchlists-owner');
        const userID = document.getElementById('user-id');
        const watchlistsIdDisplay = document.getElementById('watchlists-id-display');
 
        watchlistsNames.innerText = watchlists.title;
        watchlistsOwner.innerText = watchlists.userId;
        watchlistsIdDisplay.innerText = watchlists.id;

        const userId = watchlists.userId;
        const listsItem = document.createElement('li');
        listsItem.innerText = await this.client.getWatchlistsForUser(userId);

        watchlistsDisplay.style.display = 'block';
    }

   /**
     * When the playlist is updated in the datastore, redirect to the view playlist page.
     */
    redirectToUpdateWatchlist(watchlist) {
    if (watchlist != null) {
        window.location.href = `/updateWatchlist.html?id=${watchlist.id}`;
    }
   }

   async updateWatchlistName(evt) {
    evt.preventDefault();

    const errorMessageDisplay = document.getElementById('error-message');
    errorMessageDisplay.innerText = ``;
    errorMessageDisplay.classList.add('hidden');

    const watchlist = this.dataStore.get('watchlist');
    const watchlistName = document.getElementById('watchlist-newName').value;

    const watchlistId = watchlist.id;
    const response = await this.client.updateWatchlistName(watchlistId, watchlistName);

    return ('Watchlist Name Updated Successfully')
   }
}


/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const getWatchlist = new GetWatchlist();
    getWatchlist.mount();
};

window.addEventListener('DOMContentLoaded', main);
