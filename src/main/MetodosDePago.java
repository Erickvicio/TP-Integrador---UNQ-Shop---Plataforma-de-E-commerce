package main;

import java.util.Date;

public abstract class MetodosDePago {

    /**
     * Coordina el flujo completo del pago.
     * Ejecuta los 4 pasos en orden. Si alguno lanza una excepción, 
     * el método se corta y la excepción sube para que la maneje el sistema.
     * Si los 4 se ejecutan con éxito, se confirma el pago devolviendo true.
     */
    public void iniciarProcesoPago() {
        
    	
        this.validarDatos();
        this.reservarFondos();
        this.ejecutarTransicion();
        this.notificarResultado();
        
    }
    
    // Métodos abstractos: cambie las firmas para que no devuelvan un boolean básico,
    // ya que ahora su éxito se mide en si terminan normalmente o lanzan excepción.
    public abstract void validarDatos();
    public abstract void reservarFondos();
    public abstract void ejecutarTransicion();
    public abstract void notificarResultado();
}
