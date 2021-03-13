package VinchucaTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Period;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Vinchucas.*;



class ParticipanteExpertoTest {
	
	private Muestra muestra;
	private SitioWeb sitioWeb;
	private NivelConocimiento basico;
	private Participante participante;
	private LocalDate fechaCreacionMuestra;
	private LocalDate fechaCreacionMuestra2;
	private Muestra muestra2;
	private Muestra muestra3;
	private Muestra muestra4;
	private Muestra muestra5;
	private Muestra muestra6;
	private Muestra muestra7;
	private Muestra muestra8;
	private Muestra muestra9;
	private Muestra muestra10;
	private Muestra muestra11;
	private Muestra muestra12;
	private Muestra muestra13;
	private Muestra muestra14;
	private Muestra muestra15;
	private Muestra muestra16;
	private Muestra muestra17;
	private Muestra muestra18;
	private Muestra muestra19;
	private Muestra muestra20;
	private Muestra muestra21;
	private Muestra muestra22;
	private Muestra muestra23;
	private Muestra muestra24;
	private Muestra muestra25;
	private Muestra muestra26;
	private Muestra muestra27;
	private Muestra muestra28;
	private Muestra muestra29;
	private Muestra muestra30;
	private Muestra muestra31;
	private Muestra muestra32;
	private Muestra muestra33;
	private Muestra muestra34;
	private Ubicacion ubicacion;
	private Participante participanteVef;
	private Muestra muestraVef;
	private Muestra muestraVef2;
	private Muestra muestraVef3;
	private Muestra muestraVef4;
	private Muestra muestraVef5;
	private Muestra muestraVef6;
	private Muestra muestraVef7;
	private Muestra muestraVef8;
	private Muestra muestraVef9;
	private Muestra muestraVef10;
	private Muestra muestraVef11;
	private Muestra muestraVef12;
	private Muestra muestraVef13;
	private Muestra muestraVef14;
	private Muestra muestraVef15;
	private Muestra muestraVef16;
	private Muestra muestraVef17;
	private Muestra muestraVef18;
	private Muestra muestraVef19;
	private Muestra muestraVef20;
	private Muestra muestraVef21;
	private LocalDate fechaObtencionMuestra;
	private Voto opinionDelEmisorVef;
	private Voto opinionDelEmisor;
	private Voto opinionDelEmisorParticipante4;
	private Participante participante3;
	private NivelConocimiento especialista;
	private Participante participante4;
	

	@BeforeEach
	void setUp() {
		basico = new ConocimientoBasico();
		especialista = new ConocimientoEspecialista();
		fechaObtencionMuestra = LocalDate.of(2020, 6, 4);
		fechaCreacionMuestra = LocalDate.now();
		fechaCreacionMuestra2 = LocalDate.now();
		
		participante = new Participante("Juan", basico);
		participanteVef = new Participante("Pedro", especialista);
		participante3 = new Participante("Emilio", especialista);
		participante4 = new Participante("Maira", basico);
		
		opinionDelEmisorVef= new Voto(participanteVef, "Vinchuca");
		opinionDelEmisor= new Voto(participante, "Vinchuca");
		opinionDelEmisorParticipante4= new Voto(participante4, "Vinchuca"); 
		
		
		
		muestraVef = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef2 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra2);
		muestraVef3 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef4 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef5 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef6 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef6 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef,fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef7 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef8 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef9 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef10 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef11 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef12 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef13 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef14 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra, fechaCreacionMuestra);
		muestraVef15 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef16 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef17 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef18 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef19 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef20 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);
		muestraVef21 = new Muestra(opinionDelEmisorVef, "foto.jpg", ubicacion, participanteVef, fechaObtencionMuestra , fechaCreacionMuestra);

		
		sitioWeb = new SitioWeb();
	
		sitioWeb.registrarUsuario(participanteVef);
		sitioWeb.registrarUsuario(participante);
		sitioWeb.registrarUsuario(participante3);
		sitioWeb.registrarUsuario(participante4);
		
		muestra = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra2 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra2);
		muestra3 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra4 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra5 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra6 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra6 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante,fechaObtencionMuestra , fechaCreacionMuestra);
		muestra7 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra8 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra9 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra10 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra11 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante4, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra12 = new Muestra(opinionDelEmisorParticipante4, "foto.jpg", ubicacion, participante4, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra13 = new Muestra(opinionDelEmisorParticipante4, "foto.jpg", ubicacion, participante4, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra14 = new Muestra(opinionDelEmisorParticipante4, "foto.jpg", ubicacion, participante4, fechaObtencionMuestra, fechaCreacionMuestra);
		muestra15 = new Muestra(opinionDelEmisorParticipante4, "foto.jpg", ubicacion, participante4, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra16 = new Muestra(opinionDelEmisorParticipante4, "foto.jpg", ubicacion, participante4, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra17 = new Muestra(opinionDelEmisorParticipante4, "foto.jpg", ubicacion, participante4, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra18 = new Muestra(opinionDelEmisorParticipante4, "foto.jpg", ubicacion, participante4, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra19 = new Muestra(opinionDelEmisorParticipante4, "foto.jpg", ubicacion, participante4, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra20 = new Muestra(opinionDelEmisorParticipante4, "foto.jpg", ubicacion, participante4, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra21 = new Muestra(opinionDelEmisorParticipante4, "foto.jpg", ubicacion, participante4, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra22 = new Muestra(opinionDelEmisorParticipante4, "foto.jpg", ubicacion, participante4, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra23 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra24 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra25 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra26 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra27 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra28 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra, fechaCreacionMuestra);
		muestra29 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra30 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra31 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra32 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra33 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		muestra34 = new Muestra(opinionDelEmisor, "foto.jpg", ubicacion, participante, fechaObtencionMuestra , fechaCreacionMuestra);
		
		
		
		
		
	}
	
	
	@Test
	void unParticipanteEnviaUnaMuestraYEsParticipanteBasico() {
		
		sitioWeb.agregar(muestra);
		
		assertEquals(participante.getNivelParticipante().getNivel(), "Basico");
	}
	
	@Test
	void unParticipanteEnviaveinteMuestrasYsigueSiendoParticipanteBasico() {
		sitioWeb.agregar(muestra);
		sitioWeb.agregar(muestra2);
		sitioWeb.agregar(muestra3);
		sitioWeb.agregar(muestra4);
		sitioWeb.agregar(muestra5);
		sitioWeb.agregar(muestra6);
		sitioWeb.agregar(muestra7);
		sitioWeb.agregar(muestra8);
		sitioWeb.agregar(muestra9);
		sitioWeb.agregar(muestra10);
		sitioWeb.agregar(muestra11);
		sitioWeb.agregar(muestra12);
		sitioWeb.agregar(muestra13);
		sitioWeb.agregar(muestra14);
		sitioWeb.agregar(muestra15);
		sitioWeb.agregar(muestra16);
		sitioWeb.agregar(muestra17);
		sitioWeb.agregar(muestra18);
		sitioWeb.agregar(muestra19);
		sitioWeb.agregar(muestra20);
		
		
		
		
		assertEquals(participante.getNivelParticipante().getNivel(), "Basico");
	}
	
	@Test
	void unParticipanteVotaUnaMuestraYEsParticipanteBasico() {
		
		sitioWeb.agregar(muestraVef);
		
		
		Voto voto = new Voto(participante, "Vinchuca" );
		
		//participante.verificarMuestra(voto, muestraVef); (ESTO ES LO MISMO QUE ABAJO)
		muestraVef.votar(voto, participante);
		
		assertEquals(participante.getNivelParticipante().getNivel(), "Basico");
	}
	
	
	@Test
	void unParticipanteVotaVeinteMuestrasYEsParticipanteBasico() {
		
		sitioWeb.agregar(muestra);
		sitioWeb.agregar(muestra2);
		sitioWeb.agregar(muestra3);
		sitioWeb.agregar(muestra4);
		sitioWeb.agregar(muestra5);
		sitioWeb.agregar(muestra6);
		sitioWeb.agregar(muestra7);
		sitioWeb.agregar(muestra8);
		sitioWeb.agregar(muestra9);
		sitioWeb.agregar(muestra10);
		sitioWeb.agregar(muestra11);
		sitioWeb.agregar(muestra12);
		sitioWeb.agregar(muestra13);
		sitioWeb.agregar(muestra14);
		sitioWeb.agregar(muestra15);
		sitioWeb.agregar(muestra16);
		sitioWeb.agregar(muestra17);
		sitioWeb.agregar(muestra18);
		sitioWeb.agregar(muestra19);
		sitioWeb.agregar(muestra20);
		
		
		
		Voto voto = new Voto(participante, "Vinchuca" );
		
		sitioWeb.agregar(muestraVef);
		sitioWeb.agregar(muestraVef2);
		sitioWeb.agregar(muestraVef3);
		sitioWeb.agregar(muestraVef4);
		sitioWeb.agregar(muestraVef5);
		sitioWeb.agregar(muestraVef6);
		sitioWeb.agregar(muestraVef7);
		sitioWeb.agregar(muestraVef8);
		sitioWeb.agregar(muestraVef9);
		sitioWeb.agregar(muestraVef10);
		sitioWeb.agregar(muestraVef11);
		sitioWeb.agregar(muestraVef12);
		sitioWeb.agregar(muestraVef13);
		sitioWeb.agregar(muestraVef14);
		sitioWeb.agregar(muestraVef15);
		sitioWeb.agregar(muestraVef16);
		sitioWeb.agregar(muestraVef17);
		sitioWeb.agregar(muestraVef18);
		sitioWeb.agregar(muestraVef19);
		sitioWeb.agregar(muestraVef20);
		
		
		assertEquals(participante.getNivelParticipante().getNivel(), "Basico");

	}
	
	@Test
	void unParticipanteVotaVeinteMuestrasYenvia10MuestrasYseVuelveParticipanteExperto() {
		
		sitioWeb.agregar(muestraVef);
		sitioWeb.agregar(muestraVef2);
		sitioWeb.agregar(muestraVef3);
		sitioWeb.agregar(muestraVef4);
		sitioWeb.agregar(muestraVef5);
		sitioWeb.agregar(muestraVef6);
		sitioWeb.agregar(muestraVef7);
		sitioWeb.agregar(muestraVef8);
		sitioWeb.agregar(muestraVef9);
		sitioWeb.agregar(muestraVef10);
		sitioWeb.agregar(muestraVef11);
		sitioWeb.agregar(muestraVef12);
		sitioWeb.agregar(muestraVef13);
		sitioWeb.agregar(muestraVef14);
		sitioWeb.agregar(muestraVef15);
		sitioWeb.agregar(muestraVef16);
		sitioWeb.agregar(muestraVef17);
		sitioWeb.agregar(muestraVef18);
		sitioWeb.agregar(muestraVef19);
		sitioWeb.agregar(muestraVef20);
		
		
		Voto voto = new Voto(participante, "Vinchuca" );
		
		
		muestraVef.votar(voto, participante);
		muestraVef2.votar(voto, participante);
		muestraVef3.votar(voto, participante);
		muestraVef4.votar(voto, participante);
		muestraVef5.votar(voto, participante);
		muestraVef6.votar(voto, participante);
		muestraVef7.votar(voto, participante);
		muestraVef8.votar(voto, participante);
		muestraVef9.votar(voto, participante);
		muestraVef10.votar(voto, participante);
		muestraVef11.votar(voto, participante);
		muestraVef12.votar(voto, participante);
		muestraVef13.votar(voto, participante);
		muestraVef14.votar(voto, participante);
		muestraVef15.votar(voto, participante);
		muestraVef16.votar(voto, participante);
		muestraVef17.votar(voto, participante);
		muestraVef18.votar(voto, participante);
		muestraVef19.votar(voto, participante);
		muestraVef20.votar(voto, participante);
		
		
		sitioWeb.agregar(muestra);
		sitioWeb.agregar(muestra2);
		sitioWeb.agregar(muestra3);
		sitioWeb.agregar(muestra4);
		sitioWeb.agregar(muestra5);
		sitioWeb.agregar(muestra6);
		sitioWeb.agregar(muestra7);
		sitioWeb.agregar(muestra8);
		sitioWeb.agregar(muestra9);
		sitioWeb.agregar(muestra10);
		sitioWeb.agregar(muestra11);
		
		
		
		
		assertEquals(participante.getNivelParticipante().getNivel(), "Experto");
	}
	

	@Test
	void testPuedeVotar() {
		
		
		sitioWeb.agregar(muestraVef);
		sitioWeb.agregar(muestraVef2);
		sitioWeb.agregar(muestraVef3);
		sitioWeb.agregar(muestraVef4);
		sitioWeb.agregar(muestraVef5);
		sitioWeb.agregar(muestraVef6);
		sitioWeb.agregar(muestraVef7);
		sitioWeb.agregar(muestraVef8);
		sitioWeb.agregar(muestraVef9);
		sitioWeb.agregar(muestraVef10);
		sitioWeb.agregar(muestraVef11);
		sitioWeb.agregar(muestraVef12);
		sitioWeb.agregar(muestraVef13);
		sitioWeb.agregar(muestraVef14);
		sitioWeb.agregar(muestraVef15);
		sitioWeb.agregar(muestraVef16);
		sitioWeb.agregar(muestraVef17);
		sitioWeb.agregar(muestraVef18);
		sitioWeb.agregar(muestraVef19);
		sitioWeb.agregar(muestraVef20);
		
		Voto voto = new Voto(participante, "Vinchuca" );
		
		
		
		muestraVef.votar(voto, participante);
		muestraVef2.votar(voto, participante);
		muestraVef3.votar(voto, participante);
		muestraVef4.votar(voto, participante);
		muestraVef5.votar(voto, participante);
		muestraVef6.votar(voto, participante);
		muestraVef7.votar(voto, participante);
		muestraVef8.votar(voto, participante);
		muestraVef9.votar(voto, participante);
		muestraVef10.votar(voto, participante);
		muestraVef11.votar(voto, participante);
		muestraVef12.votar(voto, participante);
		muestraVef13.votar(voto, participante);
		muestraVef14.votar(voto, participante);
		muestraVef15.votar(voto, participante);
		muestraVef16.votar(voto, participante);
		muestraVef17.votar(voto, participante);
		muestraVef18.votar(voto, participante);
		muestraVef19.votar(voto, participante);
		muestraVef20.votar(voto, participante);
		
		sitioWeb.agregar(muestra);
		sitioWeb.agregar(muestra2);
		sitioWeb.agregar(muestra3);
		sitioWeb.agregar(muestra4);
		sitioWeb.agregar(muestra5);
		sitioWeb.agregar(muestra6);
		sitioWeb.agregar(muestra7);
		sitioWeb.agregar(muestra8);
		sitioWeb.agregar(muestra9);
		sitioWeb.agregar(muestra10);
	
		
		
		assertEquals(participante.getNivelParticipante().getNivel(), "Experto");
		
		Voto votoEsp= new Voto(participante3, "Vinchuca");
		
		
		
		muestraVef21.votar(votoEsp, participante3);
		muestraVef21.votar(voto, participante);
		
		assertTrue(muestraVef21.getParticipantesVerificadores().contains(participante));
		
	} 
	
	@Test 
	void testQueRompe() {
		//CAMBIAR EL NOMBRE
		
		//Una vez que un participante se vuelve experto, hace que todas sus votaciones sean de valor experto
		// las que sean de anteriores a su evolucion deben dar el valor de conocimiento basico
		
		sitioWeb.agregar(muestraVef);
		sitioWeb.agregar(muestraVef2);
		sitioWeb.agregar(muestraVef3);
		sitioWeb.agregar(muestraVef4);
		sitioWeb.agregar(muestraVef5);
		sitioWeb.agregar(muestraVef6);
		sitioWeb.agregar(muestraVef7);
		sitioWeb.agregar(muestraVef8);
		sitioWeb.agregar(muestraVef9);
		sitioWeb.agregar(muestraVef10);
		sitioWeb.agregar(muestraVef11);
		sitioWeb.agregar(muestraVef12);
		sitioWeb.agregar(muestraVef13);
		sitioWeb.agregar(muestraVef14);
		sitioWeb.agregar(muestraVef15);
		sitioWeb.agregar(muestraVef16);
		sitioWeb.agregar(muestraVef17);
		sitioWeb.agregar(muestraVef18);
		sitioWeb.agregar(muestraVef19);
		sitioWeb.agregar(muestraVef20);
		
		Voto voto = new Voto(participante, "Vinchuca" );
		
		
		
		muestraVef.votar(voto, participante);
		muestraVef2.votar(voto, participante);
		muestraVef3.votar(voto, participante);
		muestraVef4.votar(voto, participante);
		muestraVef5.votar(voto, participante);
		muestraVef6.votar(voto, participante);
		muestraVef7.votar(voto, participante);
		muestraVef8.votar(voto, participante);
		muestraVef9.votar(voto, participante);
		muestraVef10.votar(voto, participante);
		muestraVef11.votar(voto, participante);
		muestraVef12.votar(voto, participante);
		muestraVef13.votar(voto, participante);
		muestraVef14.votar(voto, participante);
		muestraVef15.votar(voto, participante);
		muestraVef16.votar(voto, participante);
		muestraVef17.votar(voto, participante);
		muestraVef18.votar(voto, participante);
		muestraVef19.votar(voto, participante);
		muestraVef20.votar(voto, participante);
		
		
		sitioWeb.agregar(muestra);
		sitioWeb.agregar(muestra2);
		sitioWeb.agregar(muestra3);
		sitioWeb.agregar(muestra4);
		sitioWeb.agregar(muestra5);
		sitioWeb.agregar(muestra6);
		sitioWeb.agregar(muestra7);
		sitioWeb.agregar(muestra8);
		sitioWeb.agregar(muestra9);
		sitioWeb.agregar(muestra10);
		sitioWeb.agregar(muestra11);
		
		
		
		
		assertEquals(participante.getNivelParticipante().getNivel(), "Experto");
		
		
		Voto voto2 = new Voto(participante4, "Vinchuca" );
		
		muestraVef.votar(voto, participante4);
		muestraVef2.votar(voto, participante4);
		muestraVef3.votar(voto, participante4);
		muestraVef4.votar(voto, participante4);
		muestraVef5.votar(voto, participante4);
		muestraVef6.votar(voto, participante4);
		muestraVef7.votar(voto, participante4);
		muestraVef8.votar(voto, participante4);
		muestraVef9.votar(voto, participante4);
		muestraVef10.votar(voto, participante4);
		muestraVef11.votar(voto, participante4);
		muestraVef12.votar(voto, participante4);
		muestraVef13.votar(voto, participante4);
		muestraVef14.votar(voto, participante4);
		muestraVef15.votar(voto, participante4);
		muestraVef16.votar(voto, participante4);
		muestraVef17.votar(voto, participante4);
		muestraVef18.votar(voto, participante4);
		muestraVef19.votar(voto, participante4);
		muestraVef20.votar(voto, participante4);
		
	
		
		
		sitioWeb.agregar(muestra12);
		sitioWeb.agregar(muestra13);
		sitioWeb.agregar(muestra14);
		sitioWeb.agregar(muestra15);
		sitioWeb.agregar(muestra16);
		sitioWeb.agregar(muestra17);
		sitioWeb.agregar(muestra18);
		sitioWeb.agregar(muestra19);
		sitioWeb.agregar(muestra20);
		sitioWeb.agregar(muestra21);
		sitioWeb.agregar(muestra22);
		
		assertEquals(participante4.getNivelParticipante().getNivel(), "Experto");
		assertEquals(participante4.cantidadVerificacionesMensuales(), 20);
	}
	

}
