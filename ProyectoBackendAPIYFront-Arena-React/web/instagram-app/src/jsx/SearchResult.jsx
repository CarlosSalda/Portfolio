import React, { useEffect, useState } from "react";
import ListSearch from './ListSearch'
import SearchBar from './SearchBar'
import { useHistory } from "react-router-dom";
import { searchByQuery } from "./API";

const SearchResult = () => {
  const history = useHistory();
  const [result, setResult] = useState([]);
  const [error, setError] = useState()
  const valueS = localStorage.getItem("search");
  const token = localStorage.getItem("token");
  const headers = {
    authorization: token,
  };

  useEffect(() => {

    searchByQuery(valueS, { headers: headers })
      .then(response => {
        setError("")
        setResult(response.data.content);
        const input2= document.getElementById("searchVar")
        input2.focus()
      })
      .catch((error) => setError("error"))
  })

  const goToMyProfile = () => (history.push("/UserProfile"))

  const logOut = () => {
    localStorage.clear();
    window.location.reload();
  }
  const goToTimeline = () => (history.push("/TimeLine"))

  return (
    <div className="StartWrapper">
      <div className="box Header">
        <div><button className="btn btn-outline-warning ImageLeft" onClick={goToMyProfile}><p id = "myProfileId">My Profile</p></button></div>
        <div><img className="ImageCenter" src="Images/InstagramLettersO.png" alt="InstagramLettersO" onClick={goToTimeline} /></div>
        <div><button className="btn btn-outline-danger ImageRight" onClick={logOut}><p id = "myLogOutId">Log out</p></button></div>
      </div>
      <ListSearch result={result} value={valueS} error={error} />
      <div className="Container">
        <div><SearchBar/></div>
      </div>
      <div className="box Bottom" />
    </div>
  );
}

export default SearchResult;