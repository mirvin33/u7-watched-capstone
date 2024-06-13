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
        this.bindClassMethods(['clientLoaded', 'mount', 'submit', 'updateWatchlistDisplay'], this);
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
    
            const watchlist = await this.client.getWatchlist(watchlistId);
            if (watchlist) {
                console.log("watchlist = ", watchlist);
                this.dataStore.set('watchlist', watchlist);
                this.updateWatchlistDisplay(watchlist);
                
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
}



/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const getWatchlist = new GetWatchlist();
    getWatchlist.mount();
};

window.addEventListener('DOMContentLoaded', main);
