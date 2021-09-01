import React from "react";
import { makeStyles } from '@material-ui/core/styles'
import { Icon } from "@material-ui/core";
import Paper from '@material-ui/core/Paper'
import Button from '@material-ui/core/Button'
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import HouseOutlined from '@material-ui/icons/HouseOutlined'
import ApartmentOutlined from '@material-ui/icons/ApartmentOutlined'
import Landscape from '@material-ui/icons/Landscape'
import AllInclusiveIcon from '@material-ui/icons/AllInclusive';
import authServices from "../../../services/authServices";
import { useNavigate } from 'react-router-dom';
import { useSelector } from "react-redux";

interface FilterBarProps {
    filter: string,
    setFilter: Function,
    refreshContent: Function
}

const useStyles = makeStyles((theme) => ({
    root: {
        padding: theme.spacing(2),
        width: 275,
        marginRight: theme.spacing(2),
        borderRadius: 10,
        maxHeight: 280
    },
    title: {
        marginLeft: 100,
        fontSize: 24
    },
    button: {
        width: '100%'
    },
    formControl: {
        width: '100%'
    },
    selection: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between'
    }
}));


const FilterBar:React.FC<FilterBarProps> = ({ filter, setFilter, refreshContent }) => {
    
    const classes = useStyles();
    const navigate = useNavigate();
    const account = useSelector((state:any) => state.account);

    const isAuthenticated = !!account.user;

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setFilter((event.target as HTMLInputElement).value);
        refreshContent((event.target as HTMLInputElement).value);
    };

    return(
        <Paper className={classes.root}>
            <Button variant="outlined" color="secondary" className={classes.button} onClick={
                isAuthenticated ? 
                (() => navigate('/announce')) : 
                (() => {alert("You must login before announce a Real State!");
                        navigate('/sign-in');})
            }>Announce</Button>
            <p className={classes.title}>Filters</p>
            <FormControl component="fieldset" className={classes.formControl}>
                <FormLabel component="legend" />
                <RadioGroup aria-label="filter" name="filter" value={filter} onChange={handleChange}>
                    <div className={classes.selection}>
                        <FormControlLabel value="realstates" control={<Radio />} label="All Real States" />
                        <Icon>
                            <AllInclusiveIcon />
                        </Icon>
                    </div>
                    <div className={classes.selection}>
                        <FormControlLabel value="houses" control={<Radio />} label="Houses" />
                        <Icon>
                            <HouseOutlined />
                        </Icon>
                    </div>
                    <div className={classes.selection}>
                        <FormControlLabel value="apartments" control={<Radio />} label="Apartments" />
                        <Icon>
                            <ApartmentOutlined />
                        </Icon>
                    </div>
                    <div className={classes.selection}>
                        <FormControlLabel value="lands" control={<Radio />} label="Lands" />
                        <Icon>
                            <Landscape />
                        </Icon>
                    </div>
                </RadioGroup>
            </FormControl>
        </Paper>
    );
}

export default FilterBar;
