import WatchedClient from '../api/watchedClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";


class Homepage extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount'], this);
        this.header = new Header(this.dataStore);
        console.log("Homepage constructor");
    }

mount() {
        this.header.addHeaderToPage();
        this.client = new WatchedClient();
    }
}

const main = async () => {
    const homePage = new Homepage();
    homePage.mount();
};

window.addEventListener('DOMContentLoaded', main);