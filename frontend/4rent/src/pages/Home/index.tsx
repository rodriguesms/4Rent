import React from 'react';
import Header from './components/Header/Header'
import './style.css';

interface HomeProps { }

const Home:React.FC<HomeProps> = () => {
    return(
        <div>
            <Header />
            <main className="main">
            </main>
        </div>
    );
}

export default Home;