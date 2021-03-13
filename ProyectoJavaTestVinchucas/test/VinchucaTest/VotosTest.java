package VinchucaTest;
import Vinchucas.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;








public class VotosTest {

    private Voto voto1;
    private Voto voto2;
    private Participante participante1;
    private Participante participante2;
    private ConocimientoBasico basico;
    private LocalDate fecha;

    @Before
    public void setUp() throws Exception {
        participante1 = new Participante("Juan", basico);
        participante2 = new Participante("Jose", basico);
        voto1 = new Voto(participante1, "Vinchuca");
    }

    @Test
    public void testVerificacionConstructor() {
        voto2 = new Voto(participante2, "Phtia-Chinche");
        assertEquals("Phtia-Chinche", voto2.getTipoDeVoto());
        assertEquals(participante2, voto2.getParticipante());
    }



    @Test
    public void testGetParticipante() {
        assertEquals(participante1, voto1.getParticipante());
    }

    @Test
    public void testGetTipoDeVerificacion() {
        assertEquals("Vinchuca", voto1.getTipoDeVoto());
    }

    @Test
    public void testElVotoDeJuanEsValidoMensual() {
    	
    	assertTrue(voto1.esValidaMensual());
    }
    
    

}
