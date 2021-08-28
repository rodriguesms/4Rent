import React from 'react';
import { RealStateDTO } from '../../types';
import { makeStyles, createTheme, ThemeProvider, Icon } from '@material-ui/core';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardHeader from '@material-ui/core/CardHeader'
import CardContent from '@material-ui/core/CardContent'
import CardActions from '@material-ui/core/CardActions'
import Typography from '@material-ui/core/Typography';
import moment from 'moment';
import RoomIcon from '@material-ui/icons/Room';

interface RealStateCardProps {
    realState: RealStateDTO,
    filter: string
}

const useStyles = makeStyles((theme) =>({
    card: {
        width: 720,
        marginBottom: theme.spacing(2),
        borderRadius: 10
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
        padding: theme.spacing(3)
    },
    location: {
        display: 'flex',
        flexDirection: 'row',
        marginRight: theme.spacing(1),
        paddingLeft: theme.spacing(2)
    }

}))

const RealStateCard:React.FC<RealStateCardProps> = ({realState, filter}) => {

    const classes = useStyles();

    return(
        <Card className={classes.card}>
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
        </Card>
    );

}

export default RealStateCard;