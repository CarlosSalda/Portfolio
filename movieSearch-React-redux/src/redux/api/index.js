import axios from "axios";

const baseURL = 'http://www.omdbapi.com/?i=tt3896198&apikey=8e02a2a8';

export const apiCall = (url2, data, headers, method) =>
  axios({
    method,
    url: baseURL + url2,
    data,
    headers,
  });

  export const apiCallId = (url2, data, headers, method) =>
  axios({
    method,
    url: 'http://www.omdbapi.com/'+ url2 +'&apikey=8e02a2a8',
    data,
    headers,
  });
