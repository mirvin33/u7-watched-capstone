import WatchedClient from '../api/watchedClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create watchlist page of the website.
 */
class CreateWatchlist extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToWatchlist'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToWatchlist);
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the WatchedClient.
     */
    mount() {
        document.getElementById('create').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new WatchedClient();
    }

    /**
     * Method to run when the create watchlist submit button is pressed. Call the WatchedService to create the
     * watchlist.
     */
    async submit(evt) {
        evt.preventDefault();
    
        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');
    
        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Creating...';
    
        const title = document.getElementById('watchlist-title').value;
        const userId = document.getElementById('user-Id').value;
        console.log("userId = ", userId);
        console.log("watchlistName = ", watchlistName);
    
        try {
            // Assuming you have a method in your WatchedClient to save the watchlist to DynamoDB
            const watchlist = await this.client.saveWatchlistToDynamoDB(title, userId);
            console.log('Watchlist saved:', watchlist);
            // Optionally, you can redirect the user to another page after successful creation
            // window.location.href = 'viewWatchlist.html';
        } catch (error) {
            console.error('Error saving watchlist:', error);
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        }
    }


    /**
     * When the watchlist is updated in the datastore, redirect to the view watchlist page.
     */
    redirectToWatchlist() {
        const watchlist = this.dataStore.get('watchlist');
        if (watchlist != null) {
            window.location.href = `/watchlist/${id}.html?id=${watchlist.id}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createWatchlist = new CreateWatchlist();
    createWatchlist.mount();
};

document.addEventListener('DOMContentLoaded', main);