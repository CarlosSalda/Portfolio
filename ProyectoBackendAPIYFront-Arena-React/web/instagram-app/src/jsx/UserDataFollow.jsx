import React, {useState, useEffect} from 'react';
import '../css/InstagramApp.css';
import Followers from './Followers';
import { follow, getUserByID } from './API';

const UserDataFollow = ({match}) => {

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
        getUserByID(id, {headers:headers})
        .then(response => {
            setUserPosts(response.data.posts);
            setImage(response.data.image); 
            setName(response.data.name); 
            setFollowers(response.data.followers);
        }
        ) 
        .catch((error) => console.log("Error: ", error));;
    }, []);

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

    const changeFollow =  (event) =>{
        follow(id, {headers: headers})
        .catch((error) => console.log("Error: ", error));
    }

    const startButton = () => {

        let idsFollowers = [] 
        followers.map(i => idsFollowers.concat(i.id))

        if(idsFollowers.includes(id)) {
            setFollowButton("Unfollow")
        } else {
            setFollowButton("Follow")
        }
    }
    
    return(
    <>
        <img className="UserPhoto" id="LocalUser" alt={image} src={image} />{name}<br/>
        <button onClick = {changeFollowButton} id= {followButton} type="button" className={buttonClass}>{startButton()}{followButton}</button>
        <Followers followers={followers}/>
    </>
)}

export default UserDataFollow
