import React, { useState, useEffect } from "react";
import '../css/TimeLine.css';
import '../css/InstagramApp.css';
import { useHistory } from "react-router-dom";
import SearchBar from './SearchBar'
import { getUser, getUserByID } from './API';

const Followers = ({ followers }) => {

    const [idLogged, setIdLogged] = useState([]);
    const history = useHistory();
    const token = localStorage.getItem("token");
    const headers = { authorization: token };

    
    useEffect(() => {
        getUser({headers:headers})
        .then(response => {setIdLogged(response.data.idUser)})
    }, []);

    const goToUser = (event) => {
        const id = event.target.getAttribute('value')

        if (id == idLogged) {
            console.log("entreeeeeeeeeeeeeeeeeee1")
            history.push("/UserProfile")
        } else {
            console.log("entreeeeeeeeeeeeeeeeeee2")
            getUserByID(id, { headers: headers })
                .then(response => {
                    history.push("/user/" + id);
                    window.location.reload()
                });
        }
    };


    const goToMyProfile = () => (history.push("/UserProfile"))

    return (
        <div className="Container Followers" >
            <SearchBar />
            <br />Followers
            {followers.map(follower => (
                <button type="button" key={follower.id} className="btn btn-light buttonFollower" onClick={goToUser} value={follower.id}>
                    <img type="button" className="UserPhoto" key={follower.id} alt={follower.image} src={follower.image} value={follower.id} />
                    {follower.name}
                </button>
            ))
            }
        </div>
    );
}

export default Followers;