import { Box, Container, makeStyles, Typography, Paper, Button } from "@material-ui/core";
import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import RealStateCard from "../../components/RealStateCard";
import api from "../../services/api";
import authServices from '../../services/authServices'
import { RealStateDTO } from "../../types";
import { signOut } from "../../redux/actions/accountActions";
import { useNavigate } from 'react-router-dom';

const useStyles = makeStyles(({
    root: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        height: '100%',
    },
    header: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        minWidth: 500,
        maxWidth: 800,
        overflow: 'hidden',
        height: 50,
        margin: 16,
        borderRadius: 15
    },
    name: {
        padding: 16,
        fontSize: 24,
        fontWeight: 'bold',
        color: '#fff'
    },
    container: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
    },
    noRealState: {
        fontSize: 36,
        fontWeight: 'normal',
        color: '#fff',
        padding: 16
    },
    box: {

    }
}))

interface ProfileProps { }


const Profile:React.FC<ProfileProps> = () => {

    const classes = useStyles();
    const dispatch = useDispatch();

    const navigate = useNavigate();
    const account = useSelector((state: any) => state.account)
    const isAuthenticated = authServices.isAuthenticated();

    const [myRealStates, setMyRealStates] = useState<Array<RealStateDTO>>([]);
    const [isLoading, setLoading] = useState<boolean>(true);
    
    const getContent = () => {
        setLoading(true);
        api.get(`/realstates/user-realstates/${account && account.user && account.user.data.owner.id}`, {headers: { Authorization : `Bearer ${authServices.getToken()}`}})
        .then((response) => {
            setMyRealStates(response.data.content)
        })
        .catch((error) => console.error(error))
        .finally(() => setLoading(false))
    }

    const unauthorizedUser = () => {
        navigate('/sign-in');
    }   

    const handleSignOut = async () => {
        await dispatch(signOut());
        navigate('/feed')
    }


    useEffect(() => {
        if(!isAuthenticated)
            unauthorizedUser();
        else{
            getContent();        
        }
    }, [account]);

    if(isAuthenticated && !isLoading)
        return (
            <div className={classes.root}>
                <Typography className={classes.name}>
                    {`${account && account.user && account.user.data.owner.username }'s Real States`}
                </Typography>
                <Container className={classes.container} maxWidth="lg">
                    <Box className={classes.box}>
                        {myRealStates.length > 0 ? (myRealStates.map(element => (
                            <RealStateCard realState={element} key={element.id} filter={"realstates"}/>
                        ))) : (
                        <Typography className={classes.noRealState}>
                            You do not have any registered Real State! :(
                        </Typography>
    
                        )}
                    </Box>
                </Container>
                <Button color='secondary' onClick={handleSignOut} variant='contained'>Sign Out</Button>        
            </div>
                  
        )
    else
        return<div></div>

    
}

export default Profile;