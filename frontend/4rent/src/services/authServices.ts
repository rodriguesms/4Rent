import api from './api'

class AuthServices {

    signIn(email: string, password: string) {
        return new Promise((resolve, reject) => {
            api.post("/auth", {
                email: email,
                password: password
            }).then(response => {
                if(response.data) {
                    resolve(response.data);
                }else{
                    reject(response);
                }
            })
            .catch(error => {
                reject(error);
            })
        });
    }

}

const authServices = new AuthServices();

export default authServices;