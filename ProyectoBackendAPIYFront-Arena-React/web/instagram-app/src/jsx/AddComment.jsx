import React, { useState, useEffect } from "react";
import '../css/InstagramApp.css';
import { getUser } from "./API";

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
          <button disabled = {!body.replace(/\s/g, '').length} type="button" className="btn btn-primary" onClick={addCommentToPost}>Add</button>
        </div>
      </>
      );
}

export default AddComment;