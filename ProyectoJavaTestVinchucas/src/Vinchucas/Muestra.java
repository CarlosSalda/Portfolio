package Vinchucas;

import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Vinchucas.EstadosMuestra.Estado;
import Vinchucas.EstadosMuestra.SinVotos;
import Vinchucas.EstadosMuestra.Votada;



public class Muestra {
	
	private Voto opinionDelEmisor;
	private String foto;
	private Ubicacion ubicacion;
	private Participante participante;
	private Map<String, List<Voto>> votos;
	private LocalDate fechaObtencionMuestra;
	private LocalDate fechaCreacionMuestra;
	private Estado estado;
	
	
	public Muestra(Voto opnionDelEmisor, String foto, Ubicacion ubicacion, Participante participante, LocalDate fechaObtencionMuestra, LocalDate fechaCreacionMuestra) {
		this.opinionDelEmisor = opnionDelEmisor;
		this.foto = foto;
		this.ubicacion = ubicacion;
		this.participante = participante;
		this.votos = this.inicializarMapaDeVotos();
		this.fechaObtencionMuestra = fechaObtencionMuestra;
		this.fechaCreacionMuestra = fechaCreacionMuestra;
		this.estado = new SinVotos();
	}
	
	private Map<String, List<Voto>> inicializarMapaDeVotos() {
		Map<String, List<Voto>> mapa = new HashMap<String, List<Voto>>();
		mapa.put("Vinchuca", new ArrayList<Voto>());
		mapa.put("Chinche Foliada", new ArrayList<Voto>());
		mapa.put("Phtia-Chinche", new ArrayList<Voto>());
		mapa.put("Ninguna", new ArrayList<Voto>());
		mapa.put("Imagen poco clara", new ArrayList<Voto>());
		return mapa;
	}
	
	
	public Voto getOpinionDelEmisor() {
		return this.opinionDelEmisor;
	}

	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}


	public Participante getParticipante() {
		return this.participante;
	}


	
	public List<Voto> getVotos(){
		List<Voto> votosTotales = new ArrayList<Voto>();
		for(String key : this.votos.keySet()) {
			votosTotales.addAll(this.votos.get(key));
		}
		return votosTotales;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
		
	}
	
	////////////////////////////////////////////////////////////////
	
	public Estado getEstado() { return this.estado; }
	

	/*REEMPLAZA EL VERIFICAR MUESTRA*/
	public void votar(Voto voto, Participante participante) {
		//Vota segun el estado
		this.getEstado().votarSiPuede(this, participante, voto);
		
		 // Actualizo el estado de la muestra
		 this.getEstado().actualizar(this);
		 
	} 

	
 //////////////////////////////////////////////////////////
	
	public void addVoto(Voto unVoto) {
		List<Voto> votosDeTipo = votos.get(unVoto.getTipoDeVoto());
		votosDeTipo.add(unVoto);
		votos.put(unVoto.getTipoDeVoto(), votosDeTipo);
	}
	
	
	/**
	 * Retorna la cantidad de votos asociados a una clave en el mapa de votos
	 * @param unTipoDeVinchuca
	 * @return 
	 */
	private int cantidadDeVotoDeTipo(String unTipoDeVoto) {
		return this.votos.get(unTipoDeVoto).size();
	}
	
	private String devolverEltipoMasVotado(Map<String, List<Voto>> map ) {
		int cantidadGanadora = this.tipoVinchucaMasVotado();
		List<List<Voto>> tiposGanadores = 
				map.values().stream().filter(listaDeVoto -> listaDeVoto.size() == cantidadGanadora).collect(Collectors.toList());		
		if(tiposGanadores.size() > 1) {
			return "IndeterminadoPorElSistema";
		} else {
			//Doble get 0 por ser una lista de listas, quiero llegar a un voto para sacar el tipo correspondiente al tipo ganador
			return tiposGanadores.get(0).get(0).getTipoDeVoto();
		}  
	}
	
	public String resultadoActual() {  
		//tiene en encuenta la opnion del que la envio
		this.addVoto(opinionDelEmisor);
		String resultadoActual= this.tipoVerificado();
		this.votos.get(opinionDelEmisor.tipoDeVoto).remove(opinionDelEmisor);
		return (resultadoActual);
		
	}
	
	public String tipoVerificado() {
		//no tiene que tener encuenta el que envio
		Boolean hayExpertos= this.getParticipantesVerificadores().stream().anyMatch(p ->
																		p.getNivelParticipante().getNivel() == "Experto" || p.getNivelParticipante().getNivel() == "Especialista");
		Map<String, List<Voto>> mapa = this.votos;
		if (hayExpertos) {
			for(String key : this.votos.keySet()) {
				List<Voto> votosDeTipoNivelExperto = this.votos.get(key).stream().filter(voto -> 
					voto.getParticipante().getNivelParticipante().getNivel() == "Experto" || voto.getParticipante().getNivelParticipante().getNivel() == "Especialista").collect(Collectors.toList());
				mapa.put(key, votosDeTipoNivelExperto);
			}
		}
		return this.devolverEltipoMasVotado(mapa);
		
	}
	
	/* Puede haber empate
	 * */
	private int tipoVinchucaMasVotado() {
		int cantidadGanadora = 0;
		for (String tipoDeVoto: this.votos.keySet()) {	
			cantidadGanadora = Math.max(this.cantidadDeVotoDeTipo(tipoDeVoto), cantidadGanadora);
		}
		return cantidadGanadora;
	}
	
	/**
	 * Retorna todos los participante que verificaron la muestra
	 * @return ArrayList<Participante>
	 */
	public ArrayList<Participante> getParticipantesVerificadores(){
		ArrayList<Participante> participantes = new ArrayList<Participante>();
		for(String key : this.votos.keySet()) {
			List<Participante> participantesVotacionDeTipo = this.getVotadoresDeUnTipo(key);
			participantes.addAll(participantesVotacionDeTipo);
		}
		return participantes;
	}
	
	private List<Participante> getVotadoresDeUnTipo(String tipo) {
		List<Participante> participantes = new ArrayList<Participante>();
		for (Voto voto : this.votos.get(tipo)) {
			participantes.add(voto.getParticipante());
		}
		return participantes;
	}


	public boolean esValidaMensual() {
		LocalDate fechaHoy = LocalDate.now();
		Period tiempoDiferencia = Period.between(this.fechaCreacionMuestra, fechaHoy);	
		return(tiempoDiferencia.getDays() <= 30);
	}

	public LocalDate getFechaObtencionMuestra() {
		return this.fechaObtencionMuestra;
	}

	public LocalDate getFechaCreacionMuestra() {
		return this.fechaCreacionMuestra;
	}


	
	
	
}