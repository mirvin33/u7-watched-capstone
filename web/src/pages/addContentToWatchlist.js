import WatchedClient from '../api/watchedClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed to add content to a watchlist
 * 
 */
class AddContentToWatchlist extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'submit',], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.watchlistsDisplay);
        this.header = new Header(this.dataStore);
        console.log("getWatchlist constructor");
    }

async clientLoaded() {

}

mount() {
    document.getElementById('add-content').addEventListener('click', this.submit);

    this.header.addHeaderToPage();

    this.client = new WatchedClient();
    this.clientLoaded();
    console.log("addContentToWatchlist constructor");
}
}