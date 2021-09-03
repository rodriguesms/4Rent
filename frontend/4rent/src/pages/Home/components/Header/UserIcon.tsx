import React, { useState, useRef } from 'react';
import { useSelector, useDispatch } from 'react-redux'
import { Menu, Avatar, MenuItem, Typography, makeStyles, CardActionArea, Button } from '@material-ui/core'
import { signOut } from '../../../../redux/actions/accountActions';
import { useNavigate } from 'react-router-dom';

interface UserIconProps { }

const useStyles = makeStyles({
    user: {
        width: 'auto',
        display: 'flex',
        flexDirection: 'row',
        alignItems: 'center',
        borderRadius: 16,
    },
    username: {
        fontSize: 18,
        color: '#494949',
        marginRight: 16
    },
    button2: {
        marginRight: 10
    },
});

const UserIcon:React.FC<UserIconProps> = () => {
    const classes = useStyles();
    const navigate = useNavigate();

    const user = useSelector((state: any) => state.account.user);
    const account = useSelector((state:any) => state.account);
    const [isOpen, setOpen] = useState<boolean>(false);

    const ref = useRef();
    const dispatch = useDispatch();
    const isAuthenticated = !!account.user

    const handleOpen = () => {
        setOpen(true)
    }
    const handleClose = () => {
        setOpen(false);
    }

    const handleSignOut = async () => {
        handleClose();
        await dispatch(signOut());
        navigate('/')
    }

    const goToProfile = () => {
        handleClose();
        navigate('/profile')
    }

    return(
        /*<Button color='secondary' onClick={() => navigate('/sign-in')} variant='contained' className={classes.button2}>Sign In</Button>*/
        <>  
            {isAuthenticated ? (
                <div>
                    <CardActionArea innerRef={ref} className={classes.user} onClick={handleOpen}>
                        <Typography className={classes.username}>{user && user.data && user.data.owner && user.data.owner.username}</Typography>
                        <Avatar
                        alt="Remy Sharp"
                        src={account.user && account.user.avatar}
                    />
                    </CardActionArea>
                    <Menu
                        anchorEl={ref.current}
                        anchorOrigin={{
                            vertical: 'bottom',
                            horizontal: 'center'
                        }}
                        open={isOpen}
                        onClose={handleClose}
                        getContentAnchorEl={null}
                    >   <MenuItem onClick={goToProfile}>Profile</MenuItem>
                        <MenuItem onClick={handleSignOut}>Sign Out</MenuItem>
                    </Menu>
                </div>
            ) : (
                <Button color='secondary' onClick={() => navigate('/sign-in')} variant='contained' className={classes.button2}>Sign In</Button>
            )}
        </>
    );
}

export default UserIcon;