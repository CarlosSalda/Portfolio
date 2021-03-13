import React, { useState } from 'react';


const AddPostHome= (props) => {
  const [portrait, setPortrait] = useState(props.portrait || '');
  const [landscape, setLandscape] = useState(props.landscape || '');
  const [description, setDescription] = useState(props.description || '');

 
  const addElement = () => {
    props.addElemPosts(portrait, landscape, description);
    setPortrait('');
    setLandscape('');
    setDescription('');

  };

  const changePortrait = (event) => setPortrait(event.target.value);
  const changeLandscape = (event) => setLandscape(event.target.value);
  const changeDescription = (event) => setDescription(event.target.value);


  return (
    <div className="addTODO">
      <div className="row">
        <div className="col-1 fix-possition text-right">
          <img className="rounded-circle small" src= {JSON.parse(localStorage.getItem("profilePicture"))} alt="" />
        </div>
        <div className="col-11">
          <input value={portrait} onChange={changePortrait} className="form-control mb-2" placeholder="Portrait" />
          <input value={landscape} onChange={changeLandscape} className="form-control mb-2" placeholder="Landscape" />
          <textarea value={description} onChange={changeDescription} className="form-control" aria-label="With textarea" placeholder="Description" />        
        </div>
      </div>
      <div className="buttonContainer text-right">
        <button type="button" className="btn btn-primary" onClick={addElement}>Add</button>
      </div>
    </div>
  );
};

export default AddPostHome;