import React, { useState, useEffect } from "react";
import '../css/Post.css';
import {BrowserRouter as Router, Route, Switch, useHistory} from "react-router-dom";
import AddComment from './AddComment'
import { comment, getPostByID, getUser, getUserByID, like } from "./API";


const Post = ({ match }) => {
    const history= useHistory()
    const postId = match.params.postId
    const [comments, setComments] = useState([]);
    const [description, setDescription] = useState([]);
    const [fecha, setFecha] = useState([]);
    const [landscape, setLandscape] = useState([]);
    const [likes, setLikes] = useState([]);
    const [userImage, setUserImage] = useState([]);
    const [postOwnerId, setPostOwnerId] = useState([])
    const [user, setUser] = useState([]);
    const [idLogged, setIdLogged] = useState([]);

    const token = localStorage.getItem("token");
    const headers = {
        authorization: token,
    };

    useEffect(() => {

        getPostByID(postId, {headers:headers})
        .then(response => {
                setComments(response.data.comments); 
                setDescription(response.data.description); 
                setFecha(response.data.fecha);
                setLandscape(response.data.landscape); 
                setUser(response.data.user.name);
                setUserImage(response.data.user.image);
                setLikes(response.data.likes); 
                setPostOwnerId(response.data.user.id)
        })
        .catch((error) => console.log("Error: ", error));;
    }, []);

    const changeLike =() => {
        const corazon = document.getElementById("buttonfollowP")


        
       
        if (corazon.className === "btn btn-outline-primary") {
            corazon.className = "btn btn-outline-danger"

            like(postId, {headers:headers})
            .catch((error) => console.log("Error: ", error));

            getPostByID(postId, {headers: headers})
            .then(response => {setLikes(response.data.likes);})
        } else {
            corazon.className = "btn btn-outline-primary"
            
            like(postId, {headers:headers})
            .catch((error) => console.log("Error: ", error));

            getPostByID(postId, {headers: headers})
            .then(response => {setLikes(response.data.likes);})
        }
    }

    const startHearth = (likesP) => {
        const list = []
        
        likesP.forEach(e => list.push(e.id))

        getUser({headers:headers})
        .then(response => {setIdLogged(response.data.idUser)})
        .catch((error) => console.log("Error: ", error));;

        if (list.includes(idLogged)) {
            return(<><button id="buttonfollowP" onClick={changeLike} type="button" className="btn btn-outline-danger"><i className="fa fa-heart"></i> </button> {likes.length} Me Gusta</>)
        } else {
            return( <><button id="buttonfollowP" onClick={changeLike} type="button" className="btn btn-outline-primary"><i className="fa fa-heart"></i> </button> {likes.length} Me Gusta</>)
        }
    }

    const goToUser = (event) => {
        const userN = event.target.getAttribute('value')
 

        if(userN == idLogged) {
            history.push("/UserProfile")
        } else {
            getUserByID(userN, {headers:headers})
            .then(history.push("/user/" + userN));
        }
    };

    const addComment = (body) => {
        const data = {body: body};

        comment(postId, data, {headers: headers})
        .catch((err) => console.log("Error:", err));

        getPostByID(postId, {headers:headers})
        .then(response => {
                
                setComments(response.data.comments); 
                setDescription(response.data.description); 
                setFecha(response.data.fecha);
                setLandscape(response.data.landscape); 
                setLikes(response.data.likes); 
                setUser(response.data.user.name); 
                window.location.reload();
            })
            .catch((error) => console.log("Error: ", error));;
    };

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
                <div><img class="ImageCenter" src="Images/InstagramLettersO.png" alt="InstagramLettersO" onClick={goToTimeline} value = {user.id}/></div>
                <div><button className= "btn btn-outline-danger ImageRight" onClick={logOut}><p id = "myLogOutId">Log out</p></button></div>
                </div>
                <div className="box Lateral Post">
                    <img className="Image" src={landscape} alt={landscape}/> <br/>{description}
                    <br/><br/>Comments
                    {comments.map(com => (
                        <div key={com.id} className="card Comment"> 
                            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"/>
                            <img  className="UserPhoto" src={com.user.image} alt={com.user.image} onClick = {goToUser} value={com.user.id}/>{com.user.name}
                            : " {com.body} "
                        </div>
                    ))
                    }
                    <AddComment addComment= {addComment}/>
                </div>
                <div className= "Container PostInfo">
                    <img className="UserPhoto" id="LocalUser" alt={userImage} src={userImage} onClick={goToUser} value={postOwnerId}/>
                    {user}<br/>
                    Posted on:<br/>{fecha.dayOfMonth}/{fecha.month}/{fecha.year}
                   
                    <br/><br/>{startHearth(likes)}
                </div>

                <div className="box Bottom">
                  
                </div>
            </div>
    );
}



export default Post;