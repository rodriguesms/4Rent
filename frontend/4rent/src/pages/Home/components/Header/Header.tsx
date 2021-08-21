import React from "react";
import './style.css';

interface HeaderProps {}

// eslint-disable-next-line
const Header:React.FC<HeaderProps> = ({}) => {
    return(
        <header className="header">
            <div className="toolbar">
                <div>
                    <a href="/">4Rent</a>
                </div>
                <div>
                    <button>Announce Real State</button>
                    <span>img1</span>
                    <span>img2</span>
                </div>
            </div>
        </header>
    );
}

export default Header;