package Vinchucas.Filtros;

import Vinchucas.Muestra;

public class FiltroCompuestoOR extends FiltroCompuesto {
	
	public FiltroCompuestoOR(FiltroBusqueda filtro1, FiltroBusqueda filtro2) {
		super(filtro1, filtro2);
	}
	
	public Boolean filtrar(Muestra muestra) {
		return this.filtro1.filtrar(muestra) || this.filtro2.filtrar(muestra);

	}


} 
