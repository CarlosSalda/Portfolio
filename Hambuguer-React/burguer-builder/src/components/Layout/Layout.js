import React from "react";
import Aux1 from '../../hoc/Aux1';
import Toolbar from "../Navigation/Toolbar/Toolbar";
import './layout.css';

const layout = (props) => {
  return (
    <Aux1>
      <Toolbar/>
      <main className='Content'>{props.children}</main>
    </Aux1>
  );
};

export default layout;