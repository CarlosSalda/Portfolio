package Vinchucas.EstadosMuestra;

import java.util.ArrayList;

import Vinchucas.ConocimientoBasico;
import Vinchucas.Muestra;
import Vinchucas.Participante;
import Vinchucas.Voto;

public class Votada extends Estado {

	@Override
	public void votarSiPuede(Muestra muestra, Participante participante, Voto voto) {
		participante.getNivelParticipante().recalcularNivel(participante);
		 if (this.disponibleParaVotar(participante, muestra)){ 
			 muestra.addVoto(voto); 
		 	 participante.muestraVerificadas.add(muestra);  
		
		 }
	}
	
	/**
	 * Retorna un boleando que hace saber si puede votar o no la muestra pasada por parametro
	 * @param muestra
	 * @return boolean
	 */
	protected Boolean disponibleParaVotar(Participante participante, Muestra muestra) {
		//pregunto si el participante envio esta muestra 
		boolean x = !(participante.getMuestrasEnviadas().contains(muestra));
		//pregunto si el participante verifico esta muestra
		boolean y = !(participante.getMuestrasVerificadas().contains(muestra));
		//y pregunto si puede verificar sugun su nivel de conocimiento
		boolean z = participante.getNivelParticipante().puedeVerificar(muestra);
		
		return((x && y) && z);


}

	@Override
	public void actualizar(Muestra muestra) {
		 if (!muestra.tipoVerificado().equals("IndeterminadoPorElSistema") && 
			(cantDeExpertos(muestra.getParticipantesVerificadores()) >= 2)) {
			 		
			 	muestra.setEstado( new Verificada());
		 }
		
	}
	
	
	public Integer cantDeExpertos(ArrayList<Participante> participantes) {
		Integer cantExpertos = 0;
		for (Participante participante : participantes) {
			if(participante.getNivelParticipante().getNivel().equals("Experto") || participante.getNivelParticipante().getNivel().equals("Especialista")) {
			cantExpertos += 1; 
		}
	}
		return cantExpertos; 
 }

	@Override
	public String getNombreEstado() {
		return ("Votada");
	}
}
