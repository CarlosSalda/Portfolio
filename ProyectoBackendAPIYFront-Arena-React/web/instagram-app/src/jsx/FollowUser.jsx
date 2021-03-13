import React, {useState, useEffect} from 'react';
import '../css/InstagramApp.css';
import ListPostFollowUser from './ListPostFollowUser'
import Followers from './Followers';
import {useHistory} from "react-router-dom";
import { follow, getUser, getUserByID } from './API';

const FollowUser = ({match}) => {          
    const history= useHistory()
    const [userPosts, setUserPosts] = useState([]);
    const [name, setName] = useState([]);
    const [image, setImage] = useState([]);
    const [followers, setFollowers] = useState([]);
    const [followButton, setFollowButton] = useState();
    const [buttonClass, setButtonClass] = useState();


    const token = localStorage.getItem("token");
    const headers = {
        authorization: token,
    };

    const id = match.params.userId

    useEffect(() => {
        getUserByID(id, {headers: headers})
        .then(response => {
            setUserPosts(response.data.posts);
            setImage(response.data.image); 
            setName(response.data.name); 
            setFollowers(response.data.followers);
            checkFollow();
            localStorage.setItem('search', "")
        }
        ) 
        .catch((error) => console.log("Error: ", error));;
    }, []);

    const checkFollow = () =>{
        getUser({headers: headers})
        .then(response => {
            const loggedFollows = response.data.followers;

            const idsFollowers = [] 

            loggedFollows.forEach(i => idsFollowers.push(i.id))

            const followedUser = {id:id, name:name, image:image};


            if(idsFollowers.includes(followedUser.id)){
                setFollowButton('Unfollow');
                setButtonClass('btn btn-outline-danger');
            } else{
                setFollowButton('Follow');
                setButtonClass('btn btn-outline-primary');
            }
        })
    }

 

    const FollowUserData = () => (
        <>
            <img className="UserPhoto" id="LocalUser" alt={image} src={image} />{name}<br/>
            <button onClick = {changeFollowButton} id= {followButton} type="button" className={buttonClass}>{followButton}</button>
            <Followers followers={followers}/>
        </>
    );

    const changeFollowButton = (event) => {
       
        const button = document.getElementById({followButton})
        if ('Unfollow' === followButton) {
            setButtonClass('btn btn-outline-primary');
            setFollowButton('Follow');
            changeFollow();
        } else {
            setButtonClass('btn btn-outline-danger');
            setFollowButton('Unfollow');
            changeFollow();
        }
    }

    const changeFollow = (event) =>{
        follow(id, {headers: headers})
        .catch((error) => console.log("Error: ", error));
    }

    const goToMyProfile = () => (history.push("/UserProfile"))

    const logOut = () => {
        localStorage.clear();
        
     }

    const goToTimeline = () => (history.push("/Timeline"))

    return (
          <div className="StartWrapper">
          <div className="box Header">
          <div><button className= "btn btn-outline-warning ImageLeft" onClick={goToMyProfile} ><p id = "myProfileId">My Profile</p></button></div>
                <div><img className= "ImageCenter" src= "Images/InstagramLettersO.png" alt= "InstagramLettersO" onClick={goToTimeline}/></div>
                <div><button className= "btn btn-outline-danger ImageRight" onClick={logOut}><p id = "myLogOutId">Log out</p></button></div>
            </div>
            <ListPostFollowUser elems={userPosts}/>
            <div className="Container UserData">
                <FollowUserData/>
            </div>
            <div className="box Bottom"/>
        </div>
    );
}

export default FollowUser;