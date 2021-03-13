package Vinchucas;

import java.time.*;

public class Voto {
	Participante participante;
	String tipoDeVoto;
	LocalDate fechaCreacionVoto;
	NivelConocimiento nivelALaHoraDeVotar;
	
	
	
	public Voto(Participante participante, String tipoDeVoto) {
		this.participante = participante;
		this.tipoDeVoto = tipoDeVoto;
		this.fechaCreacionVoto = LocalDate.now();
		this.nivelALaHoraDeVotar = participante.getNivelParticipante();
	}

	public NivelConocimiento getNivelAlaHoraDeVotar() {
		return this.nivelALaHoraDeVotar;
	}
	
	public Participante getParticipante () {
		return this.participante;
	}
	
	
	public String getTipoDeVoto() {
		return this.tipoDeVoto;
	}

	public boolean esValidaMensual() {
		LocalDate fechaHoy = LocalDate.now();	
		Period tiempoDiferencia = Period.between(this.getFechaCreacionvoto(), fechaHoy);		
		return(tiempoDiferencia.getDays() <= 30);
	}

	public LocalDate getFechaCreacionvoto() {
		return this.fechaCreacionVoto;
	}
	

	
	

}
