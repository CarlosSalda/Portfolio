import React, { useState } from "react";
import '../css/Post.css';
import {useHistory} from "react-router-dom";
import { getPostByID, getUser, getUserByID, like } from "./API";

const TimeLinePost = ({ post }) => {
    const history= useHistory()
    const postId = post.id
    const [likes, setLikes] = useState(post.likes);
    const [idLogged, setIdLogged] = useState([]);

    const token = localStorage.getItem("token");
    const headers = {
        authorization: token,
    };

    const changeLike =() => {
        const corazon = document.getElementById("like"+post.id)

        
       
        if (corazon.className === "btn btn-outline-primary") {
            corazon.className = "btn btn-outline-danger"

            like(postId, {headers: headers})
            .catch((error) => console.log("Error: ", error));

            getPostByID(postId, {headers:headers})
            .then(response => {setLikes(response.data.likes);})

        } else {
            corazon.className = "btn btn-outline-primary"
            
            like(postId, {headers: headers})
            .catch((error) => console.log("Error: ", error));

            getPostByID(postId, {headers:headers})
            .then(response => {setLikes(response.data.likes);})
        }
    }

    const startHearth = (likesP) => {
        const list = []
        
        likesP.forEach(e => list.push(e.id))

        getUser({headers:headers})
        .then(response => {setIdLogged(response.data.idUser)})
        .catch((error) => console.log("Error: ", error));

        if (list.includes(idLogged)) {
            return(<><button id={"like"+post.id} onClick={changeLike} type="button" className="btn btn-outline-danger"><i className="fa fa-heart"></i></button> {likes.length} Me Gusta</>)
        } else {
            return( <><button id={"like"+post.id} onClick={changeLike} type="button" className="btn btn-outline-primary"> <i className="fa fa-heart"></i></button> {likes.length} Me Gusta</>)
        }
    }
    
    const goToUser = (event) => {
        const userN = event.target.getAttribute('value')

        getUserByID(userN, {headers: headers})
        .then(response => {
            history.push("/user/" + userN);
            window.location.reload();
        });
      };  
    
      const goToPost = (event) => {
        const postId = event.target.getAttribute('value')
        history.push("/post/" + postId)
      }

    return (
        <div className="SinglePost" key= {post.id}>
            <div className = "userPostName">
                <img className="UserPhoto" alt={post.user.image} src={post.user.image} onClick = {goToUser} value={post.user.id}/>
                {post.user.name} 
                
            </div>
            <div>
                <img className = "photoPost" alt={post.portrait} src={post.portrait} onClick= {goToPost} value = {post.id} />
            </div>
            {startHearth(post.likes, post.id)}
            <div className = "descriptionPost"> »» {post.description}</div> 
        </div>
    );
}

export default TimeLinePost;