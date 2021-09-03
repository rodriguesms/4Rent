import { Container, makeStyles, Typography, Button, IconButton } from "@material-ui/core";
import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import RealStateCard from "../../components/RealStateCard";
import api from "../../services/api";
import authServices from '../../services/authServices'
import { RealStateDTO } from "../../types";
import { signOut } from "../../redux/actions/accountActions";
import { useNavigate } from 'react-router-dom';
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';

interface ProfileProps { 

    setRealStateUpdateId: Function,
    setRealStateUpdateType: Function

}

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
    rsOperations: {
        display: 'flex',
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center'
    },
    operationIcons: {
        display: 'flex',
        flexDirection: 'column',
        marginLeft: 16
    },
    iconEdit: {
        padding: 18,
        color: '#fff',
    },
    iconDelete: {
        padding: 18,
        color: '#FF0000'
    }
}))


const Profile:React.FC<ProfileProps> = ({ setRealStateUpdateType, setRealStateUpdateId }) => {

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

    const handleEdit = (type: string, id: number) => {
        setRealStateUpdateType(type==="APARTMENT" ? (`apartment`) : (type==="HOUSE" ? ('house') : ('land')));
        setRealStateUpdateId(id);
        navigate('/update');
    }

    const handleDelete = (rsType: string, id: number) => {
        const type = (rsType==="HOUSE") ? ('houses') : ((rsType==="APARTMENT") ? ('apartments') : ('lands'));

        api.delete(`/${type}/${id}`, {
            headers: {Authorization : `Bearer ${authServices.getToken()}`}
        }).then((response) => console.log(response))
        .catch(error => console.error(error))
        .finally(() => navigate('/feed'));
    }

    if(isAuthenticated && !isLoading)
        return (
            <div className={classes.root}>
                <Typography className={classes.name}>
                    {`${account && account.user && account.user.data.owner.username }'s Real States`}
                </Typography>
                <Container className={classes.container} maxWidth="lg">
                        {myRealStates.length > 0 ? (myRealStates.map(element => (
                            <div className={classes.rsOperations}>
                                <RealStateCard realState={element} key={element.id} filter={"realstates"}/>
                                <div className={classes.operationIcons}>
                                    <IconButton onClick={() => handleEdit(element.type, element.id)} className={classes.iconEdit}>
                                        <EditIcon fontSize="large"/>
                                    </IconButton>
                                    <IconButton onClick={() => handleDelete(element.type, element.id)} className={classes.iconDelete}>
                                        <DeleteIcon fontSize="large"/>
                                    </IconButton>
                                </div>
                            </div>
                        ))) : (
                        <Typography className={classes.noRealState}>
                            You do not have any registered Real State! :(
                        </Typography>
    
                        )}
                </Container>
                <Button color='secondary' onClick={handleSignOut} variant='contained'>Sign Out</Button>        
            </div>
                  
        )
    else
        return<div></div>

    
}

export default Profile;