import React, { useState, useEffect } from 'react';
import ListPostFollowUser from './ListPostFollowUser'
import Followers from './Followers';
import { useHistory } from "react-router-dom";
import { addPost, getUser, getUserByID } from './API';

const UserProfile = () => {
  const history = useHistory()
  const [myPosts, setMyPosts] = useState([]);
  const [name, setName] = useState([]);
  const [followers, setFollowers] = useState([]);
  const [photo, setPhoto] = useState();
  const [id, setId] = useState();

  const token = localStorage.getItem("token");
  const headers = {
    authorization: token,
  };

  useEffect(() => {

    getUser({ headers: headers })
      .then(response => {
        setId(response.data.idUser);
        setName(response.data.name);
        setPhoto(response.data.image);
        setFollowers(response.data.followers);
        getPosts(response.data.idUser);
        localStorage.setItem('search', "")
      })
      .catch((error) => console.log("Error: ", error));
  }, []);

  const getPosts = (idUser) => {

    getUserByID(idUser, { headers: headers })
      .then(response => {

        setMyPosts(response.data.posts);
        setFollowers(response.data.followers);
      }
      )
      .catch((error) => console.log("Error: ", error));;
  }

  const logOut = () => {
    localStorage.clear();
    window.location.reload();
  }

  const addElemPosts = (portrait, description) => {
    const data = {
      portrait: portrait,

      description: description
    };

    addPost(data, { headers: headers })
      .then((response) =>
        setMyPosts((prevState) => [{ portrait, description }, ...prevState])
      )
      .catch((err) => console.log("Error:", err));
  };

  const goToTimeline = () => (history.push("/Timeline"))

  return (
    <div className="StartWrapper">
      <div className="box Header">
        <div><button className="btn btn-outline-warning ImageLeft" onClick={goToTimeline}>Timeline</button></div>
        <div><img className="ImageCenter" src="Images/InstagramLettersO.png" alt="InstagramLettersO" onClick={goToTimeline}/></div>
        <div><button className="btn btn-outline-danger ImageRight" onClick={logOut}><p id = "myLogOutId">Log out</p></button></div>
      </div>
      <ListPostFollowUser elems={myPosts} />

      <Followers followers={followers} />
      <div className="box Bottom">
      </div>
    </div>
  );
}

export default UserProfile;