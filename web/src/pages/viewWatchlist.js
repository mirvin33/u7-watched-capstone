//import WatchedClient from '../api/watchedClient';
//import Header from '../components/header';
//import BindingClass from "../util/bindingClass";
//import DataStore from "../util/DataStore";
//
///**
// * Logic needed for the view watchlist page of the website.
// */
//class ViewWatchlist extends BindingClass {
//    constructor() {
//        super();
//        this.bindClassMethods(['clientLoaded', 'mount', 'addWatchlistToPage', 'addTitlesToPage', 'addTitle'], this);
//        this.dataStore = new DataStore();
//        this.dataStore.addChangeListener(this.addWatchlistToPage);
//        this.dataStore.addChangeListener(this.addTitlesToPage);
//        this.header = new Header(this.dataStore);
//        console.log("viewWatchlist constructor");
//    }
//
//    /**
//     * Once the client is loaded, get the watchlist metadata and title list.
//     */
//    async clientLoaded() {
//        // const urlParams = new URLSearchParams(window.location.search);
//        // const watchlistId = urlParams.get('id');
//        // document.getElementById('watchlist-name').innerText = "Loading Watchlist ...";
//        // const watchlist = await this.client.getWatchlist(watchlistId);
//        // this.dataStore.set('watchlist', watchlist);
//        // document.getElementById('titles').innerText = "(loading titles...)";
//        // const titles = await this.client.getWatchlistTitles(watchlistId);
//        // this.dataStore.set('titles', titles);
//    }
//
//    /**
//     * Add the header to the page and load the WatchedClient.
//     */
//    mount() {
//        document.getElementById('add-title').addEventListener('click', this.addTitle);
//
//        this.header.addHeaderToPage();
//
//        this.client = new WatchedClient();
//        this.clientLoaded();
//    }
//
//    /**
//     * When the watchlist is updated in the datastore, update the watchlist metadata on the page.
//     */
//    addWatchlistToPage() {
//        const watchlist = this.dataStore.get('watchlist');
//        if (watchlist == null) {
//            return;
//        }
//
//        document.getElementById('watchlist-title').innerText = watchlist.title;
//        document.getElementById('watchlist-userId').innerText = watchlist.userId;
//
//        let tagHtml = '';
//        let tag;
//        for (tag of watchlist.tags) {
//            tagHtml += '<div class="tag">' + tag + '</div>';
//        }
//        document.getElementById('tags').innerHTML = tagHtml;
//    }
//
//    /**
//     * When the titles are updated in the datastore, update the list of titles on the page.
//     */
//    addTitlesToPage() {
//        const titles = this.dataStore.get('titles');
//
//        if (titles == null) {
//            return;
//        }
//
//        let titleHtml = '';
//        let title;
//        for (title of titles) {
//            titleHtml += `
//                <li class="title">
//                    <span class="name">${title.name}</span>
//                    <span class="genre">${title.genre}</span>
//                </li>
//            `;
//        }
//        document.getElementById('titles').innerHTML = titleHtml;
//    }
//
//    /**
//     * Method to run when the add title to watchlist submit button is pressed. Call the WatchedClient to add a title to the
//     * watchlist.
//     */
//    async addTitle() {
//
//        const errorMessageDisplay = document.getElementById('error-message');
//        errorMessageDisplay.innerText = ``;
//        errorMessageDisplay.classList.add('hidden');
//
//        const watchlist = this.dataStore.get('watchlist');
//        if (watchlist == null) {
//            return;
//        }
//
//        document.getElementById('add-title').innerText = 'Adding...';
//        const titleId = document.getElementById('title-id').value;
//        const watchlistId = watchlist.id;
//
//        const titleList = await this.client.addTitleToWatchlist(watchlistId, titleId, (error) => {
//            errorMessageDisplay.innerText = `Error: ${error.message}`;
//            errorMessageDisplay.classList.remove('hidden');
//        });
//
//        this.dataStore.set('titles', titleList);
//
//        document.getElementById('add-title').innerText = 'Add Title';
//        document.getElementById("add-title-form").reset();
//    }
//}
//
///**
// * Main method to run when the page contents have loaded.
// */
//const main = async () => {
//    const viewWatchlist = new ViewWatchlist();
//    viewWatchlist.mount();
//};
//
//window.addEventListener('DOMContentLoaded', main);
