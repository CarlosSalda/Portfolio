import React from "react";
import Start from "./Start";
import "../css/LayoutIoma.css";

import {
  BrowserRouter as Router,
  Route,
  Switch,
} from "react-router-dom";

const LayoutIoma = () => {
  
  return (
    <>
      <Router>
        <Switch>
          <Route path="/" component={Start} />
        </Switch>
      </Router>
    </>
  );
};

export default LayoutIoma;
