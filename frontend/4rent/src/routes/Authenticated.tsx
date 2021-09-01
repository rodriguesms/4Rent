import React from 'react';
import { Route } from 'react-router-dom'
import Home from '../pages/Home/index'
import { useSelector } from 'react-redux';
import SignIn from '../pages/SignIn';

interface GuestRouteProps {
    element: any,
    path: string
}

const AuthenticatedRoute:React.FC<GuestRouteProps> = ({element: Component, ...rest}) => {

    const account = useSelector((state:any) => state.account);
    const isAuthenticated = Boolean(account.user);

    return(
        <Route {...rest} element={(
            isAuthenticated ? 
            Component :
            <SignIn />
        )} />
    )
}

export default AuthenticatedRoute;