import React from 'react';
import GlobalContext from './context';
import Home from './pages/Home/index';
import SignIn from './pages/SignIn/index'
import { BrowserRouter, Routes, Route } from 'react-router-dom'

interface AppProps { }

const App:React.FC<AppProps> = () => {

  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/sign-in" element={<SignIn />} />
          <Route path="*" element={<h1>404: Not Found</h1>} />
        </Routes>
      </BrowserRouter>
    );
}
export default App;
