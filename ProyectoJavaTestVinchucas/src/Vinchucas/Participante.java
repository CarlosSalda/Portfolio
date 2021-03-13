package Vinchucas;

import java.util.*;
import java.util.stream.Collectors;



public class Participante {
	private String nickName;
	private List<Muestra>  muestrasEnviadas;
	public List<Muestra>  muestraVerificadas;
	
	private NivelConocimiento nivelParticipante;

	
	public Participante(String nick, NivelConocimiento nivelParticipante) {
		this.nickName = nick;
		this.nivelParticipante = nivelParticipante;
		this.muestrasEnviadas = new ArrayList<Muestra>();
		this.muestraVerificadas = new ArrayList<Muestra>();
	
	}
	
	/**
	 *  El participante envia la muestra pasada por parametro al sitioWeb
	 * @param muestra
	 */
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<Muestra> getMuestrasEnviadas() {
		return muestrasEnviadas;
	}

	public List<Muestra> getMuestrasVerificadas() {
		return this.muestraVerificadas;
	}


	public NivelConocimiento getNivelParticipante() {
		return nivelParticipante;
	} 

	public void setNivelParticipante(NivelConocimiento nivelParticipante) {
		this.nivelParticipante = nivelParticipante;
	}

	public List<Muestra> muestrasMensuales() {
		List<Muestra> muestrasMensuales = this.getMuestrasEnviadas().stream().filter(muestra -> muestra.esValidaMensual()).collect(Collectors.toList());
		
		return muestrasMensuales;
	}

	public List<Muestra> verificacionesMensuales() {
		List<Muestra> verificacionesMensuales = this.getMuestrasVerificadas().stream().filter(muestra -> muestra.esValidaMensual()).collect(Collectors.toList());
		
		return verificacionesMensuales;
	}

	public int cantidadVerificacionesMensuales() {
		return this.verificacionesMensuales().size();
	}

	public int cantidadMuestrasMensuales() {
		return this.muestrasMensuales().size();
	}
	

	public void agregarAMuestras(Muestra muestra) {
		this.muestrasEnviadas.add(muestra);
	}
}
