import React from 'react';
import BurguerLogo from '../../assets/images/burger-logo.png';
import './Logo.css';

const Logo = (props) => {
    return(
        <div className='Logo'>
            <img src= {BurguerLogo} alt='MyBurguerLogo'/>
        </div>
    )
}

export default Logo;
