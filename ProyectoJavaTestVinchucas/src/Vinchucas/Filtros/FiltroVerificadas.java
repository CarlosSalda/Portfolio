package Vinchucas.Filtros;

import Vinchucas.Muestra;


public class FiltroVerificadas extends FiltroNivelVerificacion {
		
		
		@Override
		public Boolean filtrar(Muestra muestra) {
			return (muestra.getEstado().getNombreEstado().equals("Verificada"));
		} 
	}


