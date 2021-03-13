package VinchucaTest;
import Vinchucas.*;
import Vinchucas.Organizacion.TipoOrganizacion;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;




import static org.junit.Assert.assertEquals;


public class OrganizacionTest {
	private Ubicacion ubicacion;
	private Organizacion organizacion;
    private Ubicacion ubicacionTest;
    private FuncionalidadExterna funcMuestra;
    private FuncionalidadExterna funcVerificacion;
    	
    
    @Before
    public void setUp(){
        ubicacion = mock(Ubicacion.class);
  
        organizacion = new Organizacion(ubicacion, Organizacion.TipoOrganizacion.Asistencia,10, funcMuestra, funcVerificacion);
        ubicacionTest = mock(Ubicacion.class);
    }

    @Test
    public void elTipoDeOrganizacionEsAsistencia() {
    	assertEquals(organizacion.getTipoDeOrganizacion(), Organizacion.TipoOrganizacion.Asistencia);
    }
    
    @Test
    public void laOrganizacionTiene10empleados() {
    	assertEquals(organizacion.getCantidadDeEmpleados(), 10);
    }
    

    
    @Test
    public void testFuncExtMuestra() {
    	FuncionalidadExterna funcionalidadExt = new FuncExternaClass();
    	FuncionalidadExterna funcExternaSpy = Mockito.spy(funcionalidadExt);
    	Ubicacion ubicacion = Mockito.mock(Ubicacion.class);
    	Muestra muestra = Mockito.mock(Muestra.class);
    	ZonaDeCobertura zonaCobertura = new ZonaDeCobertura("Varela", ubicacion, 100);
    	Organizacion organizacion = new Organizacion(ubicacion, TipoOrganizacion.Asistencia, 10, funcExternaSpy, funcionalidadExt);
    	organizacion.updateMuestra(zonaCobertura, muestra);
    	Mockito.verify(funcExternaSpy).NuevoEvento(organizacion, zonaCobertura, muestra);
    }
    
    @Test
    public void testFuncExtVerificacion() {
    	FuncionalidadExterna funcionalidadExt = new FuncExternaClass();
    	FuncionalidadExterna funcExternaSpy = Mockito.spy(funcionalidadExt);
    	Ubicacion ubicacion = Mockito.mock(Ubicacion.class);
    	Muestra muestra = Mockito.mock(Muestra.class);
    	ZonaDeCobertura zonaCobertura = new ZonaDeCobertura("Varela", ubicacion, 100);
    	Organizacion organizacion = new Organizacion(ubicacion, TipoOrganizacion.Asistencia, 10, funcionalidadExt, funcExternaSpy);
    	organizacion.updateVerificacion(zonaCobertura, muestra);
    	Mockito.verify(funcExternaSpy).NuevoEvento(organizacion, zonaCobertura, muestra);
    }
}

