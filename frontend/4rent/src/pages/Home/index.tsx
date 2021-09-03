import React, {useState} from 'react';
import { Feed } from './components'
import Header from './components/Header/index';
import { makeStyles } from '@material-ui/styles'
import NewRealState from '../Announce/announce';
import { Routes, Route } from 'react-router-dom';
import Profile from '../Profile/index'
import UpdateRealState from '../Update/update';

interface HomeProps { }

const useStyles = makeStyles({
    root: {
        display: "flex",
        flexDirection: "column",
        width: '100%'
    },
    main: {
        minHeight: "100vh",
        padding: 24,
    },
    toolbar: {
        minHeight: 64
    }
})

const Home:React.FC<HomeProps> = () => {

    const styles = useStyles();

    const [realStateUpdateId, setRealStateUpdateId] = useState<number>(0);
    const [realStateUpdateType, setRealStateUpdateType] = useState<string>('')

    return(
        <div className={styles.root}>
            <Header />
            <div className={styles.toolbar}></div>
            <main className={styles.main}>
                <Routes>
                    <Route path="/" element={<Feed />} />
                    <Route path="/feed" element={<Feed />} />
                    <Route path="/announce" element={<NewRealState />} />
                    <Route path="/update" element={<UpdateRealState id={realStateUpdateId} type={realStateUpdateType}/>} />
                    <Route path="*" element={<h1>404: Not Found</h1>} />
                    <Route path="/profile" element={<Profile setRealStateUpdateType={setRealStateUpdateType} setRealStateUpdateId={setRealStateUpdateId}/>} />
                </Routes>
            </main>
        </div>
    );
}

export default Home;