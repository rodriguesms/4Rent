import React from 'react';
import './style.css';

interface HomeProps { }

// eslint-disable-next-line
const Home:React.FC<HomeProps> = ({}) => {
    return(
        <div>
            <header className="header">
                <div className="toolbar">
                    <div>
                        <span>4Rent</span>
                    </div>
                    <div>
                        <button>Announce Real State</button>
                        <span>img1</span>
                        <span>img2</span>
                    </div>
                </div>
            </header>
            <main className="main">
                <div className="navbar">navbar</div>
                <div className="announcements">announcements</div>
            </main>
        </div>
    );
}

export default Home;