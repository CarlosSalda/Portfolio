import React, { useState, useEffect } from 'react';
import ListPostHome from './ListPostHome';
import Followers from './Followers'
import '../css/TimeLine.css';
import '../css/InstagramApp.css';
import {useHistory} from "react-router-dom";
import { getUser } from './API';

const TimeLine = () => {
  const history = useHistory();
  const [userData, setUserData] = useState();
  const [name,setName] = useState([]);
  const [photo, setPhoto] = useState([]);
  let [followers, setFollowers] = useState([]);
  const [timeLine, setTimeLine] = useState([]);
  const token = localStorage.getItem("token");
  const headers = {authorization: token};

  useEffect(() => {

    getUser({headers: headers})
    .then(response => {
        setUserData(response.data);
        setTimeLine(response.data.timeline);
        setFollowers(response.data.followers);
        setName(response.data.name);
        setPhoto(response.data.image);
        localStorage.setItem('search', "")
      })
  }, []);

  const goToMyProfile = () => (history.push("/UserProfile"))

  const logOut = () => {
    localStorage.clear();
    window.location.reload();
  }
const goToTimeline = () => (history.push("/TimeLine"))

return (
      <div className="StartWrapper">
      <div className="box Header">
      <div><button className= "btn btn-outline-warning ImageLeft" onClick={goToMyProfile}><p id = "myProfileId">My Profile</p></button></div>
      <div><img className= "ImageCenter" src= "Images/InstagramLettersO.png" alt= "InstagramLettersO" onClick={goToTimeline}/></div>
      <div><button className= "btn btn-outline-danger ImageRight" onClick={logOut}><p id = "myLogOutId">Log out</p></button></div>
         
      </div>
        
        <ListPostHome timeLine = {timeLine}/>
       
        <Followers followers={followers}/>
      <div className="box Bottom">
       
      </div>
    </div>
  );
}

export default TimeLine; 