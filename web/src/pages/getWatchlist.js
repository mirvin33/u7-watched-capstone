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
        this.bindClassMethods(['clientLoaded', 'mount', 'submit', 'updateWatchlistDisplay',
            'watchlistsDisplay', 'redirectToUpdateWatchlist', 'updateWatchlistName'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.watchlistsDisplay);
        this.header = new Header(this.dataStore);
        console.log("getWatchlist constructor");
    }

    /**
     * Once the client is loaded, get the watchlist metadata and title list.
     */
    async clientLoaded() {

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        try {
            const watchlists = await this.client.getWatchlistsForUser();
            if (watchlists) {
                console.log("watchlists = ", watchlists);
                this.dataStore.set('watchlists', watchlists);
            }
        } catch (error) {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        }
    }

    /**
     * Add the header to the page and load the WatchedClient.
     */
    mount() {
        document.getElementById('get-watchlist').addEventListener('click', this.submit);
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
    async watchlistsDisplay() {
        const watchlists = this.dataStore.get('watchlists');
        let rows = ""
        for (const watchlist of watchlists) {
            rows += "<li>" + watchlist.title + "<li/>"
        }
        const watchlistsDisplay = document.getElementById('watchlists-display');

        watchlistsDisplay.style.display = 'block';
        watchlistsDisplay.innerHTML = rows;
        console.log("display")
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
