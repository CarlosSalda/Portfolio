package Vinchucas.Filtros;

import Vinchucas.Muestra;

public class FiltroTipoInsectoDetectado extends FiltroBusqueda {
	private String tipoDeInsecto;
	
	public FiltroTipoInsectoDetectado(String tipoDeInsectoDetectado) {
		this.tipoDeInsecto = tipoDeInsectoDetectado;
	}

	@Override
	public Boolean filtrar(Muestra muestra) {
		return (this.tipoDeInsecto == muestra.resultadoActual());
	}

}
