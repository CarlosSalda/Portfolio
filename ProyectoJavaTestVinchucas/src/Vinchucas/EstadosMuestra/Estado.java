package Vinchucas.EstadosMuestra;

import Vinchucas.Muestra;
import Vinchucas.Participante;
import Vinchucas.Voto;

public abstract class Estado {

	public abstract void votarSiPuede(Muestra muestra, Participante participante, Voto voto);

	public abstract void actualizar(Muestra muestra);

	public abstract String getNombreEstado();
	


}

