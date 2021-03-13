import React, { useEffect, useState} from "react";
import {useHistory} from "react-router-dom";
import { searchByQuery } from "./API";

const SearchBar = () =>{

    const history= useHistory()
    const[search, setSearch] = useState()
    const token = localStorage.getItem("token");
    const headers = {
        authorization: token,
    };
    
    useEffect(() => {
      if(!localStorage.getItem("search")){
        localStorage.setItem("search", '')
      }
      setSearch(localStorage.getItem("search"))
    })


    const doSearch = (value) => {
        
        searchByQuery(value, {headers:headers})
        .then(history.push("/search"))
    }

    const searchChanged = (event) => {
      localStorage.setItem("search", event.target.value)

   
      doSearch(event.target.value)
    }

    return (
        <>
          <input id ="searchVar" className="" type="text" placeholder="Search" onChange={searchChanged} value={search} />
        </>
    )
}

export default SearchBar;          