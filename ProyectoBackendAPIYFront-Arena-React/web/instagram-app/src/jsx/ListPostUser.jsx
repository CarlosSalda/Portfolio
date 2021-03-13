import React, { useState } from "react";
import AddPostUser from "./AddPostUser";
import {useHistory} from "react-router-dom";

const GoToUser = () => {const history= useHistory();   history.push("/UserProfile")}

const UserImagePost = (props) => (
  <div className="col-1 aaa">
    <img
      className="rounded-circle"
      src= {props.image}
      alt=""
     
    />
  </div>
);

const HeaderPostUser = (props) => (
  <div className="row">
    <div className="col-11">
      <h6> {props.name} </h6>
    </div>
    <div className="col-1">
      <HeaderPostActionsUser
        index={props.index}
        edit={props.edit}
        delete={props.delete}
      />
    </div>
  </div>
);

const HeaderPostActionsUser = (props) => (
  <div className="btn-group">
    <button type="button" className="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></button>
    <div className="dropdown-menu">
      <button
        className="dropdown-item"
        type="button"
        onClick={() => props.edit(props.index)}
      >
        Edit
      </button>
      <button
        className="dropdown-item"
        type="button"
        onClick={() => props.delete(props.index)}
      >
        Delete
      </button>
    </div>
  </div>
);

const ListPostUser = (props) => {
  const [currentSelected, setCurrentSelected] = useState(null);

  const edit = (index) => setCurrentSelected(index);
  const deleteNote = (index) => props.deleteElemPosts(index);

  const renderEditElem = (index, portrait, description) => (
    <AddPostUser
      addElemPosts={(newPortrait, newDescription) => {
        props.editElemPosts(index,newPortrait, newDescription);
        setCurrentSelected(null);
      }}
      portrait={portrait}

      description={description}
    />
  );

  const renderElem = (index, portrait, description) => (
    <div
      key={index}
      className={
        index === 0
          ? "list-group-item borderList first"
          : "list-group-item borderList"
      }
    >
      <div className="row">
        <UserImagePost image= {props.image} />
        <div className="col-11">
          <HeaderPostUser index={index} edit={edit} delete={deleteNote} name = {props.name}/>
          <img src={portrait}></img>
          <p>{description}</p>
        </div>
      </div>
    </div>
  );

  return (

    <div className="list-group">
      {props.elems.map((elem, index) => {
        if (currentSelected === index) {
          return renderEditElem(index,elem.portrait, elem.description);
        }
        return renderElem(index, elem.portrait, elem.description);
      })}
    </div>
  );
};

export default ListPostUser;