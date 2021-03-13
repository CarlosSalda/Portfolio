package Vinchuca.Filtros.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import Vinchucas.*;
import Vinchucas.Filtros.FiltroFechaCreacion;
class FiltroFechaCreacionTest {

	
	private SitioWeb sitioWeb;
	private LocalDate fechaHoy;
	private LocalDate fecha20deMayo;
	private LocalDate fechaObtencionMuestra;
	private Participante participante;
	private Muestra muestra;
	private FiltroFechaCreacion filtroFechaCreacion;
	private Ubicacion ubicacion;
	private LocalDate fechaCreacionMuestra;
	private Voto opinionDelemisor;
	private NivelConocimiento basico;

	@BeforeEach
	public void Setup() {
		
		sitioWeb = new SitioWeb();
		basico = new ConocimientoBasico();
		participante = new Participante("Juan", basico);
		
		fechaCreacionMuestra = LocalDate.now();
		opinionDelemisor = new Voto(participante, "Vinchuca");
		
		muestra = new Muestra(opinionDelemisor, "", ubicacion, participante, fechaObtencionMuestra, fechaCreacionMuestra);
		ubicacion = mock(Ubicacion.class);

		fechaObtencionMuestra = LocalDate.of(2020, 5, 27);
		sitioWeb.agregar(muestra); 
		
		
		fechaHoy = LocalDate.now();
		fecha20deMayo = LocalDate.of(2020, 5, 20);
		
	}
	
	@Test
	public void seQuiereFiltrarMuestrasQueTengaFechaDeCreacionElDiaDeHoy() {
	    List<Muestra> muestrasEsperables = new ArrayList<Muestra>();
		
	    muestrasEsperables.add(muestra);
		
	    filtroFechaCreacion = new FiltroFechaCreacion(fechaHoy);
		

		assertEquals(sitioWeb.filtrarMuestras(filtroFechaCreacion), muestrasEsperables);
	}
	
	
	@Test
	public void seQuiereFiltrarMuestrasQueTengaFechaDeCreacionEl20DeMayo() {
	    List<Muestra> muestrasEsperables = new ArrayList<Muestra>();
		
	    filtroFechaCreacion = new FiltroFechaCreacion(fecha20deMayo);
		
	
		assertEquals(sitioWeb.filtrarMuestras(filtroFechaCreacion), muestrasEsperables);
	}
}
