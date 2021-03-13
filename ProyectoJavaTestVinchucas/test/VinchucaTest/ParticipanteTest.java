package VinchucaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Vinchucas.ConocimientoBasico;
import Vinchucas.Muestra;
import Vinchucas.Participante;
import Vinchucas.SitioWeb;
import Vinchucas.Ubicacion;
import Vinchucas.Voto;

class ParticipanteTest {
	
	private Participante participante1;
	private Participante participante2;
	private SitioWeb sitioWeb1;
	private ConocimientoBasico basico;
	private Muestra muestra1;
	private Ubicacion ubicacion1;
	private Voto opinionDelQUeLaEnvio;
	private Voto opinionDelParticipante2;
	private LocalDate fechaTest;
	private LocalDate fechaCreacionMuestra;

	@BeforeEach
	void setUp() {
		basico = new ConocimientoBasico();
		participante1 = new Participante("Juan", basico);
		
		participante2 = new Participante("Jose", basico);
		
		sitioWeb1 = new SitioWeb();
		fechaCreacionMuestra = LocalDate.now();
		sitioWeb1.registrarUsuario(participante1);
		
		opinionDelQUeLaEnvio = new Voto(participante1, "Chinche Foliada");
		opinionDelParticipante2 = new Voto(participante2, "Chinche Foliada");
		
		muestra1 = new Muestra(opinionDelQUeLaEnvio, "chincheFoliada.jpg", ubicacion1, participante1, fechaTest , fechaCreacionMuestra);	
	}

	@Test
	public void testParticipante() {
		assertEquals("Juan", participante1.getNickName());
		assertEquals(basico, participante1.getNivelParticipante());

	}

	@Test
	public void testEnviarMuestra() {
		sitioWeb1.agregar(muestra1);
		assertTrue(sitioWeb1.getMuestras().contains(muestra1));
	}

	@Test
	public void testGetNickName() {
		assertEquals("Juan", participante1.getNickName());
	}

	@Test
	public void testSetNickName() {
		participante1.setNickName("Jose");
		assertEquals("Jose", participante1.getNickName());
	}

	@Test
	public void testGetMuestrasEnviadas() {	
		sitioWeb1.agregar(muestra1);
		assertTrue(participante1.getMuestrasEnviadas().contains(muestra1));
	}

	@Test
	public void testGetNivelParticipante() {
		assertEquals(basico, participante1.getNivelParticipante());
	}

	@Test
	public void testSetNivelParticipante() {
		participante1.setNivelParticipante(basico);
		assertEquals(basico, participante1.getNivelParticipante());
	}

}