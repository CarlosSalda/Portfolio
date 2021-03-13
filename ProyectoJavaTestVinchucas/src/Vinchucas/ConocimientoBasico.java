package Vinchucas;


import java.util.List;

public class ConocimientoBasico extends NivelConocimiento {


	@Override
	public String getNivel() {
		return "Basico";
	}

	public void recalcularNivel(Participante participante) {
			if((participante.cantidadVerificacionesMensuales() >= 20 && participante.cantidadMuestrasMensuales() >= 10)) {
			
			participante.setNivelParticipante(new ConocimientoExperto()); 
			}
	}
	  
	
	@Override
	public Boolean puedeVerificar(Muestra muestraAct) { 
		List<Voto> x = muestraAct.getVotos();
		Boolean validaronExpertos = x.stream().anyMatch(voto -> voto.getNivelAlaHoraDeVotar().getNivel().equals("Experto")
														|| voto.getNivelAlaHoraDeVotar().getNivel().equals("Especialista"));
		
		return !validaronExpertos; 
	}	
	
	     
	

}
