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

        const watchlistTitle = document.getElementById('watchlist-title').value;


        const watchlist = await this.client.createWatchlist(watchlistTitle, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('watchlist', watchlist);
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

// document.addEventListener('DOMContentLoaded', () => {
//     const form = document.getElementById('watchlist-title');
//     if (form) {
//         form.addEventListener('submit', (event) => {
//             event.preventDefault();
//             const input = document.getElementById('watchlist-title');
//             if (input) {
//                 const watchlistTitle = input.value;
//                 // Submit the form with the watchlist name
//                 console.log(`Watchlist created: ${watchlistName}`);
//             } else {
//                 console.error('Input element with id "watchlistName" not found');
//             }
//         });
//     } else {
//         console.error('Form element with id "watchlistForm" not found');
//     }
// });

window.addEventListener('DOMContentLoaded', main);