package VinchucaTest;
import Vinchucas.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;




public class UbicacionTest {
	private Ubicacion ubicacion1,ubicacion2,ubicacion3;
	private Participante participante;
	private Muestra muestra, muestra1;
	private SitioWeb sitioWeb;
	private ConocimientoBasico cb;
    private LocalDate fechaTest;
    private LocalDate fechaCreacionMuestra;
    private Voto opinionDelEmisor;
    
    @Before
    public void setUp(){
		
		ubicacion1 = new Ubicacion(10d,10d);
		ubicacion2 = new Ubicacion(10d,14d);
		ubicacion3 = new Ubicacion(102d,105d);
		
		cb = new ConocimientoBasico();
	 	participante = new Participante("Juan", cb);
		fechaCreacionMuestra = LocalDate.now();
		opinionDelEmisor = new Voto(participante, "Vinchuca");

    	sitioWeb = new SitioWeb();
		
   
		muestra = new Muestra(opinionDelEmisor, "foto", ubicacion1, participante, fechaTest, fechaCreacionMuestra);
		muestra1 = new Muestra(opinionDelEmisor, "foto", ubicacion2, participante, fechaTest, fechaCreacionMuestra);
		sitioWeb.registrarUsuario(participante);
    }
    


    @Test
    public void testUnaUbicacionAXDistanciaDeOtra(){
    	assertEquals(445d, ubicacion1.distanciaAUbicacion(ubicacion2));
    }
    
    @Test
    public void testUbicacionEsCercanaAOtra() {
    	assertTrue(ubicacion1.esCercana(500d, ubicacion2));
    }
    
    @Test
    public void testMuestraCercanaAOtraMuestra() {
		sitioWeb.agregar(muestra);
		
    	assertEquals(1, sitioWeb.muestrasAXKilometrosDeMuestra( 500, muestra1).size());
    	assertTrue(sitioWeb.muestrasAXKilometrosDeMuestra(500, muestra1).contains(muestra));
    }
    
    @Test
    public void testUbicacionesCercanas() {
    	List<Ubicacion> ubi= new ArrayList<Ubicacion>();
    	ubi.add(ubicacion2);
    	ubi.add(ubicacion3);
    	assertEquals(1, ubicacion1.ubicacionesAMenosDeXKilometrosDeOtraUbicacion(ubi, 500, ubicacion1).size());
    	assertTrue(ubicacion1.ubicacionesAMenosDeXKilometrosDeOtraUbicacion(ubi, 500, ubicacion1).contains(ubicacion2));
    	assertFalse(ubicacion1.ubicacionesAMenosDeXKilometrosDeOtraUbicacion(ubi, 500, ubicacion1).contains(ubicacion3));
    }

}

