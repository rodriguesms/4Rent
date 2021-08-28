import React from "react";
import { Button } from '@material-ui/core';
//import '../style.css'
import { makeStyles } from '@material-ui/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar'
import SvgIcon from '@material-ui/core/SvgIcon'
import Avatar from '@material-ui/core/Avatar'

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
    button: {
        marginRight: 10
    }
});

const Header:React.FC<HeaderProps> = () => {
    
    return(
        <AppBar position="fixed" className={useStyles().appbar}>
            <Toolbar>
                <img src="/images/Group 2.svg" alt="logo.svg" className={useStyles().img}></img>
                <div className={useStyles().grow}></div>
                <div className={useStyles().userSection}>
                    <Button color='secondary' variant='contained' className={useStyles().button}>Announce</Button>
                </div>
                <Avatar alt="" src="/"/>
                {/* <div>
                    <a href="/">4Rent</a>
                    <input type="text"></input>
                </div>
                <div>
                    <span>img1</span>
                    <span>img2</span>
                </div> */}
            </Toolbar>
        </AppBar>
    );
}

export default Header;