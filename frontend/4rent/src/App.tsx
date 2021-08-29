import React from 'react';
import GlobalContext from './context';
import Home from './pages/Home/index';
import SignIn from './pages/SignIn/index'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { ThemeProvider, createTheme } from '@material-ui/core/styles'

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
    <ThemeProvider theme={theme}>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/sign-in" element={<SignIn />} />
          <Route path="*" element={<h1>404: Not Found</h1>} />
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
    );
}
export default App;
