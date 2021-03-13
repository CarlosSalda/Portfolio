package Vinchucas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Vinchucas.Filtros.FiltroBusqueda;

public class SitioWeb {

	private List<Muestra> muestras;
	private List <ZonaDeCobertura> zonas;
	private List<Participante> participantes;
	private List<Organizacion> organizaciones;
	
	
	public SitioWeb () {
		this.muestras= new ArrayList<Muestra>();
		this.participantes = new ArrayList<Participante>();
		this.zonas = new ArrayList<ZonaDeCobertura>();
		this.organizaciones = new ArrayList<Organizacion>();
	}
	
	public List<Muestra> getMuestras(){
		return this.muestras;
	}
	
	/**
	 * Se registra al participante al sitio web asignandole el sitioWeb para que dicho participante pueda ser capaz
	 * de enviar las muestras a tomar o hacer las verificaciones
	 * @param participante
	 */
	public void registrarUsuario(Participante participante) {
		this.participantes.add(participante);
	}



	/**
	 * Se agrega a la lista de muestras guardadas en el sitio web, la muestra pasada por parametro
	 * @param muestra
	 */
	public void agregar(Muestra muestra) {
		Participante emisor = muestra.getParticipante();
		
		this.muestras.add(muestra);
		emisor.agregarAMuestras(muestra);
		emisor.getNivelParticipante().recalcularNivel(emisor);
	}


	
	public void agregarZona(ZonaDeCobertura zonaCobertura) {
		zonas.add(zonaCobertura);
	}

	public List<ZonaDeCobertura> getZonas() {
		return zonas;
	}
	
	
	/**Filtra las muestras dado un filtro de busqueda pasado por parametro y retornas las muestras que cumplen el filtro
	 * @param filtro
	 * @return List<Muestra>
	 */
	public List<Muestra> filtrarMuestras(FiltroBusqueda filtro){
		List <Muestra> muestrasAEntregar = this.muestras.stream().filter(muestra -> filtro.filtrar(muestra)).collect(Collectors.toList());
		return (muestrasAEntregar); 
	}

	public List<Participante> getParticipantes (){
		return this.participantes;
	}

	public List<Organizacion> getOrganizaciones() {
		return this.organizaciones;
	}
	
	/**
	 * Dada una muestra y un radio, retorna las muestras que se encuentras a menos del radio dado por parametro
	 * @param muestra
	 * @param radio
	 * @return List<Muestra>
	 */
	
	public List<Muestra> muestrasAXKilometrosDeMuestra(double radio, Muestra muestra){
		List<Muestra> muestras = this.muestras;
		List<Muestra> muestrasSinEstaMuestra = muestras.stream().filter(muestraActual ->!muestraActual.equals(muestra)).collect(Collectors.toList());
 
		List<Ubicacion> ubicacionesDeLasMuestras = muestrasSinEstaMuestra.stream().map(muestraActual->muestraActual.getUbicacion()).collect(Collectors.toList()); 	
		List<Ubicacion> ubicacionesCercanasAMuestra= muestra.getUbicacion().ubicacionesAMenosDeXKilometrosDeOtraUbicacion(ubicacionesDeLasMuestras, radio, muestra.getUbicacion());
	
		return (muestrasSinEstaMuestra.stream().filter(muestraAc -> 
		ubicacionesCercanasAMuestra.contains(muestraAc.getUbicacion())).collect(Collectors.toList()));	
	}
}
