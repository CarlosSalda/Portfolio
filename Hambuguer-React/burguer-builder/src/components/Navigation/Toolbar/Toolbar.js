import React from 'react';
import Logo from '../../Logo/Logo';
import NavigationItems from '../NavigationItems/Navigationitems';
import './Toolbar.css';



const Toolbar = (props) => {
    return(
        <header className='Toolbar'>
            <div> MENU </div>
            <Logo/>
            <NavigationItems/>
        </header>
    )
}

export default Toolbar;