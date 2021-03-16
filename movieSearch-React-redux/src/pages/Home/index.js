import React, { useState } from "react";
import {
  Container,
  Grid,
  Typography,
  Card,
  TextField,
  Button,
} from "@material-ui/core";
import styles from "./style";

import { MovieIcon } from "../../icons/index";

export default ({history}) => {
  const [searchText, setSearchText] = useState("");
  const classes = styles();


  const handleSearchTextChange = (event) => {
    setSearchText(event.target.value);
  };

  const handleCleanText = (event) => {
		setSearchText('');
  };

  const handleSearchText = (event) => {
		history.push(`/results?movieName=${searchText}`);
  };

  return (
    <Container className={classes.container}>
      <Card className={classes.cardContainer}>
        <Grid container className={classes.titleGridContainer}>
          <Grid>
            <Typography className={classes.title}> Bienvenido!</Typography>
          </Grid>
          <Grid>
            <MovieIcon className={classes.movieIcon} />
          </Grid>
        </Grid>
        <TextField
          value={searchText}
          placeholder="Buscar..."
          onChange={handleSearchTextChange}
          className={classes.textFieldSearch}
        ></TextField>
        <Grid className={classes.buttonsContainer}>
          <Button variant="contained" onClick={handleCleanText}>
            Limpiar
          </Button>

          <Button
            variant="contained"
            color="primary"
            size="large"
            className={classes.searchButton}
            onClick={handleSearchText}
          >
            Buscar
          </Button>
        </Grid>
      </Card>
    </Container>
  );
};
