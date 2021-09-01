import React, {useState} from 'react';
//import './style.css';
import { Feed, FilterBar } from './components'
import Header from './components/Header/index';
import { makeStyles } from '@material-ui/styles'
import Container from '@material-ui/core/Container'
import Box from '@material-ui/core/Box'
import { RealStateDTO } from "../../types";
import api from "../../services/api";
import NewRealState from '../Announce/announce';
import { Routes, Route } from 'react-router-dom';

interface HomeProps { }

const useStyles = makeStyles({
    root: {
        display: "flex",
        flexDirection: "column",
        width: '100%'
    },
    main: {
        height: "100vh",
        padding: 24,
    },
    toolbar: {
        minHeight: 64
    }
})

const Home:React.FC<HomeProps> = () => {

    const styles = useStyles();

    return(
        <div className={styles.root}>
            <Header />
            <div className={styles.toolbar}></div>
            <main className={styles.main}>
                <Routes>
                    <Route path="/" element={<Feed />} />
                    <Route path="/feed" element={<Feed />} />
                    <Route path="/announce" element={<NewRealState />} />
                    <Route path="*" element={<h1>404: Not Found</h1>} />
                </Routes>
            </main>
        </div>
    );
}

export default Home;