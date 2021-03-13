import React from "react";
import {useHistory} from "react-router-dom";
import '../css/SearchResult.css';
import { getUserByID } from "./API";

const ListSearch = (props) => {

    const history = useHistory()
    const token = localStorage.getItem("token");
    const headers = {
        authorization: token,
    };

    const goToPost = (event) => {
        const postId = event.target.getAttribute('value')
        history.push("/post/" + postId)
    }

    const goToUser = (event) => {
        const id = event.target.getAttribute('value')
        
        getUserByID(id, {headers:headers})
        .then(response => {            
            history.push("/user/" + id);
            window.location.reload();
        });
    };    
    
    const addResults = () =>
    {
        props.elemsDescription.map(e => console.log(e))
    }
    

    {if(props.value.length == 0 || props.error=== "error") {
        return(<div className="box Lateral Description"> No Hay Resultados Que Mostrar </div>)
    }

    if(props.value.includes("#")) {
        return (   
            <div className="box Lateral Posts">
                <div className= "UserPostWrapper">
                    {props.result.map(post =>(
                    <div key={post.id}>
                        <img id="postOU" src={post.portrait} onClick= {goToPost} value = {post.id} />
                    </div>
                    ))}
                </div>
            </div>)
    } else {
        return (   
            <div className="box Lateral UserResult">
                {props.result.map(e =>(
                    <button type="button" key={e.id}  className="btn btn-ligh buttonFollower botonResult" onClick= {goToUser} value= {e.id}>
                        <img type="button" className="UserPhoto" key={e.id} alt={e.image} src={e.image} value= {e.id}/> {e.name}
                    </button>
                    ))
                }
            </div>)
    }
}
}

export default ListSearch;