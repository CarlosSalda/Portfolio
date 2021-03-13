package Vinchucas;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ZonaDeCobertura {
	private String nombre;
	
	private Ubicacion epicentro;
	
	private float radio;
	
	List<ReceptorEvento> receptorEvento = new ArrayList<ReceptorEvento>();
	
	//List<Organizacion> organizaciones = new ArrayList<Organizacion>();
	
	public ZonaDeCobertura(String nombre, Ubicacion epicentro, float radio) {
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.radio = radio;
	}
	public void setEpicentro(Ubicacion ubicacion) {
		this.epicentro = ubicacion;
	}
	public Ubicacion getEpicentro() {
		return epicentro;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setRadio(float radio) {
		this.radio = radio;
	}
	public float getRadio() {
		return radio;
	}
	public void registrarse(ReceptorEvento recEvento) {
		receptorEvento.add(recEvento);
	}
	public List<ReceptorEvento> getReceptorEvento() {
		return receptorEvento;
	}
	public void sacarRegistro(ReceptorEvento recEvento) {
		receptorEvento.remove(recEvento);
	}
	
	public List<ZonaDeCobertura> solapantes(List<ZonaDeCobertura> zonas) {
		Stream <ZonaDeCobertura> zonasSolapantes = 	zonas.stream().filter(z -> this.epicentro.distanciaAUbicacion(z.epicentro) < z.getRadio() + this.getRadio() && z!= this );
		return (ArrayList<ZonaDeCobertura>) zonasSolapantes.collect(Collectors.toList());
	}
	
	
	public void notificarMuestra(Muestra muestra) {
		for (ReceptorEvento recEvento : receptorEvento) {
			recEvento.updateMuestra(this, muestra);
		}
	}
	
	public void notificarVerificacion(Muestra muestra) {
		for (ReceptorEvento recEvento : receptorEvento) {
			recEvento.updateVerificacion(this, muestra);
		}
	}
	
}
