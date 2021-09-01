import api from './api'

type Owner = {
    email: string,
    username: string
}

type UserStored = {
    owner: Owner,
    username: string,
    token: string,
    type: string
}

class AuthServices {
    signIn = (email: string, password: string) => {
        return new Promise((resolve, reject) => {
            api.post("/auth", {
                email: email,
                password: password
            }).then(response => {
                if(response.data.owner.username) {
                    this.setToken(response.data.token);
                    resolve(response);
                }else{
                    reject(response);
                }
            })
            .catch(error => {
                reject(error);
            })
        });
    }

    signInWithToken = () => {
        return new Promise((resolve, reject) => {
            api.get(
                "/auth/retrieve", { headers: {Authorization : `Bearer ${this.getToken()}`} })
                .then(response => {
                console.log(response)
                if(response.data.owner.username) {
                    resolve(response);
                }else{
                    reject(response);
                }
            })
            .catch(error => {
                reject(error);
            })
        });
    }

    signOut = () => {
        this.removeToken();
    }

    setToken = (token: string) => localStorage.setItem("token", token);

    removeToken = () => localStorage.removeItem("token");

    setUser = (data: UserStored) => {
        localStorage.setItem("user", JSON.stringify(data.owner));
        localStorage.setItem("token", (`${data.type} ${data.token}`));
    }

    getUser = () => {
        const user = localStorage.getItem("user");
        if(user){
            return JSON.parse(user);
        }else{
            return ""
        }
    }

    getToken = () => {    
        return localStorage.getItem("token");
    }

    isAuthenticated = () => !!(this.getToken());

}

const authServices = new AuthServices();

export default authServices;