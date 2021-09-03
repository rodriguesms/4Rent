import React from "react";
import { makeStyles } from '@material-ui/core/styles';
import { Icon, Button, Typography, FormHelperText } from "@material-ui/core";
import LockOpenIcon from '@material-ui/icons/LockOpen';
import VpnKeyIcon from '@material-ui/icons/VpnKey';
import { useNavigate } from 'react-router-dom';
import { useState } from "react";
import { useDispatch } from "react-redux";
import { signIn } from '../../redux/actions/accountActions'

export interface SignInProps {}

const useStyles = makeStyles((theme) => ({
    root: {
        width: '100vw',
        height: '100vh',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center'
    },
    container: {
        width: '30%',
        height: '35%',
        background: '#A9A9A9',
        borderRadius: 15,
        overflow: 'hidden',
        display: 'flex',
        flexDirection: 'column',
    },
    header: {
        height: '10%',
        background: '#494949',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        fontFamily: 'PT Sans Narrow',
        fontWeight:'bold',
        color: '#fff',
        fontSize: 18
    },
    body: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        flexDirection: 'column',
        height: '90%',
        color: '#fff'
    },
    form: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        marginBottom: theme.spacing(2)
    },
    button: {
        display: 'flex',
        flexDirection: 'row',
    },
    input: {
        marginBottom: theme.spacing(1),
        width: 250,
        height: 30,
        borderRadius: 10,
        paddingLeft: theme.spacing(1),
        borderWidth: 0,
        textDecoration: 'none',   
        '&&:focus': {
            outline: 'none'
        } 
    },
    registerCall: {
        padding: 16,
        color: '#fff'
    }
}))


const SignIn: React.FC<SignInProps> = () => {

    const [userEmail, setEmail] = useState<string>('');
    const [userPassword, setPassword] = useState<string>('');
    const [errorMessage, setErrorMessage] = useState<string>();

    

    const classes = useStyles();
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const handleSignIn = async (email:string, password:string) => {
        try{ 
           await dispatch(signIn(email, password));
           navigate('/feed')
        }catch(error){
            setErrorMessage(`Invalid Credentials!`);
        }
    }
    
     return(
        <div className={classes.root}>
            <div className={classes.container}>
                <div className={classes.header}>Sign In</div>
                <div className={classes.body}>
                    <Icon fontSize={'large'}>
                        <LockOpenIcon fontSize={'large'}/>
                    </Icon>
                    <div className={classes.form}>
                        <h4>Insert your credentials</h4>
                        <input className={classes.input} type="text" placeholder="Email" value={userEmail} onChange={e => setEmail(e.target.value)}/>
                        <input className={classes.input} type="password" placeholder="Password" value={userPassword} onChange={e => setPassword(e.target.value)}/>
                        {
                        errorMessage &&
                        <FormHelperText error>
                            {errorMessage}
                        </FormHelperText>
                    }
                    </div>
                    <Button
                        variant="contained"
                        color="secondary"
                        endIcon={<VpnKeyIcon/>}
                        onClick={() => handleSignIn(userEmail, userPassword)}
                    >
                        <Typography variant="body1">Sign In</Typography>
                    </Button>
                </div>
            </div>
            <Typography className={classes.registerCall} variant="body1">Doesn't have an account yet?</Typography>
            <Button
                variant="contained"
                color="secondary"
                endIcon={<VpnKeyIcon/>}
                onClick={() => navigate('/register')}
            >
                <Typography variant="body1">Sign up</Typography>
            </Button>
        </div>
    );
}

export default SignIn