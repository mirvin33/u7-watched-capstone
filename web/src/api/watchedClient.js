import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the WatchedService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class WatchedClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'getWatchlist', 'getWatchlistContent',
        'createWatchlist', 'addContentToWatchlist', 'deleteWatchlist',
        'searchWatchlists', 'updateWatchlist', 'getWatchlistsForUser'];
        this.bindClassMethods(methodsToBind, this);
        this.authenticator = new Authenticator();
        this.props = props;
        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }
            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    /**
     * Gets the watchlist for the given ID.
     * @param id Unique identifier for a watchlist
     * @param errorCallback A function to execute if the call fails.
     * @returns The watchlist's metadata.
     */
    async getWatchlist(id, errorCallback) {
        try {
            console.log("getWatchlist from Client");
            const token = await this.getTokenOrThrow("Only authenticated users can view watchlists.");
            const response = await this.axiosClient.get(`watchlist/${id}`
                , {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
            });
            return response.data.watchlist;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Gets the watchlist for the given ID.
     * @param userId Unique identifier for all watchlists
     * @param errorCallback A function to execute if the call fails.
     * @returns The watchlist's metadata.
     */
    async getWatchlistsForUser(errorCallback) {
        try {
            console.log("getWatchlists from Client");
            const token = await this.getTokenOrThrow("Only authenticated users can view watchlists.");
            const response = await this.axiosClient.get(`watchlists`
                , {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
            });
            console.log("returned watchlists");
            return response.data.watchlists;          
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Get the content of a given watchlist by the watchlist's identifier.
     * @param   id Unique identifier for a watchlist
     * @param errorCallback A function to execute if the call fails.
     * @returns The list of content in a watchlist.
     */
    async getWatchlistContent(id, errorCallback) {
        try {
            const response = await this.axiosClient.get(`watchlists/${id}/content`);
            return response.data.contentList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }
    

    /**
     * Create a new watchlist owned by the current user.
     * @param title The name of the watchlist to create.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The watchlist that has been created.
     */
    async createWatchlist(title, errorCallback) {
        try {
            console.log("Create Watchlist Client Start")
            const token = await this.getTokenOrThrow("Only authenticated users can create watchlists.");
            const response = await this.axiosClient.post(`watchlist`, {
                title: title,
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.watchlist;
        } catch (error) {
            this.handleError(error, errorCallback);
        }
    }

    /**
     * Add content to a watchlist.
     * @param id The id of the watchlist to add content to.
     * @param contentId The content id to add.
     * @param queueNext Whether to queue the content next.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The updated watchlist.
     */
    async addContentToWatchlist(contentId, title, streamingService, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can add content to a watchlist.");
            const response = await this.axiosClient.post(`watchlists/${id}/add`, {
                title: title,
                streamingService: streamingService,
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.watchlist;
        } catch (error) {
            this.handleError(error, errorCallback)
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
            await this.axiosClient.delete(`watchlist/${id}/delete`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.deleteResult;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Search for watchlists.
     * @param criteria A string containing search criteria to pass to the API.
     * @param errorCallback A function to execute if the call fails.
     * @returns The watchlists that match the search criteria.
     */
    async searchWatchlists(criteria, errorCallback) {
        try {
            const queryParams = new URLSearchParams({ q: criteria })
            const queryString = queryParams.toString();

            const response = await this.axiosClient.get(`watchlists/search?${queryString}`);

            return response.data.watchlists;
        } catch (error) {
            this.handleError(error, errorCallback)
        }

    }

    async updateWatchlistName(id, title) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can update watchlists.");
    
            const response = await this.axiosClient.put(`watchlist/${id}/update`, {
                title: title
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.watchlist; 
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }


    /**
     * Update for watchlists.
     * @param id A string containing update criteria to pass to the API.
     * @param errorCallback A function to execute if the call fails.
     * @returns The watchlists that match the updatedwatchlist.
     */
    async updateWatchlist(id, title, errorCallback) {
        try {
        const token = await this.getTokenOrThrow("Only authenticated users can delete a watchlist.");
        const response = await this.axiosClient.put(`watchlist/${id}/update`, {
            updatedWatchlistName : title,
        }, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
        return response.data.watchlist;
    } catch (error) {
        this.handleError(error, errorCallback)
    }
}

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
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

