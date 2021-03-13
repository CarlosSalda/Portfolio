package Vinchucas.Filtros;

import Vinchucas.Muestra;


public class FiltroVotadas extends FiltroNivelVerificacion {

	
	@Override
	public Boolean filtrar(Muestra muestra) {
		return muestra.getEstado().getNombreEstado().equals("Votada") ;
	}

}
