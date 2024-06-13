import WatchedClient from '../api/watchedClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class DeleteWatchlist extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'clientLoaded'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        console.log("deleteWatchlist constructor");
    }

    /**
     * Once the client is loaded, get the watchlist metadata and title list.
     */
    async clientLoaded() {
        console.log("Client loaded");
    }

    mount() {
        document.getElementById('delete-watchlist').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new WatchedClient();
        this.clientLoaded();
    }

    async submit(evt) {
        evt.preventDefault();
        
        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const deleteButton = evt.target;
        const origButtonText = deleteButton.innerText;
        deleteButton.innerText = 'Deleting...';
        deleteButton.disabled = true;

        const watchlistId = document.getElementById('watchlist-id').value;
        console.log("watchlistId = ", watchlistId);
        const watchlist = await this.client.getWatchlist(watchlistId);
            if (watchlist) {
                console.log("watchlist = ", watchlist);
                this.dataStore.set('watchlist', watchlist);
                console.log("Get Watchlist")
                const response = await this.client.deleteWatchlist(watchlist.id);
                console.log("Deleted WatchlistId")
        }
    }
}

const main = async () => {
    const deleteWatchlist = new DeleteWatchlist();
    deleteWatchlist.mount();
};

window.addEventListener('DOMContentLoaded', main);
