import React, { useState, useEffect } from "react";
import '../css/Post.css';
import '../css/InstagramApp.css';
import {useHistory} from "react-router-dom";
import { getPostByID, getUser, getUserByID } from "./API";

const Comments = ({comments, postId, setComments}) => {
    const history = useHistory();

    const token = localStorage.getItem("token");
    const headers = {
        authorization: token,
    };
    
    const addComment = (body) => {
        const data = {body: body};
   

        getPostByID(postId, {headers:headers})
        .then(response => {
            setComments(response.data.comments);
             }
        ).catch((error) => console.log("Error: ", error));;

    };

    const goToUser = (event) => {
        const userN = event.target.getAttribute('value')
        getUserByID(userN, {headers: headers})
        .then(history.push("/user/" + userN));
    };

    return(
        <>
        <br/><br/>Comments
            {comments.map(com => (
                <div key={com.id} className="card Comment">
                    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"/>
                    <img  className="UserPhoto" src={com.user.image} alt={com.user.image} onClick = {goToUser} value={com.user.id}/>{com.user.name}
                    : " {com.body} "
                </div>
            ))}
            <AddComment addComment= {addComment}/>
        </>
    );
}

export default Comments

const AddComment = ({addComment}) => {

    const [body, setBody] = useState('');

    const [image, setImage] = useState()
    const [name, setName] = useState()
    const token = localStorage.getItem("token");
    const headers = {
        authorization: token,
    };

    const addCommentToPost = () => {
        addComment(body);
        setBody('')
    };

    const changeBody = (event) => setBody(event.target.value)

    useEffect(() => {
        getUser({headers:headers})
        .then(response => {
              setName(response.data.name);
              setImage(response.data.image);
          })
      }, []);

    return (
      <>
        <div className="card Comment">
            <img className="UserPhoto" src= {image} alt={image} /> {name}
            <textarea value={body} onChange={changeBody} className="form-control" aria-label="With textarea" placeholder="Your Comment" />
        </div>
        <div className="buttonContainer text-right">
          <button type="button" className="btn btn-primary" onClick={() => addComment(body)}>Add</button>
        </div>
      </>
      );
}