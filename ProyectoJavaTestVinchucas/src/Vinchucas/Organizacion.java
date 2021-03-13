package Vinchucas;

public class Organizacion extends ReceptorEvento{

	public enum TipoOrganizacion {
	    Salud, Educativa, Cultural, Asistencia
	}
	
	private Ubicacion ubicacion;
	private TipoOrganizacion tipo;
	private int cantEmpleados;
	private FuncionalidadExterna funcMuestra;
	private FuncionalidadExterna funcMuestraVerificada;
	
	public Organizacion(Ubicacion ubicacion, TipoOrganizacion tipo, int cantidad, FuncionalidadExterna funcMuestra, FuncionalidadExterna funcVerificaciones) {
		this.ubicacion = ubicacion;
		this.tipo = tipo;
		this.cantEmpleados = cantidad;
		this.funcMuestra = funcMuestra;
		this.funcMuestraVerificada = funcVerificaciones;
	}



	public TipoOrganizacion getTipoDeOrganizacion() {
		return this.tipo;
	}

	public int getCantidadDeEmpleados() {
		return this.cantEmpleados;
	}

	public void updateMuestra(ZonaDeCobertura zona, Muestra muestra) { 
		funcMuestra.NuevoEvento(this, zona, muestra);
	}

	public void updateVerificacion(ZonaDeCobertura zona, Muestra muestra) { 
		funcMuestraVerificada.NuevoEvento(this, zona, muestra);
	}
	
	
}