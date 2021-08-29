import React from "react";
import { Button } from '@material-ui/core';
//import '../style.css'
import { makeStyles } from '@material-ui/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar'
import SvgIcon from '@material-ui/core/SvgIcon'
import Avatar from '@material-ui/core/Avatar'
import { useNavigate } from 'react-router-dom';

interface HeaderProps { }

const useStyles = makeStyles({
    appbar: {
        boxShadow: "none",
        background: "#fff"
    },
    img: {
        maxHeight: 60
    },
    grow: {
        flexGrow: 1
    },
    userSection: {
        display: 'flex',
        alignItems: 'center'
    },
    button1: {
        marginRight: 10
    },
    button2: {
        marginRight: 10
    }
});

const Header:React.FC<HeaderProps> = () => {
    
    const isLogged = false;
    const navigate = useNavigate();
    const classes = useStyles();

    return(
        <AppBar position="fixed" className={classes.appbar}>
            <Toolbar>
                <img src="/images/Group 2.svg" alt="logo.svg" className={classes.img}></img>
                <div className={classes.grow}></div>
                {isLogged ? (
                    <div> 
                        <div className={classes.userSection}>
                            <Button color='secondary' variant='contained' className={classes.button1}>Announce</Button>
                        </div>
                        <Avatar alt="" src="/"/>
                    </div>
                ) : (
                    <div>
                        <Button color='secondary' onClick={() => navigate('/sign-in')} variant='contained' className={classes.button2}>Sign In</Button>
                    </div>
                )}
            </Toolbar>
        </AppBar>
    );
}

export default Header;