import React, {useState, useEffect} from "react";
import { makeStyles } from '@material-ui/core/styles' 
import RealStateCard from "../../../components/RealStateCard";
import { RealStateDTO } from "../../../types";
import api
 from "../../../services/api";
import { Box, Container } from "@material-ui/core";
import { FilterBar } from ".";

interface FeedProps { }

const Feed:React.FC<FeedProps> = () => {
    
    const [filter, setFilter] = React.useState('realstates');
    const [realStates, setRealStates] = useState<Array<RealStateDTO>>([]);
    const [isLoading, setLoading] = useState<boolean>(true);
    
    const getContent = (consultFilter: string) => {
        setLoading(true);
        api.get(`/${consultFilter}`)
        .then((response) => setRealStates(response.data.content))
        .catch((error) => console.error(error))
        .finally(() => setLoading(false))
    }

    useEffect(() => {
       getContent(filter);
    }, [])

    if(isLoading){
        return(
            <div></div>
        );
    }


    else {
        return(
            <Container maxWidth="lg">
                <Box display="flex">
                    <FilterBar filter={filter} setFilter={setFilter} refreshContent={getContent}/>
                    <div>
                    {realStates.map(element => (
                        <RealStateCard realState={element} key={element.id} filter={filter}/>
                    ))}
                    </div>
                </Box>
            </Container>                
                
        );
    } 


}

export default Feed;