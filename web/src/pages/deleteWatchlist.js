import WatchedClient from '../api/watchedClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class DeleteWatchlist extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'clientLoaded', 'deleteWatchlistsDisplay'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.deleteWatchlistsDisplay);
        document.addEventListener('DOMContentLoaded', () => {
            deleteWatchlistsDisplay();
        });
        this.header = new Header(this.dataStore);
        console.log("deleteWatchlist constructor");
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
                console.log("Client loaded");
            }
        } catch (error) {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        }
        
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
        const watchlist = await this.client.getWatchlist(watchlistId, (error) => {
        errorMessageDisplay.innerText = `Error: ${error.message}`;
        errorMessageDisplay.classList.remove('hidden');
    });

            if (watchlist) { 
                console.log("watchlist = ", watchlist);
                this.dataStore.set('watchlist', watchlist);
                console.log("Get Watchlist");
                const response = await this.client.deleteWatchlist(watchlist.id);
                console.log("Deleted WatchlistId");
                alert("Watchlist Deleted");
                deleteButton.innerText = 'Delete';
        }
    }

     /**
     * Update the watchlists display with the fetched watchlist data.
     * @param watchlist The watchlists data to display.
     */
     deleteWatchlistsDisplay() {
        console.log("display1")
        const watchlists = this.dataStore.get('watchlists');
        
        let rows = ""
        for (const watchlist of watchlists) {
            rows += "<li>" + watchlist.title + "<li/>"
            rows += "<li>" + watchlist.id + "<li/>"
        }
        console.log("display2")
        const watchlistsDisplay = document.getElementById('watchlists-display');
         console.log("display3")

        watchlistsDisplay.style.display = 'block';
        watchlistsDisplay.innerHTML = rows;
        console.log("displaycomplete")
    }

}

const main = async () => {
    const deleteWatchlist = new DeleteWatchlist();
    deleteWatchlist.mount();
};

window.addEventListener('DOMContentLoaded', main);
