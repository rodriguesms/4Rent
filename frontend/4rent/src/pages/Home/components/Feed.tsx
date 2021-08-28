import React, {useState, useEffect} from "react";
import { makeStyles } from '@material-ui/core/styles' 
import RealStateCard from "../../../components/RealStateCard";
import { RealStateDTO } from "../../../types";

const useStyles = makeStyles((theme) => ({
    root: {

    }
}));

interface FeedProps {
    filter: string,
    getContent: Function,
    isLoading: boolean,
    realStateList: Array<RealStateDTO>
}

const realStatesMock = [
    {
        id: 1,
        announcementTitle: "string",
        announcementDate: "string",
        city: "string",
        state: "string",
        price: 12313,
        forRent: false,
        status: "string",
        type: "string"
    },
    {
        id: 2,
        announcementTitle: "string",
        announcementDate: "string",
        city: "string",
        state: "string",
        price: 12313,
        forRent: false,
        status: "string",
        type: "string"
    }
];

const Feed:React.FC<FeedProps> = ({filter, getContent, isLoading, realStateList}) => {




    useEffect(() => {
       getContent(filter);
    }, [])

    if(isLoading){
        return(
            <div>carregando</div>
        );
    }

    else {
        return(
            <div>
                {realStateList.map(element => (
                    <RealStateCard realState={element} key={element.id} filter={filter}/>
                ))}
            </div>
        );
    } 


}

export default Feed;