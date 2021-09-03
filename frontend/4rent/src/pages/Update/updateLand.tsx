import { Button, FormControl, FormControlLabel, FormHelperText, FormLabel, Paper, Radio, RadioGroup, Typography } from "@material-ui/core";
import { makeStyles } from "@material-ui/styles";
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router";
import api from "../../services/api";
import authServices from "../../services/authServices";

interface updateLandFormprops {
    id: number
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

const UpdateLand:React.FC<updateLandFormprops> = ({ id }) => {
    
    const classes = useStyles();

    const navigate = useNavigate();

    const [errorMessage, setErrorMessage] = useState<string>();

    const [isLoading, setLoading] = useState<boolean>(true);

    const user = useSelector((state: any) => state.account.user);

    const [announcementTitle, setAnnouncementTitle] = useState<string>();
    const [area, setArea] = useState<number>();
    const [city, setCity] = useState<string>();
    const [definition, setDefinition] = useState<string>();
    const [neighborhood, setNeighborhood] = useState<string>();
    const [number, setNumber] = useState<number>();
    const [ownerEmail, setOwnerEmail] = useState<string>(user && user.data.owner.email);
    const [price, setPrice] = useState<number>();
    const [state, setState] = useState<string>();
    const [street, setStreet] = useState<string>();
    const [zipCode, setZipCode] = useState<string>();

    useEffect(() => {
        setLoading(true);
        api.get(`lands/${id}`)
        .then((response) => {
            setAnnouncementTitle(response.data.announcementTitle);
            setArea(response.data.area);
            setCity(response.data.city);
            setDefinition(String(response.data.forRent));
            setNeighborhood(response.data.neighborhood);
            setNumber(response.data.number);
            setPrice(response.data.price);
            setState(response.data.state);
            setStreet(response.data.street);
            setZipCode(response.data.zipCode);
        }).catch(error => console.error(error))
        .finally(() => setLoading(false));
    }, [id])

    const handleSubmit = () => {
        const forRent = definition==='true' ? (true) : (false);

        api.put(`/lands/${id}`, {
            announcementTitle: announcementTitle,
            area: area,
            city: city,
            forRent: forRent,
            neighborhood: neighborhood,
            number: number,
            ownerEmail: ownerEmail,
            price: price,
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

    if(isLoading){
        return(<div></div>);
    }

    return(
        <Paper className={classes.container}>
            <div className={classes.header}>
                <Typography variant="h4">Land</Typography>
            </div>
            <div className={classes.body}>
                <input className={classes.input} type="text" placeholder={"Announcement Title"} value={announcementTitle} onChange={e => setAnnouncementTitle(e.target.value)}/>                
                <input className={classes.input} type="number" placeholder={"Total Area"} value={area || ''} onChange={e => setArea(parseFloat(e.target.value))}/>                
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
                    <Typography variant="body1">Update</Typography>
                </Button>
            </div>
        </Paper>
    )
}

export default UpdateLand;