import React, { useRef } from 'react';
import { ApartmentDetail, HouseDetail, LandDetail, RealStateDTO } from '../../types';
import { makeStyles, createTheme, ThemeProvider, Icon } from '@material-ui/core';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardHeader from '@material-ui/core/CardHeader'
import CardContent from '@material-ui/core/CardContent'
import CardActions from '@material-ui/core/CardActions'
import Typography from '@material-ui/core/Typography';
import moment from 'moment';
import RoomIcon from '@material-ui/icons/Room';
import PersonIcon from '@material-ui/icons/Person';
import { useState } from 'react';
import { useSelector } from 'react-redux';
import api from '../../services/api';
import LandscapeIcon from '@material-ui/icons/Landscape';
import { useEffect } from 'react';
import ContactsIcon from '@material-ui/icons/Contacts';
import HomeIcon from '@material-ui/icons/Home';
import ApartmentIcon from '@material-ui/icons/Apartment';
import SingleBedIcon from '@material-ui/icons/SingleBed';
import DirectionsCarIcon from '@material-ui/icons/DirectionsCar';

interface RealStateCardProps {
    realState: RealStateDTO,
    filter?: string,
}

const useStyles = makeStyles((theme) =>({
    closedCard: {
        width: 720,
        marginBottom: theme.spacing(2),
        borderRadius: 10,
    },
    openCard: {
        width: 720,
        maxHeight: 500,
        marginBottom: theme.spacing(2),
        borderRadius: 10,
    },
    header: {
        backgroundColor: '#A9A9A9',
        borderRadius: 15,
        color: '#fff',
        fontWeight: 'bold'
    },
    subheader: {
        display: 'flex',
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'space-between',
        paddingLeft: theme.spacing(3),
        paddingRight: theme.spacing(3),
        textOverflow: 'hidden'
    },
    subInfo: {
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        padding: theme.spacing(3),
    },
    location: {
        display: 'flex',
        flexDirection: 'row',
        marginRight: theme.spacing(1),
        paddingLeft: theme.spacing(2)
    },
    globalDetail: {
        display: 'flex',
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'space-between',
        paddingTop: theme.spacing(1),
        paddingLeft: theme.spacing(2),
        paddingRight: theme.spacing(2)
    },
    ownerDetail: {
        display: 'flex',
        flexDirection: 'row'
    },
    addressDetail: {
        paddingTop: theme.spacing(1),
        display: 'flex',
        flexDirection: 'row'
    }
}))

const RealStateCard:React.FC<RealStateCardProps> = ({ realState, filter = "realstate" }) => {

    const classes = useStyles();

    const [isOpen, setOpen] = useState<boolean>(false);
    const [loading, setLoading] = useState<boolean>(true);
    const [apartmentDetail, setApartmentDetails] = useState<ApartmentDetail>();
    const [houseDetail, setHouseDetails] = useState<HouseDetail>();
    const [landDetail, setLandDetails] = useState<LandDetail>();

    const account = useSelector((state:any) => state.account);

    const getApiType = () => {
        switch(realState.type){
            case 'APARTMENT': {
                return 'apartments';
            }
            case 'HOUSE':{
                return 'houses'
            }
            case 'LAND': {
                return 'lands'
            }
            default: {
                return null
            }
        }
    }

    const getHouseDetails = () => {
        if(loading){
            api.get(`/houses/${realState.id}`)
            .then((response) => setHouseDetails(response.data))
            .catch((error) => console.error(error))
            .finally(() => setLoading(false));
        }
    }

    const getLandDetails = () => {
        if(loading){
            api.get(`/lands/${realState.id}`)
            .then((response) => setLandDetails(response.data))
            .catch((error) => console.error(error))
            .finally(() => setLoading(false));
        }
    }

    const getApartmentDetails = () => {
        if(loading){
            api.get(`/apartments/${realState.id}`)
            .then((response) => setApartmentDetails(response.data))
            .catch((error) => console.error(error))
            .finally(() => setLoading(false));
        }
    }

    const getRSDetails = () => {
        if(loading){
            api.get(`/${getApiType()}/${realState.id}`)
            .then((response) => {
                if(realState.type==="APARTMENT"){
                    setApartmentDetails(response.data);
                }
                else if(realState.type==="HOUSE"){
                    setHouseDetails(response.data);
                }
                else if(realState.type==="LAND"){
                    setLandDetails(response.data)
                }
            }).finally(() => setLoading(false));
        }
    }

    const isAuthenticated = !!account.user;

    useEffect(() => {
        if(filter==="realstates"){
            getRSDetails();
        }else if(filter==="lands"){
            console.log(filter)
            getLandDetails();
        }else if(filter==="houses"){
            getHouseDetails();
        }else if(filter==="apartments"){
            getApartmentDetails();
        }
    }, [houseDetail, apartmentDetail, landDetail]);

    const handleFocus = () => {
        setOpen(!isOpen);
    }


    return(
        <Card onClick={handleFocus} className={isOpen ? (classes.openCard) : (classes.closedCard)}>
            {isOpen ? (
                <CardActionArea>
                    <CardHeader
                        title={<Typography align="center" className={classes.header} variant="h5">{`$${realState.price.toLocaleString('pt-BR')}`}</Typography>}
                        >
                    </CardHeader>
                    <div className={classes.subheader}>
                        <Typography variant="h6">{realState.announcementTitle}</Typography>
                        <Typography variant="h6">{realState.forRent ? (`For Rent!`) : (`For Sale!`)}</Typography>
                    </div>
                    <div className={classes.globalDetail}>
                        <div className={classes.ownerDetail}>
                            <Icon>
                                <RoomIcon />
                            </Icon>
                            <Typography variant="body1">{`${realState.city}, ${realState.state}`}</Typography>
                        </div>
                        <div className={classes.ownerDetail}>
                            <Icon>
                                <PersonIcon />
                            </Icon>
                            <Typography variant="body1">{apartmentDetail?.ownerName || houseDetail?.ownerName || landDetail?.ownerName}</Typography>
                        </div>
                    </div>
                    <div className={classes.globalDetail}>
                        <div className={classes.addressDetail}>
                            <Icon>
                                <ContactsIcon />
                            </Icon>
                            <Typography variant="body1">{`${apartmentDetail?.neighborhood || houseDetail?.neighborhood || landDetail?.neighborhood}, ${apartmentDetail?.street || houseDetail?.street || landDetail?.street}`}</Typography>
                            <Typography variant="body1">{`, ${apartmentDetail?.number || houseDetail?.number || landDetail?.number}`}</Typography>
                        </div>
                        <div className={classes.addressDetail}>
                            <Icon>
                                <LandscapeIcon />
                            </Icon>
                            <Typography variant="body1">{`${apartmentDetail?.area || houseDetail?.area || landDetail?.area}m`}</Typography>
                        </div>  
                    </div>
                    
                    {(realState.type==="APARTMENT" || filter==="apartments") ? (
                        <div>
                            <div className={classes.globalDetail}>
                                <div className={classes.addressDetail}>
                                    <Icon>
                                        <ApartmentIcon />
                                    </Icon>
                                    <Typography variant="body1">{`$${apartmentDetail && apartmentDetail.condomValue}`}</Typography>
                                </div>
                                <div className={classes.addressDetail}>
                                    <Typography variant="body1">{`${apartmentDetail && apartmentDetail.aptArea}m`}</Typography>
                                </div>  
                            </div>
                            <div className={classes.globalDetail}>
                            <div className={classes.addressDetail}>
                                <Icon>
                                    <SingleBedIcon />
                                </Icon>
                                <Typography variant="body1">{`${apartmentDetail && apartmentDetail.roomsQuant}`}</Typography>
                            </div>
                            <div className={classes.addressDetail}>
                                <Icon>
                                    <DirectionsCarIcon />
                                </Icon>
                                <Typography variant="body1">{`${apartmentDetail && apartmentDetail.garageSpots}`}</Typography>
                            </div>  
                        </div>
                        </div>
                    ) : (<></>)}
                    {(realState.type==="HOUSE" || filter==="houses")  ? (
                        <div className={classes.globalDetail}>
                            <div className={classes.addressDetail}>
                                <Icon>
                                    <SingleBedIcon />
                                </Icon>
                                <Typography variant="body1">{`${houseDetail && houseDetail.roomsQuant}`}</Typography>
                            </div>
                            <div className={classes.addressDetail}>
                                <Icon>
                                    <HomeIcon />
                                </Icon>
                                <Typography variant="body1">{`${houseDetail && houseDetail.builtArea}m`}</Typography>
                            </div>  
                        </div>
                    ) : (<></>)}
                    

                    <div className={classes.subInfo}>
                        <div>
                            <Typography variant="caption">{realState.type ? (realState.type) : (<></>)}</Typography>
                        </div>
                        <Typography variant="caption">{moment(realState.announcementDate).calendar()}</Typography>
                    </div>
                </CardActionArea>
            ) : (
                <CardActionArea>
                    <CardHeader
                    title={<Typography align="center" className={classes.header} variant="h5">{`$${realState.price.toLocaleString('pt-BR')}`}</Typography>}
                    >
                    </CardHeader>
                    <div className={classes.subheader}>
                        <Typography variant="h6">{realState.announcementTitle}</Typography>
                        <Typography variant="h6">{realState.forRent ? (`For Rent!`) : (`For Sale!`)}</Typography>
                    </div>
                    <div className={classes.location}>
                        <Icon>
                            <RoomIcon />
                        </Icon>
                        <Typography variant="body1">{`${realState.city}, ${realState.state}`}</Typography>
                    </div>
                    <div className={classes.subInfo}>
                        <div>
                            <Typography variant="caption">{realState.type ? (realState.type) : (<></>)}</Typography>
                        </div>
                        <Typography variant="caption">{moment(realState.announcementDate).calendar()}</Typography>
                    </div>
                </CardActionArea>            
            )}
        </Card>
    );

}

export default RealStateCard;