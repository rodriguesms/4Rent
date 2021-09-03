import { makeStyles } from '@material-ui/styles';
import React from 'react';
import UpdateHouse from './updateHouse';
import UpdateApartment from './updateApartment';
import UpdateLand from './updateLand';

interface UpdateRealStateProps {
    id: number,
    type: string,
}

const useStyles = makeStyles(({
    root: {
        display: 'flex',
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center'
    },
    select: {
        width: 200,
        height: 160,
        borderRadius: 15,
        padding: 16,
        marginRight: 16,
    },
    header: {
        display: 'flex',
        justifyContent: 'center',
    },
    selection: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between'
    },
    formControl: {
        width: '100%'
    },
}))

const UpdateRealState:React.FC<UpdateRealStateProps> = ({id, type}) => {
    
    const classes = useStyles();

    return (
        <div className={classes.root}>
            {
                (type === "house") ? (<UpdateHouse id={id}/>) : ((type === "apartment") ? (<UpdateApartment id={id}/>) : (<UpdateLand id={id}/>))
            }
        </div>
    );

}

export default UpdateRealState;