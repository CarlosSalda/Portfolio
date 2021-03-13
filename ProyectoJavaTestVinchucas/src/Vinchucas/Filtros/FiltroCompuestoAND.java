package Vinchucas.Filtros;

import Vinchucas.Muestra;

public class FiltroCompuestoAND extends FiltroCompuesto {

	public FiltroCompuestoAND(FiltroBusqueda filtro1, FiltroBusqueda filtro2) {
		super(filtro1, filtro2);
	}
	
	
	public Boolean filtrar(Muestra muestra) {
		return this.filtro1.filtrar(muestra) && this.filtro2.filtrar(muestra);

	}

	
	

}
