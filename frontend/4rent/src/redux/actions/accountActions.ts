import authServices from "../../services/authServices";

export const LOGIN_SUCCESS = '@ACCOUNT/LOGIN_SUCCESS';
export const SILENT_LOGIN = '@ACCOUNT/LOGIN_SUCCESS';
export const SIGNOUT = '@ACCOUNT/SIGNOUT';
export const SIGNUP = '@ACCOUNT/SIGNUP';

const signIn = (email: string, password: string) => {

    return async (dispatch: any) => {
        
        const user = await authServices.signIn(email, password);
    
        dispatch({
            type: LOGIN_SUCCESS,
            payload: {
                user
            }
        })
        
    }
}

const signUp = (username: string, email: string, password: string) => {
    
    return async (dispatch: any) => {
        
        const user = await authServices.signUp(username, email, password);

        dispatch({
            type: SIGNUP,
            payload: {
                user
            }
        })

    }

}

const setUserData = () => {
    return async (dispatch: any) => {
        try{
            const user = await authServices.signInWithToken();
        
            dispatch({
                type: SILENT_LOGIN,
                payload: {
                    user
                }
            })
        }catch(error){
            console.error(error); // if token is expired
            signOut();
        }
                
    }
}

const signOut = () => {
    return async (dispatch: any) => {
        await authServices.signOut();
        
        dispatch({
            type: SIGNOUT
        })
    }
}




export { signIn, setUserData, signOut, signUp };