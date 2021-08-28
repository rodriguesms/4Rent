import React, {useState} from 'react';
//import './style.css';
import { Header, Feed, FilterBar } from './components'
import { makeStyles } from '@material-ui/styles'
import Container from '@material-ui/core/Container'
import Box from '@material-ui/core/Box'
import { RealStateDTO } from "../../types";
import api from "../../services/api";

interface HomeProps { }

const useStyles = makeStyles({
    root: {
        display: "flex",
        flexDirection: "column"
    },
    main: {
        height: "100vh",
        padding: 24
    },
    toolbar: {
        minHeight: 64
    }
})

const Home:React.FC<HomeProps> = () => {

    const [filter, setFilter] = React.useState('realstates');
    const [realStates, setRealStates] = useState<Array<RealStateDTO>>([]);
    const [isLoading, setLoading] = useState<boolean>(true);

    const styles = useStyles();

    const getContent = (consultFilter: string) => {
        setLoading(true);
        console.log(consultFilter);
        api.get(`/${consultFilter}`)
        .then((response) => setRealStates(response.data.content))
        .catch((error) => console.error(error))
        .finally(() => setLoading(false))
    }

    return(
        <div className={styles.root}>
            <Header />
            <div className={styles.toolbar}></div>
            <main className={styles.main}>
                <Container maxWidth="lg">
                    <Box display="flex">
                        <FilterBar filter={filter} setFilter={setFilter} refreshContent={getContent}/>
                        <Feed filter={filter} getContent={getContent} isLoading={isLoading} realStateList={realStates}/>
                    </Box>
                </Container>
            </main>
        </div>
    );
}

export default Home;