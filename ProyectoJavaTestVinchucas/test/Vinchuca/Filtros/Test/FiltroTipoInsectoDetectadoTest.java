package Vinchuca.Filtros.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Vinchucas.*;
import Vinchucas.Filtros.FiltroTipoInsectoDetectado;

class FiltroTipoInsectoDetectadoTest {
	
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
	private Voto voto2;
	private LocalDate fechaCreacionMuestra;

	@BeforeEach
	public void Setup() {
		basico =  new ConocimientoBasico();
		experto =  new ConocimientoExperto();
		
		participante = new Participante("Pedro", basico);
		participante2 = new Participante("Juan", experto);
		participante3 = new Participante("Juan Sosa", experto);
		participante4 = new Participante("Juan Pedro", basico);
		participante = new Participante("Pedro", basico);
		
		
		opinionDelEmisor= new Voto(participante, "Vinchuca");
		
		sitioWeb = new SitioWeb();
		sitioWeb.registrarUsuario(participante);
		sitioWeb.registrarUsuario(participante2);
		
		fechaObtencionMuestra = LocalDate.now();
		fechaCreacionMuestra = LocalDate.now();
		
		muestra = new Muestra(opinionDelEmisor, "fotoVinchuca.jpg", ubicacion, participante, fechaObtencionMuestra, fechaCreacionMuestra);
		
	
	
		ubicacion = mock(Ubicacion.class);

		sitioWeb.agregar(muestra);
		
		
	}
	@Test
	void seBuscaMuestraConResultadoActualDeChincheFoliada() {
		
		FiltroTipoInsectoDetectado filtroTipoInsecto = new FiltroTipoInsectoDetectado("Chinche Foliada");
		
		
		assertEquals(sitioWeb.filtrarMuestras(filtroTipoInsecto).size(), 0);	
	}
	
	
	@Test
	void seBuscaMuestraConResultadoActualDeVinchuca() {
		
		FiltroTipoInsectoDetectado filtroTipoInsecto = new FiltroTipoInsectoDetectado("Vinchuca");
		
		voto = new Voto(participante2, "Vinchuca");
		muestra.votar(voto, participante2);
		//participante2.verificarMuestra(voto, muestra);
		
		assertEquals(sitioWeb.filtrarMuestras(filtroTipoInsecto).size(), 1);	
	}
	

	@Test
	void seBuscaMuestraConResultadoActualDeImagenPocoClara() {
		
		FiltroTipoInsectoDetectado filtroTipoInsecto = new FiltroTipoInsectoDetectado("Imagen poco clara");
		
		
		voto2 = new Voto(participante4,"Vinchuca");
		muestra.votar(voto2, participante4);
		//participante4.verificarMuestra(voto2, muestra);
		
		
		voto = new Voto(participante3, "Imagen poco clara");
		muestra.votar(voto, participante3);
		//participante3.verificarMuestra(voto, muestra);

		
		assertEquals(participante3.getNivelParticipante().getClass(), basico.getClass());
		//assertEquals(sitioWeb.filtrarMuestras(filtroTipoInsecto).size(), 1);	
		//assertEquals(sitioWeb.filtrarMuestras(filtroTipoInsecto).contains(muestra), true);
	}
	
	
}
