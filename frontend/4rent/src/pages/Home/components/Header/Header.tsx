import React, { useState } from "react";
import './style.css';

interface HeaderProps {}


const Header:React.FC<HeaderProps> = () => {

    const [isLogged, setLogin] = useState<boolean>(true);
    //setLogin(true);

    return(
        <header className="header">
            <div className="toolbar">
                <div className="logo">
                    <a href="/">4Rent</a>
                </div>
                <div>
                    {isLogged ? 
                    <button>Announce</button>:
                    <button>Login</button>}

                    <span>img1</span>
                    <span>img2</span>
                </div>
            </div>
        </header>
    );
}

export default Header;