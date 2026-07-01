package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pedido {
	private String correo;
	private ArrayList<Subsistema> subsistemas;
	private ArrayList<ArchivoAdjunto> adjuntos;
	private Map<ItemDeCatalogo, Integer> productos;
    private Estado estado;
    private Direccion dir;

	public Pedido(String correo,Direccion dir) {
		this.correo=correo;
		this.estadoInicial();
		this.subsistemas= new ArrayList<>();
		this.adjuntos= new ArrayList<>();
		this.dir = dir;
        this.productos = new HashMap<>(); 
	}
	
	public void agregarSubsitema(Subsistema susbistema) {
		this.subsistemas.add(susbistema);
	}
	
	public void notificarSubsitemas() {
		this.subsistemas.stream().forEach(s -> s.update(estado));
	}
	
	public void quitarSubsistma(Subsistema subsistema) {
		this.subsistemas.remove(subsistema);
		
	}
	
	public void agregarArchivoAdjunto(ArchivoAdjunto archivo) {
		this.adjuntos.add(archivo);
	}
	
	public ArrayList<Subsistema> getSubsistemas() {
		return subsistemas;
	}

	public String getCorreo() {
		return correo;
	}
	
	private void estadoInicial() {
        this.setEstado(new Borrador(this));
    }

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public ArrayList<ArchivoAdjunto> getAdjuntos() {
		return adjuntos;
	}
}
