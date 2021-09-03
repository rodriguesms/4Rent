import React from 'react';
import Home from './pages/Home/index';
import SignIn from './pages/SignIn/index'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { ThemeProvider, createTheme } from '@material-ui/core/styles'
import GuestRoute from './routes/Guest'
import { Provider } from 'react-redux';
import store from './redux/store';
import Auth from './components/Auth/auth'
import SignUp from './pages/SignUp';


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
              <GuestRoute path="/register" element={<SignUp/>} />
            </Routes>
          </Auth>
        </BrowserRouter>
      </ThemeProvider>
    </Provider>
    );
}
export default App;
