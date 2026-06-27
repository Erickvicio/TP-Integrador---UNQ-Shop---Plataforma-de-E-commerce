package main;

public class DatoInvalido extends RuntimeException {
	String mensaje;
	
	public DatoInvalido(String mensaje) {
		super(mensaje);
		
	}
	
}
