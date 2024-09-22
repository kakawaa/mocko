import {defineStore} from "pinia";

export const useDestinationStore
	= defineStore('destination', {

	state: () => ({
		params: new Map(),
	}),


	actions: {

		set(key, value) {
			this.params.set(key, value)
		},

		clear() {
			this.params.clear()
		},

		get(key) {
			return this.params.get(key)
		},
	},

})
