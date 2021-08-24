import React, {useEffect, useState} from 'react';
import Home from './pages/Home/index';
import api from './api';

interface AppProps { }

interface RealStateProps {
  id: number,
  announcementTitle: string,
  announcementDate: string,
  city: string,
  state: string,
  price: number,
  forRent: boolean,
  status: string,
  type: string
}

const App:React.FC<AppProps> = () => {

  const [realStates, setRealStates] = useState<Array<RealStateProps>>([]);
  const [isLoading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    api
      .get(`/realstates`)
      .then((response) => setRealStates(response.data.content))
      .catch((err) => {
        console.error(err)          
      });
    setLoading(false);
  }, []);


  if(isLoading){
    console.log('isLoading')
    return (
      <Home />
    );
  }else{
    console.log(realStates)
    return(
      <Home />
    );
  }

}


export default App;
