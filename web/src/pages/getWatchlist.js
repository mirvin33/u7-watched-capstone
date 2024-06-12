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
        this.bindClassMethods(['clientLoaded', 'mount', 'submit'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        console.log("getWatchlist constructor");
    }

    /**
     * Once the client is loaded, get the watchlist metadata and title list.
     */
    async clientLoaded() {
        // const urlParams = new URLSearchParams(window.location.search);
        // const watchlistId = urlParams.get('id');
        // document.getElementById('watchlist-name').innerText = "Loading Watchlist ...";
    
        // try {
        //     const watchlist = await this.client.getWatchlist(watchlistId);
        //     this.dataStore.set('watchlist', watchlist);
        //     document.getElementById('titles').innerText = "(loading titles...)";
        //     const titles = await this.client.getWatchlistTitles(watchlistId);
        //     this.dataStore.set('titles', titles);
        //     this.updateWatchlistDisplay(watchlist);
        // } catch (error) {
        //     console.error("Error loading watchlist:", error);
        // }
    }

    /**
     * Add the header to the page and load the WatchedClient.
     */
    mount() {
        document.getElementById('get-watchlist').addEventListener('click', this.submit);

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
     updateWatchlistDisplay(watchlist) {
        const watchlistDisplay = document.getElementById('watchlist-display');
        const watchlistName = document.getElementById('watchlist-name');
        const watchlistOwner = document.getElementById('watchlist-owner');
        const tags = document.getElementById('tags');
        const contentList = document.getElementById('content-list');

        watchlistName.innerText = watchlist.title;
        watchlistOwner.innerText = watchlist.userId;
        tags.innerHTML = '';

        if (watchlist.tags) {
            watchlist.tags.forEach(tag => {
                const tagElement = document.createElement('span');
                tagElement.className = 'tag';
                tagElement.innerText = tag;
                tags.appendChild(tagElement);
            });
        }

        contentList.innerHTML = '';
        if (watchlist.content) {
            watchlist.content.forEach(content => {
                const listItem = document.createElement('li');
                listItem.innerText = content.title;
                contentList.appendChild(listItem);
            });
        }

        watchlistDisplay.style.display = 'block';
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
