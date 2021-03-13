package Vinchucas;


import java.util.List;
import java.util.stream.Collectors;



public class Ubicacion {
	private double longitud;
	private double latitud;
	
	public Ubicacion(double longitud, double latitud) {
		this.longitud = longitud;
		this.latitud = latitud;
	}
	
	public double getLongitud() {
		return longitud;
	}
	
	public double getLatitud() {
		return latitud;
	}
	
	
	/**
	 * Retorna la distancia entre una ubicacion a otra ubicacion pasada por parametro
	 * @param ubicacion
	 * @return double
	 */
	public double distanciaAUbicacion(Ubicacion ubicacion) {  
        double radioTierra = 6371;
        double dLat = Math.toRadians(ubicacion.getLatitud() - this.getLatitud());  
        double dLng = Math.toRadians(ubicacion.getLongitud() - this.getLongitud());  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(this.getLatitud())) * Math.cos(Math.toRadians(ubicacion.getLatitud()));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  
        return Math.ceil(distancia);  
	}
	
	/**
	 * Dada una distancia y una ubicacion, denota  si la distancia es menor a la distancia entre la ubicacion
	 * y la ubicacion dada por parametro
	 * @param distancia
	 * @param ubicacion
	 * @return boolean
	 */
	public boolean esCercana(double distancia, Ubicacion ubicacion) {
		return this.distanciaAUbicacion(ubicacion) <= distancia;
	}
	

	
	/**
	 * Dada una ubicacion , una lista de ubicaciones y una distancia. 
	 * Retorna aquellas ubicaciones que estan cercanas a la ubicacion dada en la distancia dada
	 * @param ubicaciones
	 * @param distancia
	 * @param ubicacion
	 * @return
	 */
	
	public List<Ubicacion> ubicacionesAMenosDeXKilometrosDeOtraUbicacion(List<Ubicacion> ubicaciones, double distancia, Ubicacion ubicacion){
		return ubicaciones.stream().filter(ubicacionActual -> ubicacionActual.esCercana(distancia, ubicacion)).collect(Collectors.toList());
	}


}

