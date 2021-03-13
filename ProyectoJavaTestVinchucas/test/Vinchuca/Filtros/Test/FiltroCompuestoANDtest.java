package Vinchuca.Filtros.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Vinchucas.ConocimientoBasico;
import Vinchucas.ConocimientoExperto;
import Vinchucas.Muestra;
import Vinchucas.Participante;
import Vinchucas.SitioWeb;
import Vinchucas.Ubicacion;
import Vinchucas.Voto;
import Vinchucas.Filtros.FiltroCompuestoAND;
import Vinchucas.Filtros.FiltroCompuestoOR;
import Vinchucas.Filtros.FiltroFechaCreacion;
import Vinchucas.Filtros.FiltroTipoInsectoDetectado;

class FiltroCompuestoANDtest {
	private Participante participante;
	private Participante participante2;
	private Participante participante3;
	private Participante participante4;
	private SitioWeb sitioWeb;
	private Muestra muestra;
	private Ubicacion ubicacion;
	private LocalDate fechaObtencionMuestra;
	private ConocimientoBasico basico;
	private ConocimientoExperto experto;
	private Voto voto;
	private Voto opinionDelEmisor;
	private LocalDate fechaCreacionMuestra;


	@BeforeEach
	public void Setup() {
		
		basico =  new ConocimientoBasico();
		experto =  new ConocimientoExperto();
		

		participante = new Participante("Pedro", basico);
		participante2 = new Participante("Juan", experto);
		participante3 = new Participante("Juan Sosa", experto);
		participante4 = new Participante("Juan Pedro", basico);
		
		opinionDelEmisor = new Voto(participante, "Vinchuca");
		
		sitioWeb = new SitioWeb();
		sitioWeb.registrarUsuario(participante);
		
		fechaObtencionMuestra = LocalDate.now();
		fechaCreacionMuestra = LocalDate.now();
		muestra = new Muestra(opinionDelEmisor, "fotoVinchuca.jpg", ubicacion, participante, fechaObtencionMuestra, fechaCreacionMuestra);
		

	
		ubicacion = mock(Ubicacion.class);

		sitioWeb.agregar(muestra);
		
		
	}
	
	
	@Test
	void filtrarPorFechaDeCreacionDeHoyANDTipoInsectoDetectadoChincheFoliada() {
		LocalDate fechaHoy = LocalDate.now();
		
		FiltroFechaCreacion filtroFechaHoy = new FiltroFechaCreacion(fechaHoy); 
		
				
		FiltroTipoInsectoDetectado filtroTipo = new FiltroTipoInsectoDetectado("Chinche Foliada");
		
		FiltroCompuestoAND filtroCompuesto = new FiltroCompuestoAND(filtroFechaHoy, filtroTipo);
		
		assertEquals(sitioWeb.filtrarMuestras(filtroCompuesto).contains(muestra), false);
		
	}
	
	@Test
	void filtrarPorFechaDeCreacionDeHoyANDTipoInsectoDetectadoVinchuca() {
		LocalDate fechaHoy = LocalDate.now();
		
		FiltroFechaCreacion filtroFechaHoy = new FiltroFechaCreacion(fechaHoy);
		
				
		FiltroTipoInsectoDetectado filtroTipo = new FiltroTipoInsectoDetectado("Vinchuca");
		
		FiltroCompuestoAND filtroCompuesto = new FiltroCompuestoAND(filtroFechaHoy, filtroTipo);
		
		assertEquals(sitioWeb.filtrarMuestras(filtroCompuesto).contains(muestra), true);
		
	}

}
