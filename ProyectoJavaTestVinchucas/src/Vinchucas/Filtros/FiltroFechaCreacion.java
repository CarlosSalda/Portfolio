package Vinchucas.Filtros;

import java.time.LocalDate;

import Vinchucas.Muestra;

public class FiltroFechaCreacion extends FiltroBusqueda {
	LocalDate fechaDeCreacion;
	
	public FiltroFechaCreacion(LocalDate fecha) {
		this.fechaDeCreacion = fecha;
	}

	@Override
	public Boolean filtrar(Muestra muestra) {
		return(this.fechaDeCreacion.equals(muestra.getFechaCreacionMuestra()));
	}
	

}
