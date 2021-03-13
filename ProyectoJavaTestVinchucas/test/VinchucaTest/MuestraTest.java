package VinchucaTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Vinchucas.*;

class MuestraTest {
	private Muestra muestra;
	private Muestra muestraMock;
	private Participante participante;
	private Ubicacion ubicacion;
	private SitioWeb sitioWeb;
	private ConocimientoBasico cb;
	private ConocimientoExperto ce;
	private Voto opinionDelEmisor;
	private LocalDate fecha;
	private LocalDate fechaCreacionMuestra;
	
	@BeforeEach
	public void setUp(){
		cb = new ConocimientoBasico();
		ce = new ConocimientoExperto();
		sitioWeb = new SitioWeb();
		ubicacion = new Ubicacion(10d, 15d);
		participante = new Participante("Juan", cb);
		opinionDelEmisor = new Voto(participante, "Vinchuca");
		
		fecha = LocalDate.now();
		fechaCreacionMuestra = LocalDate.now();
		
		muestra = new Muestra(opinionDelEmisor, "foto", ubicacion, participante, fecha, fechaCreacionMuestra);
		muestraMock = mock(Muestra.class);
		
		
		sitioWeb.registrarUsuario(participante);
	}

	
	@Test
	void testElSistemaContieneUnaMuestraEnviadaPorParticipante() {
		//CAMBIAR EL TEST A SITIOWEB TEST
		sitioWeb.agregar(muestra);
		assertTrue(sitioWeb.getMuestras().contains(muestra));
	}
	
	@Test
	void testComprueboSiLosDatosDeLasMuestrasSonAsignadosCorrectamente() {
		
		//CAMBIAR EL TEST A MUESTRA
		assertEquals(participante, muestra.getParticipante());
		assertEquals("Juan", muestra.getParticipante().getNickName());
		assertEquals("Vinchuca", muestra.getOpinionDelEmisor().getTipoDeVoto());
		assertEquals(15d, muestra.getUbicacion().getLatitud());
	}
	
	@Test
	void testParticipanteEsDeConocimientoExpertoPorQueTieneCocimientoDeFormaExterna() {
		
		//CAMBIAR EL TEST A PARTICIPANTE TEST
		participante.getNivelParticipante().recalcularNivel(participante);
		assertTrue("Basico".equals(participante.getNivelParticipante().getNivel()));
	}
	
	@Test
	public void unaMuestraEnviadaHoyTieneFechaDeHoy() {
		LocalDate fechaHoy = LocalDate.now();
		assertEquals(muestra.getFechaCreacionMuestra(), fechaHoy);
	}
	
	@Test
	public void unaMuestraObtenidaTieneFechaDeDosMesesAtrasPeroConFechaDeCreacionDeHoy() {
		LocalDate fechaHoy = LocalDate.now();
		LocalDate fechaDosMesesAntes =  fechaHoy.minusDays(60);
		
		when(muestraMock.getFechaObtencionMuestra()).thenReturn(fechaDosMesesAntes);
		when(muestraMock.getFechaCreacionMuestra()).thenReturn(fechaHoy);
		
		
		assertEquals(muestraMock.getFechaObtencionMuestra(), fechaDosMesesAntes);
		assertEquals(muestraMock.getFechaCreacionMuestra(), fechaHoy);
	}
	
	@Test
	public void unaMuestraEnviadaEsteMesEsValidaMensual() {
		
		assertEquals(muestra.esValidaMensual(), true);
	}
}
