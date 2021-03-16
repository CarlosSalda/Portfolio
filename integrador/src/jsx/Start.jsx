import React from "react";
import { useHistory } from "react-router-dom";
import userSvg from "../images/svg/user.svg";
import exitSvg from "../images/svg/exit.svg";

const Start = () => {
  const history = useHistory();

  const goToHome = () => history.push("/");

  const changeDropMenuClass = (event) => {
    var boton = event.target;
    var divBtnmenu = document.getElementById("btnShow");
    var divDropmenu = document.getElementById("dropdownMenu");

    if (divBtnmenu.className == "btn-group show") {
      boton.ariaExpanded = false;
      divBtnmenu.className = "btn-group";
      divDropmenu.className = "dropdown-menu";
    } else {
      boton.ariaExpanded = true;
      divBtnmenu.className = "btn-group show";
      divDropmenu.className = "dropdown-menu show";
    }
  };

  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-dark" id="mainNavbar">
      

        <div className="btn-group" id="btnShow">
          <button
            type="button"
            className="btn dropdown-toggle"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
            onClick={changeDropMenuClass}
          >
            Primary
          </button>
          <div
            className="dropdown-menu"
            id="dropdownMenu"
            x-placement="bottom-start"
          >
            <a className="dropdown-item" href="#">
              Nueva Factura
            </a>
            <a className="dropdown-item" href="#">
              Listado de Ingresos
            </a>
            <a className="dropdown-item" href="#">
              Busqueda Avanzada
            </a>
          </div>
        </div>

        <img className="logoIoma" onClick={goToHome} alt="logoIoma"/>

        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarColor01"
          aria-controls="navbarColor01"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbarColor01"></div>

        <div className="divIcons">
          <a className="navbar-brand" href="#">
            User <img className="svgUser" src={userSvg} alt="iconoUsuario"/>
          </a>
          <a className="navbar-brand" href="#">
            Salir <img className="svgExit" src={exitSvg} alt="iconoSalida"/>
          </a>
        </div>

        <img className="logoGba" onClick={goToHome} alt="logoGBA"/>
      </nav>
    </>
  );
};

export default Start;
