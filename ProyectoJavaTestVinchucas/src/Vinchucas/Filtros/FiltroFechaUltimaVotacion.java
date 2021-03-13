package Vinchucas.Filtros;

import java.time.LocalDate;


import Vinchucas.Muestra;
import Vinchucas.Voto;

public class FiltroFechaUltimaVotacion extends FiltroBusqueda{
	String operador;
	LocalDate fecha;
	Voto voto;
	
	
	public FiltroFechaUltimaVotacion(LocalDate fecha) {
		//this.operador = operador;
		this.fecha = fecha;
	}

	@Override
	public Boolean filtrar(Muestra muestra) {
		/*Stream<Voto> streamVotos = muestra.getVotos().stream() ;
		switch (this.operador) {
		case "==": 
			streamVotos = streamVotos.filter(voto -> voto.getFechaCreacionvoto().isEqual(fecha));
			break;
		case "<":
			streamVotos = streamVotos.filter(voto -> voto.getFechaCreacionvoto().isBefore(fecha));
			break;
		case ">": 
			streamVotos = streamVotos.filter(voto -> voto.getFechaCreacionvoto().isAfter(fecha));
			break;
		}
		return streamVotos.collect(Collectors.toList()).size() > 0;*/
		Boolean hayElementos= !muestra.getVotos().isEmpty() ;
		
		if (hayElementos) {
			int posicionUltimoVoto = muestra.getVotos().size() - 1;
	
			Voto ultimoVoto = muestra.getVotos().get(posicionUltimoVoto);
			return (ultimoVoto.getFechaCreacionvoto().isAfter(fecha) || ultimoVoto.getFechaCreacionvoto().isEqual(fecha));
			
		}else {
			return (hayElementos);
		}
	}
}
	

	
