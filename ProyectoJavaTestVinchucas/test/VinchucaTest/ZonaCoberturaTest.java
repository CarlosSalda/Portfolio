package VinchucaTest;
import Vinchucas.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;







    public class ZonaCoberturaTest {
	ZonaDeCobertura zonaCobertura;
	Ubicacion ubicacion;
	SitioWeb sWebMock;
	SitioWeb sWebReal;
	FuncionalidadExterna funcMuestra;
	FuncionalidadExterna funcVerificacion;
	Muestra muestra;
	
	
	@Before
	public void Setup() {
		ubicacion = mock(Ubicacion.class);
		zonaCobertura = new ZonaDeCobertura("Zona Sur",ubicacion,50);
		
		sWebMock = mock(SitioWeb.class);
		sWebReal = new SitioWeb();
		muestra  = mock(Muestra.class);
	}
	
	
	@Test
	public void testGetSetEpicentro() {
		Ubicacion ubicacion2 = Mockito.mock(Ubicacion.class);
		zonaCobertura.setEpicentro(ubicacion2);
		assertEquals(zonaCobertura.getEpicentro(),ubicacion2);
	}
	
	
	@Test
	public void testGetSetNombre() {
		zonaCobertura.setNombre("Florencio Varela");
		assertEquals(zonaCobertura.getNombre(),"Florencio Varela");
	}
	


	@Test
	public void testGetSetRadio() {
		zonaCobertura.setRadio(100);
		float radioTest = 100;
		assertEquals(zonaCobertura.getRadio(), radioTest,0);
	}
	
	@Test
	public void testRegistrarse() {
		Organizacion organizacion;
		organizacion = mock(Organizacion.class);
		zonaCobertura.registrarse(organizacion);
		assertEquals(zonaCobertura.getReceptorEvento().size(),1);
	}
	@Test
	public void testDesRegistrarse() {
		Organizacion organizacion;
		organizacion = mock(Organizacion.class);
		zonaCobertura.registrarse(organizacion);
		zonaCobertura.sacarRegistro(organizacion);
		assertEquals(zonaCobertura.getReceptorEvento().size(),0);
	}
	
	@Test
	public void testEpicentrosSolapantes() {

		Ubicacion ubicacion2 = mock(Ubicacion.class);
		Ubicacion ubicacion3 = mock(Ubicacion.class);
		when(ubicacion.getLatitud()).thenReturn(50.0);
		when(ubicacion.getLongitud()).thenReturn(50.0);
		when(ubicacion2.getLatitud()).thenReturn(49.0);
		when(ubicacion2.getLongitud()).thenReturn(49.0);
		when(ubicacion3.getLatitud()).thenReturn(49.0);
		when(ubicacion3.getLongitud()).thenReturn(51.0);
		ZonaDeCobertura zonaCobertura2 = new ZonaDeCobertura("Quilmes", ubicacion2, 90);
		ZonaDeCobertura zonaCobertura3 = new ZonaDeCobertura("Berazategui", ubicacion3, 150);
		
		sWebReal.agregarZona(zonaCobertura);
		sWebReal.agregarZona(zonaCobertura2);
		sWebReal.agregarZona(zonaCobertura3);
		
		assertEquals(zonaCobertura.solapantes(sWebReal.getZonas()).size(),2);
	}
	
	@Test
	public void testNotificarMuestraAOrganizaciones() {
		Organizacion UNICEF = mock(Organizacion.class);
		Organizacion Caritas = mock(Organizacion.class);

		zonaCobertura.registrarse(UNICEF);
		zonaCobertura.registrarse(Caritas);
		zonaCobertura.notificarMuestra(muestra);
		
		verify(UNICEF).updateMuestra(zonaCobertura, muestra);
		verify(Caritas).updateMuestra(zonaCobertura, muestra);
	}
	
	@Test
	public void testNotificarVerificacionAOrganizaciones() {
		Organizacion UNICEF = mock(Organizacion.class);
		Organizacion Caritas = mock(Organizacion.class);

		zonaCobertura.registrarse(UNICEF);
		zonaCobertura.registrarse(Caritas);
		zonaCobertura.notificarVerificacion(muestra);
		
		verify(UNICEF).updateVerificacion(zonaCobertura, muestra);
		verify(Caritas).updateVerificacion(zonaCobertura, muestra);
	}
}
