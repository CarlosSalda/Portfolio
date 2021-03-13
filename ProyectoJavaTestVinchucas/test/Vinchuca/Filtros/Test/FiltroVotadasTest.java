package Vinchuca.Filtros.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import java.time.LocalDate;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import Vinchucas.ConocimientoEspecialista;
import Vinchucas.Muestra;
import Vinchucas.Participante;
import Vinchucas.SitioWeb;
import Vinchucas.Ubicacion;
import Vinchucas.Voto;

import Vinchucas.Filtros.FiltroVotadas;

class FiltroVotadasTest {
	private SitioWeb sitioWeb;

	private LocalDate fechaObtencionMuestra;
	private Participante participante;
	private Participante participante2;
	private Muestra muestra;
	private ConocimientoEspecialista especialista;
	private FiltroVotadas filtroVotadas;
	private Ubicacion ubicacion;
	private LocalDate fechaCreacionMuestra;
	private Voto opinionDelEmisor;
	private Voto votoParticipante2;
	
	
	
	@BeforeEach
	public void Setup() {
		especialista =  new ConocimientoEspecialista();
		
		sitioWeb = new SitioWeb();
		ubicacion = mock(Ubicacion.class);
		fechaObtencionMuestra = LocalDate.of(2020, 5, 27);
	
		fechaCreacionMuestra = LocalDate.now();

		participante = new Participante("Juan", especialista);
		participante2 = new Participante("Pedro", especialista);
		
		opinionDelEmisor= new Voto(participante, "Vinchuca");
		votoParticipante2 = new Voto(participante2, "Vinchuca");
		

		
		muestra = new Muestra(opinionDelEmisor, "", ubicacion, participante, fechaObtencionMuestra, fechaCreacionMuestra);
		


		
		
	}
	
	
	@Test
	void muestrasQueHanSidoVotadas() {
		
		sitioWeb.registrarUsuario(participante);
		sitioWeb.agregar(muestra); 
		
		muestra.votar(votoParticipante2, participante2);
		//participante2.verificarMuestra(votoParticipante2, muestra);
		
		filtroVotadas = new FiltroVotadas();
		
		assertEquals(sitioWeb.filtrarMuestras(filtroVotadas).contains(muestra), true);
	}


}
