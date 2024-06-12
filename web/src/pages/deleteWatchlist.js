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

        try {
            const watchlist = await this.client.getWatchlist(watchlistId);
            
            if (watchlist) {
                this.dataStore.delete(watchlistId);
                errorMessageDisplay.innerText = `Watchlist not deleted successfully.`;
                errorMessageDisplay.classList.remove('hidden');
            } else {
                throw new Error("Watchlist not found");
            }
        } catch (error) {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        } finally {
            deleteButton.innerText = origButtonText;
            deleteButton.disabled = false;
        }
    }

    /**
     * Delete a watchlist.
     * @param id The id of the watchlist to delete.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    async deleteWatchlist(id, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can delete a watchlist.");
            await this.axiosClient.delete(`watchlists/${id}/delete`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }
    
    /**
     * Get the authentication token or throw an error if not authenticated.
     */
    async getTokenOrThrow(errorMessage) {
        const token = await this.dataStore.get('authToken');
        if (!token) {
            throw new Error(errorMessage);
        }
        return token;
    }

handleError(error, errorCallback) {
    console.error(error);

    const errorFromApi = error?.response?.data?.error_message;
    if (errorFromApi) {
        console.error(errorFromApi)
        error.message = errorFromApi;
    }

    if (errorCallback) {
        errorCallback(error);
    }
}
}

const main = async () => {
    const deleteWatchlist = new DeleteWatchlist();
    deleteWatchlist.mount();
};

window.addEventListener('DOMContentLoaded', main);
