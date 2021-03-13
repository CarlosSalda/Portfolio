package Vinchucas;


import java.util.List;

public class ConocimientoExperto extends NivelConocimiento {


	@Override
	public String getNivel() {
		return "Experto";
	}

	public void recalcularNivel(Participante participante) {

		if ((participante.cantidadVerificacionesMensuales() < 20 && participante.cantidadMuestrasMensuales() < 10)) {
			participante.setNivelParticipante(new ConocimientoBasico());
		}
	}  

	
	
	@Override
	public Boolean puedeVerificar(Muestra muestraAct) {
		return (muestraAct.tipoVerificado().equals("IndeterminadoPorElSistema") || 
				(cantDeExpertos(muestraAct.getVotos()) < 2));
	} 
	 
	public Integer cantDeExpertos(List<Voto> votos) {
		Integer cantVotoExpertos = 0;
		for (Voto voto : votos) {
			if(voto.getNivelAlaHoraDeVotar().getNivel().equals("Experto") || 
			   voto.getNivelAlaHoraDeVotar().getNivel().equals("Especialista")) {
				cantVotoExpertos += 1;
		}
	}
		return cantVotoExpertos;
	} 

}
