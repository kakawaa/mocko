import {defineStore} from "pinia";
import {doLogin, doLogout, setHttpToken, removeHttpToken} from "@/api/auth";
import config from "@/config/index.js";

export const useAuthStore = defineStore('auth', {

	state: () => ({
		token: '',
	}),


	actions: {

		//setToken
		setTokenHandle(token) {
			this.token = token
			setHttpToken(this.token)
			sessionStorage.setItem(config.isLogin, "true")
			sessionStorage.setItem(config.Authorization, token)
		},


		//login
		loginHandle(data) {
			return new Promise((resolve, reject) => {
				return doLogin(data)
					.then(
						response => {
							const token = response.data
							this.setTokenHandle(token)
							resolve()
						}
					)
					.catch(
						error => {
							reject(error)
						}
					)
			})
		},


		//logout
		logoutHandle() {
			return new Promise((resolve, reject) => {
				return doLogout()
					.then(
						() => {
							sessionStorage.removeItem(config.isLogin)
							removeHttpToken()
							resolve()
						}
					).catch(
						error => {
							reject(error)
						}
					)
			})
		}

		// -----
	}

})
