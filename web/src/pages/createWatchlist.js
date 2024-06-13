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
        this.bindClassMethods(['mount', 'submit'], this);
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

        const watchlistTitle = document.getElementById('watchlist-title').value;
        console.log("Create Watchlist")
        createButton.innerText = 'Create Watchlist';

        const watchlist = await this.client.createWatchlist(watchlistTitle, (error) => {    
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('watchlist', watchlist);
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createWatchlist = new CreateWatchlist();
    createWatchlist.mount();
};

window.addEventListener('DOMContentLoaded', main);

