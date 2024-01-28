import React, { useRef } from "react";
import { FaBars, FaTimes } from "react-icons/fa";
import { Link } from "react-router-dom";
import '../Styles/main.css';
import logo from '../Images/LOGO_BLANCO_SIN_FONDO.png';

function Navbar() {
    const navRef = useRef();

    const showNavbar = () => {
        navRef.current.classList.toggle("responsive_nav");
    }

    const closeNavbar = () => {
        navRef.current.classList.remove("responsive_nav");
    }

    return (   
        <header>
            <img src={logo} alt="Logo" style={{ maxWidth: '60px' }}/>
            <nav ref={navRef}>
                <Link to="/" onClick={closeNavbar}>Inicio</Link>
                <Link to="/Product" onClick={closeNavbar}>Productos</Link>
                <Link to="/Comparative" onClick={closeNavbar}>Comparativas</Link>
                <Link to="/User" onClick={closeNavbar}>Perfil</Link>
                <button className="nav-btn nav-close-btn" onClick={showNavbar}>
                    <FaTimes />
                </button>
            </nav>
            <button className="nav-btn" onClick={showNavbar}>
                <FaBars />
            </button>
        </header>
    );
}

export default Navbar;
