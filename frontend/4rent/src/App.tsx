import React from 'react';
import GlobalContext from './context';
import Home from './pages/Home/index';
import SignIn from './pages/SignIn/index'
import NewRealState from './pages/Announce/announce';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { ThemeProvider, createTheme } from '@material-ui/core/styles'
import GuestRoute from './routes/Guest'
import { Provider } from 'react-redux';
import store from './redux/store';
import { useSelector } from 'react-redux';
import Auth from './components/Auth/auth'
import AuthenticatedRoute from './routes/Authenticated';


interface AppProps { }

const theme = createTheme({
  typography: {
    fontFamily: [
      'PT Sans Narrow',
      'sans-serif'
    ].join(','),
  }
});

const App:React.FC<AppProps> = () => {

  return (
    <Provider store={store}>
      <ThemeProvider theme={theme}>
        <BrowserRouter>
          <Auth>
            <Routes>
              <Route path="//*" element={<Home />} />
              <GuestRoute path="/sign-in" element={<SignIn/>} />
            </Routes>
          </Auth>
        </BrowserRouter>
      </ThemeProvider>
    </Provider>
    );
}
export default App;
