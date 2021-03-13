package Vinchucas;


import java.util.List;

public class ConocimientoEspecialista extends NivelConocimiento {


	@Override
	public String getNivel() {
		return "Especialista";
	}
	
	
	public void recalcularNivel(Participante participante) {}
 
	@Override
	public Boolean puedeVerificar(Muestra muestraAct) {
		return (muestraAct.tipoVerificado().equals("IndeterminadoPorElSistema") ||  
				(cantDeExpertos(muestraAct.getVotos()) < 2));
	}
	
	public Integer cantDeExpertos(List<Voto> votos) {
		Integer cantExpertos = 0;
		for (Voto voto : votos) {
			if(voto.getNivelAlaHoraDeVotar().getNivel().equals("Experto") || 
			   voto.getNivelAlaHoraDeVotar().getNivel().equals("Especialista")) {
			cantExpertos += 1;
		}
	}
		return cantExpertos;
	}

}
