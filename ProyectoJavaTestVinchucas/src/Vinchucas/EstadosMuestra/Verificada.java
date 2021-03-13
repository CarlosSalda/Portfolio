package Vinchucas.EstadosMuestra;

import Vinchucas.Muestra;
import Vinchucas.Participante;
import Vinchucas.Voto;

public class Verificada extends Estado  {

	@Override
	public void votarSiPuede(Muestra muestra, Participante participante, Voto voto) {
		// No hace nada por que al esta ya verificada no puede admitir mas votos 
		
	}

	@Override
	public void actualizar(Muestra muestra) {
		// No hace nada porque una muestra verificada no puede involucionar ya que no se remueven los votos
		
	}

	@Override
	public String getNombreEstado() {
		return ("Verificada");
	}

}
