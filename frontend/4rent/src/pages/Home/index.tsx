import React from 'react';
import Header from './components/Header/Header'
import './style.css';

interface HomeProps { }

// eslint-disable-next-line
const Home:React.FC<HomeProps> = ({}) => {
    return(
        <div>
            <Header />
            <main className="main">
                <div className="navbar">navbar</div>
                <div className="announcements">announcements</div>
            </main>
        </div>
    );
}

export default Home;