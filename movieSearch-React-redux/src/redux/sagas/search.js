import { put, call, takeLatest } from "redux-saga/effects";
import {
  SEARCH_MOVIE_START,
  SEARCH_MOVIE_ERROR,
  SEARCH_MOVIE_COMPLETE,
  SEARCH_MOVIE_BY_ID_START,
  SEARCH_MOVIE_BY_ID_ERROR,
  SEARCH_MOVIE_BY_ID_COMPLETE,
} from "../../consts/actionTypes";
import { apiCall, apiCallId } from "../api";

export function* searchMovie({ payload }) {
  try {
    const result = yield call(
      apiCall,
      `&s=${payload.movieName}`,
      null,
      null,
      "GET"
    );

    yield put({ type: SEARCH_MOVIE_COMPLETE, result });
  } catch (error) {
    yield put({ type: SEARCH_MOVIE_ERROR, error });
  }
}

export function* searchMovieById({ payload }) {
  try {
    const movie = yield call(
      apiCallId,
      `?i=${payload.movieId}`,
      null,
      null,
      "GET"
    );

    yield put({ type: SEARCH_MOVIE_BY_ID_COMPLETE, movie });
  } catch (error) {
    yield put({ type: SEARCH_MOVIE_BY_ID_ERROR, error });
  }
}

export default function* search() {
  yield takeLatest(SEARCH_MOVIE_START, searchMovie);
  yield takeLatest(SEARCH_MOVIE_BY_ID_START, searchMovieById);
}
