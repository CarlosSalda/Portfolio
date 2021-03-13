import { Container } from '@material-ui/core';
import React, {useEffect} from 'react';
import queryString from 'query-string';

import { useDispatch } from 'react-redux';
import {searchMovie} from '../../redux/actions/search';


export default ({location}) => {

    const dispatch = useDispatch();

    useEffect(() => {
        const {movieName} = queryString.parse(location.search);
        dispatch(searchMovie({movieName}));
    })

    return(
        <Container>
                Results
        </Container>
    )
}