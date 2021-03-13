import React, { useState } from 'react';

const AddPostUser = (props) => {
  const [portrait, setPortrait] = useState(props.portrait || '');
  
  const [description, setDescription] = useState(props.description || '');

  const addElement = () => {
    props.addElemPosts(portrait, description);
    setPortrait('');

    setDescription('');
  };

  const changePortrait = (event) => setPortrait(event.target.value);

  const changeDescription = (event) => setDescription(event.target.value);

  return (
    <div className="addTODO">
      <div className="row">
        <div className="col-1 fix-possition text-right">
          <img className="rounded-circle small" src= {props.image} alt="{props.image}" />
        </div>
        <div className="col-11">
          <input value={portrait} onChange={changePortrait} className="form-control mb-2" placeholder="Portrait" />

          <textarea value={description} onChange={changeDescription} className="form-control" aria-label="With textarea" placeholder="Description" />
        </div>
      </div>
      <div className="buttonContainer text-right">
        <button type="button" className="btn btn-primary" onClick={addElement}>Add</button>
      </div>
    </div>
  );
};

export default AddPostUser;