import React, { Component, useEffect } from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect
} from "react-router-dom";

import Start from "./Start";
import TimeLine from "./TimeLine";
import UserProfile from "./UserProfile"
import FollowUser from './FollowUser';
import Post from './Post';
import SearchResult from './SearchResult';

const InstagramApp = () => {
  
  return (
    <Router>
          <Switch>
            <ProtectedRoute path = "/search" component={SearchResult}/>                               
            <ProtectedRoute path="/UserProfile" component= {UserProfile}/>
            <ProtectedRoute path="/User/:userId" component= {FollowUser}/>
            <ProtectedRoute path="/Post/:postId" component= {Post}/>
            <ProtectedRoute path = "/TimeLine" component={TimeLine}/>
            {localStorage.token && <Route path = "/" component={TimeLine}/>}
            <Route path = "/" component={Start}/>
          </Switch>
        </Router>
    );
}

class ProtectedRoute extends Component {
  render() {
    const { component: Component, ...props } = this.props

    return (
      <Route 
        {...props} 
        render={props => (
          localStorage.token ?
            <Component {...props} /> :
            <Redirect to='/' />
        )} 
      />
    )
  }
}

export default InstagramApp;