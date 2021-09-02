import { FormControl, FormControlLabel, FormLabel, Icon, Paper, Radio, RadioGroup, Typography } from '@material-ui/core';
import { makeStyles } from '@material-ui/styles';
import React from 'react';
import { useState } from 'react';
import HouseOutlined from '@material-ui/icons/HouseOutlined'
import ApartmentOutlined from '@material-ui/icons/ApartmentOutlined'
import Landscape from '@material-ui/icons/Landscape'
import HouseInput from './houseForm';
import ApartmentInput from './apartmentForm';
import LandInput from './landForm';

interface NewRealStateProps {}

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

const NewRealState:React.FC<NewRealStateProps> = () => {
    
    const classes = useStyles();

    const [type, setType] = useState<string>('house');

    const handleTypeChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setType((event.target as HTMLInputElement).value);
    };

    return (
        <div className={classes.root}>
            {
                (type === "house") ? (<HouseInput />) : ((type === "apartment") ? (<ApartmentInput />) : (<LandInput />))
            }
            <Paper className={classes.select}>
                <div className={classes.header}>
                    <Typography variant="h5">Real State type:</Typography>
                </div>
                <FormControl component="fieldset" className={classes.formControl}>
                    <FormLabel component="legend" />
                    <RadioGroup aria-label="type" name="type" value={type} onChange={handleTypeChange}>
                        <div className={classes.selection}>
                            <FormControlLabel value="house" control={<Radio />} label="House" />
                            <Icon>
                                <HouseOutlined />
                            </Icon>
                        </div>
                        <div className={classes.selection}>
                            <FormControlLabel value="apartment" control={<Radio />} label="Apartment" />
                            <Icon>
                                <ApartmentOutlined />
                            </Icon>
                        </div>
                        <div className={classes.selection}>
                            <FormControlLabel value="land" control={<Radio />} label="Land" />
                            <Icon>
                                <Landscape />
                            </Icon>
                        </div>
                    </RadioGroup>
                </FormControl>                
            </Paper>
        </div>
    );

}

export default NewRealState;