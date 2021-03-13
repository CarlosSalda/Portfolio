package Vinchuca.Filtros.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Vinchucas.*;
import Vinchucas.Filtros.FiltroFechaUltimaVotacion;

class FiltroFechaultimaVotacion {

	private SitioWeb sitioWeb;
	private LocalDate fechaObtencionMuestra;
	private Participante participante;
	private Participante participante2;
	private Muestra muestra;
	private FiltroFechaUltimaVotacion FiltroFechaUltimaVotacion;
	private LocalDate fechaDeseada;
	private Ubicacion ubicacion;
	private Voto voto;
	private Voto opinionDelEmisor;
	private ConocimientoBasico basico;
	private LocalDate fechaDiezDeAbril;
	private LocalDate fechaCreacionMuestra;

	@BeforeEach
	public void Setup() {
		basico= new ConocimientoBasico();
		participante = new Participante("Juan", basico);
		participante2 = new Participante("Pedro", basico);
		sitioWeb = new SitioWeb();
		opinionDelEmisor= new Voto(participante, "Vinchuca");
		muestra = new Muestra(opinionDelEmisor, "fotoVinchuca.jpg", ubicacion, participante, fechaObtencionMuestra, fechaCreacionMuestra);
		sitioWeb.registrarUsuario(participante2);
		sitioWeb.registrarUsuario(participante);
		voto = new Voto(participante2, "Vinchuca");
	
		fechaCreacionMuestra = LocalDate.now();
		ubicacion = mock(Ubicacion.class);
		fechaDeseada = LocalDate.now();
		fechaDiezDeAbril = LocalDate.of(2020, 4, 10);

		
	}
	
	@Test
	void seBuscaMuestrasConFechaDeVotosDel10DeAbril() {
		sitioWeb.agregar(muestra);
		FiltroFechaUltimaVotacion = new FiltroFechaUltimaVotacion(fechaDiezDeAbril);
		
		muestra.votar(voto, participante2);
		//participante2.verificarMuestra(voto, muestra);
		
		
		assertEquals(1,sitioWeb.filtrarMuestras(FiltroFechaUltimaVotacion).size());
	} 
	
	@Test
	void seBuscaMuestrasConFechaDeVotosDelDiaDeHoy() {
		sitioWeb.agregar(muestra);
		FiltroFechaUltimaVotacion = new FiltroFechaUltimaVotacion(fechaDeseada);
		
		muestra.votar(voto, participante2);
		//participante2.verificarMuestra(voto, muestra);
		
		
		assertEquals(1,sitioWeb.filtrarMuestras(FiltroFechaUltimaVotacion).size());
	}
	
	@Test
	void seBuscaMuestrasConFechaDeVotosDelDiaDeHoyPeroNoHayNingunaVotada() {

		FiltroFechaUltimaVotacion = new FiltroFechaUltimaVotacion(fechaDeseada);
		
		
		
		assertEquals(0,sitioWeb.filtrarMuestras(FiltroFechaUltimaVotacion).size());
	}
	
	
	@Test
	void seBuscaMuestrasConFechaDeVotosDel10DeAbrilDel2021() {
		sitioWeb.agregar(muestra);
		LocalDate fecha = LocalDate.of(2021, 4, 10);
		
		FiltroFechaUltimaVotacion = new FiltroFechaUltimaVotacion(fecha);
		muestra.votar(voto, participante2);
		//participante2.verificarMuestra(voto, muestra);
		
		assertEquals(0,sitioWeb.filtrarMuestras(FiltroFechaUltimaVotacion).size());
	}


}
