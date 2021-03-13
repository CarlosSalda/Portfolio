package Vinchucas;

public abstract class NivelConocimiento {
	

	public abstract String getNivel(); 
	
	public abstract void recalcularNivel(Participante participante);

	public abstract Boolean puedeVerificar(Muestra muestraAct);
	
	
}
