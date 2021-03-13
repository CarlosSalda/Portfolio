import React from "react";
import {useHistory} from "react-router-dom";
import '../css/ListPostHome.css';
import '../css/TimeLine.css';


const ListPostFollowUser = (props) => {

    const history = useHistory()

    const goToPost = (event) => {
        const postId = event.target.getAttribute('value')
        history.push("/post/" + postId)
    }

    return (
        <div className= "box Lateral Posts">
            <div className="UserPostWrapper">
                {props.elems.map(post => (
                    <div key={post.id}>
                        <img id="postOU" src={post.portrait} onClick= {goToPost} value= {post.id}/>                
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ListPostFollowUser;