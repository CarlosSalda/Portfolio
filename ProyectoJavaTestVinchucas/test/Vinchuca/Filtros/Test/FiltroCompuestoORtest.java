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
import Vinchucas.Filtros.FiltroCompuestoOR;
import Vinchucas.Filtros.FiltroFechaCreacion;
import Vinchucas.Filtros.FiltroTipoInsectoDetectado;

class FiltroCompuestoORtest {
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
		
		ubicacion = mock(Ubicacion.class);
	
		fechaObtencionMuestra = LocalDate.now();
		fechaCreacionMuestra = LocalDate.now();
		muestra = new Muestra(opinionDelEmisor, "fotoVinchuca.jpg", ubicacion, participante
							, fechaObtencionMuestra, fechaCreacionMuestra);
		
		sitioWeb = new SitioWeb();
		sitioWeb.registrarUsuario(participante);
		

		sitioWeb.agregar(muestra);
		
		}
	
	@Test
	void filtrarPorFechaDeCreacionDeAyerORTipoInsectoDetectadoChincheFoliada() {
		LocalDate fechaAyer = LocalDate.now().minusDays(1);
		
		FiltroFechaCreacion filtroFechaAyer = new FiltroFechaCreacion(fechaAyer);	
		FiltroTipoInsectoDetectado filtroTipo = new FiltroTipoInsectoDetectado("Chinche Foliada");
		
		FiltroCompuestoOR filtroCompuesto = new FiltroCompuestoOR(filtroFechaAyer, filtroTipo);
		
		assertEquals(sitioWeb.filtrarMuestras(filtroCompuesto).contains(muestra), false);
		
	}
	
	@Test
	void filtrarPorFechaDeCreacionDeHoyORTipoInsectoDetectadoVinchuca() {
		LocalDate fechaHoy = LocalDate.now();
		
		FiltroFechaCreacion filtroFechaHoy = new FiltroFechaCreacion(fechaHoy);
		
				
		FiltroTipoInsectoDetectado filtroTipo = new FiltroTipoInsectoDetectado("Vinchuca");
		
		FiltroCompuestoOR filtroCompuesto = new FiltroCompuestoOR(filtroFechaHoy, filtroTipo);
		
		assertEquals(sitioWeb.filtrarMuestras(filtroCompuesto).contains(muestra), true);
		
	}

}
