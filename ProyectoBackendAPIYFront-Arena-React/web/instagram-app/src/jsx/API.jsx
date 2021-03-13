import axios from 'axios';

const url = "http://localhost:7000";

export const register =function (obj) {
    return axios.post(url + "/Register", obj)
}

export const login = function (obj) {
    return axios.post(url + "/Login", obj)
}

export const getUser =  function (obj) {
    return axios.get(url + "/user", obj)
}

export const getPostByID = function (id, obj) {
    return  axios.get(url + "/post/" + id, obj)
}

export const getUserByID = function (id, obj) {
    return axios.get(url + "/user/" + id, obj)
}

export const follow =  function (id, obj) {
    return  axios.put(url + "/user/" + id + "/follow","", obj)
}

export const like =function (id, obj) {
    return axios.put(url + "/post/" + id + "/like", "", obj)
}

export const comment = function (id, data, obj) {
    return axios.post(url + "/post/" + id + "/comment", data, obj)
}

export const searchByQuery =function (val, obj) {
    const headers = {
        authorization: obj.headers.authorization,
    };
    return axios.get('http://localhost:7000/search', { params: { q: val }, headers: headers})
}

export const addPost =function (data, obj) {
    return axios.post(url + "/post/add", data, obj)
}