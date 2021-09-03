import { Button, FormControl, FormControlLabel, FormHelperText, FormLabel, Paper, Radio, RadioGroup, Typography } from "@material-ui/core";
import { makeStyles } from "@material-ui/styles";
import React, { useState } from "react";
import { useSelector } from "react-redux";
import api from "../../services/api";
import { HouseForm, emptyHouseForm } from "../../types";
import { useNavigate } from 'react-router-dom'
import authServices from "../../services/authServices";

interface houseFormProps {
    house?: HouseForm,
}

const useStyles = makeStyles(({
    container: {
        width: 640,
        height: 650,
        borderRadius: 15,
        marginRight: 16,
    },
    header: {
        display: 'flex',
        justifyContent: 'center',
        padding: 8,
        marginBottom: 8,
    },
    body: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        height: 400
    },
    submitButton: {
        display: 'flex',
        justifyContent: 'center',
    },
    input: {
        width: 320,
        height: 30,
        borderRadius: 10,
        margin: 8,
        borderWidth: 1,
        textDecoration: 'none',   
        '&&:focus': {
            outline: 'none'
        } 
    },
    twoInputsDiv: {
        display: 'flex',
        flexDirection: 'row',
    },
    twoInputs: {
        width: 150,
        height: 30,
        borderRadius: 10,
        margin: 8,
        borderWidth: 1,
        textDecoration: 'none',   
        '&&:focus': {
            outline: 'none'
        } 
    },
    selection: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between'
    },
    formControl: {
        marginLeft: 80
    },
    definition: {
        display: 'flex',
        height: 100,
    },
    errorMessage: {
        display: 'flex',
        justifyContent: 'center'
    }
}))

const HouseInput:React.FC<houseFormProps> = ({ house = emptyHouseForm }) => {
    
    const classes = useStyles();
    const navigate = useNavigate();

    const user = useSelector((state: any) => state.account.user);

    const [errorMessage, setErrorMessage] = useState<string>();    

    const [announcementTitle, setAnnouncementTitle] = useState<string>(house.announcementTitle);
    const [builtArea, setBuiltArea] = useState<number>(house.builtArea);
    const [area, setArea] = useState<number>(house.area);
    const [city, setCity] = useState<string>(house.city);
    const [floor, setFloor] = useState<number>(house.floor);
    const [definition, setDefinition] = useState<string>(String(house.forRent));
    const [neighborhood, setNeighborhood] = useState<string>(house.neighborhood);
    const [number, setNumber] = useState<number>(house.number);
    const [ownerEmail, setOwnerEmail] = useState<string>(user && user.data.owner.email);
    const [price, setPrice] = useState<number>(house.price);
    const [roomsQuant, setRoomsQuant] = useState<number>(house.roomsQuant);
    const [state, setState] = useState<string>(house.state);
    const [street, setStreet] = useState<string>(house.street);
    const [zipCode, setZipCode] = useState<string>(house.zipCode);

    const handleSubmit = () => {
        const forRent = definition==='true' ? (true) : (false);

        api.post('/houses', {
            announcementTitle: announcementTitle,
            builtArea: builtArea,
            area: area,
            city: city,
            floor: floor,
            forRent: forRent,
            neighborhood: neighborhood,
            number: number,
            ownerEmail: ownerEmail,
            price: price,
            roomsQuant: roomsQuant,
            state: state,
            street: street,
            zipCode: zipCode
        }, 
        {headers: {Authorization : `Bearer ${authServices.getToken()}`}
        }).then((response) => console.log(response))
        .then(() => navigate('/feed'))
        .catch(error => {
            console.error(error)
            setErrorMessage(`Invalid Credentials!`);
        })
    }

    const handleTypeChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setDefinition((event.target as HTMLInputElement).value);
    };

    return(
        <Paper className={classes.container}>
            <div className={classes.header}>
                <Typography variant="h4">House</Typography>
            </div>
            <div className={classes.body}>
                <input className={classes.input} type="text" placeholder={"Announcement Title"} value={announcementTitle} onChange={e => setAnnouncementTitle(e.target.value)}/>                
                <div className={classes.twoInputsDiv}>
                    <input className={classes.twoInputs} type="number" placeholder={"Total Area"} value={area || ''} onChange={e => setArea(parseFloat(e.target.value))}/>                
                    <input className={classes.twoInputs} type="number" placeholder={"Built Area"} value={builtArea || ''} onChange={e => setBuiltArea(parseFloat(e.target.value))}/>                
                </div>
                <div className={classes.twoInputsDiv}>
                    <input className={classes.twoInputs} type="number" placeholder={"Floors"} value={floor || ''} onChange={e => setFloor(parseInt(e.target.value))}/>                
                    <input className={classes.twoInputs} type="number" placeholder={"Quantity of Bedrooms"} value={roomsQuant || ''} onChange={e => setRoomsQuant(parseInt(e.target.value))}/>                
                </div>
                <div className={classes.twoInputsDiv}>
                    <input className={classes.twoInputs} type="text" placeholder={"State"} value={state} onChange={e => setState(e.target.value)}/>                
                    <input className={classes.twoInputs} type="text" placeholder={"City"} value={city} onChange={e => setCity(e.target.value)}/>                
                </div>
                <div className={classes.twoInputsDiv}>
                    <input className={classes.twoInputs} type="text" placeholder={"Neighborhood"} value={neighborhood} onChange={e => setNeighborhood(e.target.value)}/>                
                    <input className={classes.twoInputs} type="text" placeholder={"Street"} value={street} onChange={e => setStreet(e.target.value)}/>                
                </div>
                <div className={classes.twoInputsDiv}>
                    <input className={classes.twoInputs} type="number" placeholder={"Number"} value={number || ''} onChange={e => setNumber(parseInt(e.target.value))}/>                
                    <input className={classes.twoInputs} type="text" placeholder={"Zip Code"} value={zipCode} onChange={e => setZipCode(e.target.value)}/>                
                </div>
                <input className={classes.twoInputs} type="number" placeholder={"Price (US$)"} value={price || ''} onChange={e => setPrice(parseFloat(e.target.value))}/>                
            </div>
            <div className={classes.definition}>
                <FormControl component="fieldset" className={classes.formControl}>
                    <FormLabel component="legend" />
                    <RadioGroup aria-label="type" name="type" value={definition} onChange={handleTypeChange}>
                        <div className={classes.selection}>
                            <FormControlLabel value={'true'} control={<Radio />} label="For Rent" />
                        </div>
                        <div className={classes.selection}>
                            <FormControlLabel value={'false'} control={<Radio />} label="For Sale" />
                        </div>
                    </RadioGroup>
                </FormControl> 
            </div>
            {
                errorMessage &&
                <FormHelperText className={classes.errorMessage} error>
                    {errorMessage}
                </FormHelperText>
                }
            <div className={classes.submitButton}>
                <Button
                    onClick={handleSubmit}
                    variant="contained"
                    color="secondary"
                >
                    <Typography variant="body1">Submit</Typography>
                </Button>
            </div>
        </Paper>
    )
}

export default HouseInput;