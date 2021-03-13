package Vinchuca.Filtros.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Vinchucas.ConocimientoBasico;
import Vinchucas.ConocimientoEspecialista;
import Vinchucas.Muestra;
import Vinchucas.Participante;
import Vinchucas.SitioWeb;
import Vinchucas.Ubicacion;
import Vinchucas.Voto;
import Vinchucas.Filtros.FiltroVerificadas;
import Vinchucas.Filtros.FiltroVotadas;

class FiltroVerificadasTest {

	private SitioWeb sitioWeb;
	private LocalDate fechaHoy;
	private LocalDate fechaObtencionMuestra;
	private Participante participante;
	private Participante participante2;
	private Participante participante3;
	private Muestra muestra;
	private ConocimientoBasico basico;
	private ConocimientoEspecialista especialista;
	private FiltroVerificadas filtroVerificadas;
	private Ubicacion ubicacion;
	private Voto voto;
	private Voto voto2;
	private Voto opinionDelEmisor;
	private LocalDate fechaCreacionMuestra;
	
	
	@BeforeEach
	public void Setup() {
		basico =  new ConocimientoBasico();
		especialista = new ConocimientoEspecialista();
		participante = new Participante("Juan", basico);
		participante2 = new Participante("Juan1", especialista);
		participante3 = new Participante("Juan2", especialista);
		opinionDelEmisor= new Voto(participante, "Vinchuca");
		sitioWeb = new SitioWeb();
		muestra = new Muestra(opinionDelEmisor, "", ubicacion, participante, fechaObtencionMuestra, fechaCreacionMuestra);
		ubicacion = mock(Ubicacion.class);
	
		fechaObtencionMuestra = LocalDate.of(2020, 5, 27);
		fechaHoy = LocalDate.now();
		fechaCreacionMuestra = LocalDate.now();
	}
	
	
	
	@Test
	void noHayMuestrasVerificadas() {
		sitioWeb.registrarUsuario(participante);
		sitioWeb.agregar(muestra);
		
		filtroVerificadas = new FiltroVerificadas();
		assertEquals(sitioWeb.filtrarMuestras(filtroVerificadas).contains(muestra), false);
	}
	
	@Test
	void siHayMuestrasVerificadas() {
		sitioWeb.registrarUsuario(participante);
		sitioWeb.registrarUsuario(participante2);
		sitioWeb.registrarUsuario(participante3);
		sitioWeb.agregar(muestra);
		
		voto2 = new Voto(participante2,"Vinchuca");
		muestra.votar(voto2, participante2);
		//participante2.verificarMuestra(voto2, muestra);
		
		
		voto = new Voto(participante3, "Vinchuca");
		muestra.votar(voto, participante3);
		//participante3.verificarMuestra(voto, muestra);
		
		
		
		filtroVerificadas = new FiltroVerificadas();
		
		
		assertEquals(sitioWeb.filtrarMuestras(filtroVerificadas).contains(muestra), true);
	}

}
