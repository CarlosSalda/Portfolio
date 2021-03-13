package Vinchucas.Filtros;

import Vinchucas.Muestra;

public abstract class FiltroCompuesto extends FiltroBusqueda{
	FiltroBusqueda filtro1;
	FiltroBusqueda filtro2;
	
	
	public FiltroCompuesto(FiltroBusqueda filtro1, FiltroBusqueda filtro2 ) {
		this.filtro1 = filtro1;
		this.filtro2 = filtro2;
	}


	@Override
	public abstract Boolean filtrar(Muestra muestra);
}
