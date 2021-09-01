import authServices from "../../services/authServices";

export const LOGIN_SUCCESS = '@ACCOUNT/LOGIN_SUCCESS';
export const SILENT_LOGIN = '@ACCOUNT/LOGIN_SUCCESS';
export const SIGNOUT = '@ACCOUNT/SIGNOUT';

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

const setUserData = () => {
    return async (dispatch: any) => {
        const user = await authServices.signInWithToken();
        
        dispatch({
            type: SILENT_LOGIN,
            payload: {
                user
            }
        })
    }
}

const signOut = () => {
    return async (dispatch: any) => {
        const user = await authServices.signOut();
        
        dispatch({
            type: SIGNOUT
        })
    }
}




export { signIn, setUserData, signOut };