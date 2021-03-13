package VinchucaTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import Vinchucas.ConocimientoBasico;
import Vinchucas.Muestra;
import Vinchucas.NivelConocimiento;
import Vinchucas.Participante;
import Vinchucas.SitioWeb;
import Vinchucas.Ubicacion;
import Vinchucas.Voto;
import Vinchucas.ZonaDeCobertura;

class SitioWebTest {
	
	private SitioWeb sitioWeb1;
	private Participante participante1;
	private Muestra muestra1;
	private ZonaDeCobertura zonaCobertura1;
	private Voto opinionDelEmisor;
	private LocalDate fecha;
	private LocalDate fechaCreacionMuestra;
	private Ubicacion ubicacion;
	private NivelConocimiento cb;
	
	@BeforeEach
	void setUp() {		
		cb = new ConocimientoBasico();
		sitioWeb1 = new SitioWeb();
		participante1 =new Participante("Juan", cb);
		opinionDelEmisor = new Voto(participante1, "Vinchuca");
		
		
		fecha = LocalDate.now();
		fechaCreacionMuestra = LocalDate.now();
		ubicacion = new Ubicacion(10d, 15d);
		
		muestra1 = new Muestra(opinionDelEmisor, "foto", ubicacion, participante1, fecha, fechaCreacionMuestra);
		zonaCobertura1 = mock(ZonaDeCobertura.class);
	}

	@Test
	void testSitioWeb() {
		assertEquals(0, sitioWeb1.getMuestras().size());
		assertEquals(0, sitioWeb1.getParticipantes().size());
		assertEquals(0, sitioWeb1.getOrganizaciones().size());
		assertEquals(0, sitioWeb1.getZonas().size());
	}

	@Test
	void testRegistrarUsuario() {
		sitioWeb1.registrarUsuario(participante1);
		assertEquals(1, sitioWeb1.getParticipantes().size());
	}

	@Test
	void testAgregarMuestraAlSitioWeb() {
		sitioWeb1.agregar(muestra1);
		assertEquals(1, sitioWeb1.getMuestras().size());
	}

	@Test
	void testAgregarZona() {
		sitioWeb1.agregarZona(zonaCobertura1);
		assertEquals(1, sitioWeb1.getZonas().size());
	}

	
}