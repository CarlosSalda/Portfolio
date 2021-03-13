package Vinchucas.EstadosMuestra;

import Vinchucas.Muestra;
import Vinchucas.Participante;
import Vinchucas.Voto;

public class SinVotos  extends Estado{

	@Override
	public void votarSiPuede(Muestra muestra, Participante participante, Voto voto) {
		 muestra.addVoto(voto); 
	 	 participante.muestraVerificadas.add(muestra);
		
	}

	@Override
	public void actualizar(Muestra muestra) {
		if (muestra.getVotos().size() > 0) {
			muestra.setEstado( new Votada()); 	
		}
		
	}

	@Override
	public String getNombreEstado() {
		
		return ("SinVotos");
	}



}
