import React, {useEffect, useCallback} from 'react';
import authService from '../../services/authServices';
import { useDispatch } from 'react-redux';
import { setUserData } from '../../redux/actions/accountActions'

interface authProps {
    children: any
}

const Auth:React.FC<authProps> = ({children}) => {
    
    const dispatch = useDispatch();

    const initAuth = useCallback(async () => {
        if(authService.isAuthenticated()){
            //retrieve the user data again
            await dispatch(setUserData());}
    }, [dispatch])

    useEffect(() => {
        initAuth()
    }, [initAuth])

    return children;

}

export default Auth;