import React from "react";
import { CardActionArea } from '@material-ui/core';
import { makeStyles } from '@material-ui/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar'
import { useNavigate } from 'react-router-dom';
import UserIcon from "./UserIcon";

interface HeaderProps { }

const useStyles = makeStyles({
    appbar: {
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
    iconHome: {
        width: 50,
    }
});

const Header:React.FC<HeaderProps> = () => {

    const navigate = useNavigate();
    const classes = useStyles();

    return(
        <AppBar position="fixed" className={classes.appbar}>
            <Toolbar>
                <CardActionArea className={classes.iconHome} onClick={() => navigate('/feed')}>
                    <img src="/images/Group 2.svg" alt="logo.svg" className={classes.img}></img>
                </CardActionArea>
                <div className={classes.grow}></div>
                <UserIcon />
            </Toolbar>
        </AppBar>
    );
}

export default Header;