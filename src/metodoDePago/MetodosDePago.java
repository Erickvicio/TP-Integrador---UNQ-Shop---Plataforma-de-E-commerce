package metodoDePago;


public abstract class MetodosDePago {

    /**
     * Coordina el flujo completo del pago.
     * Ejecuta los 4 pasos en orden. Si alguno lanza una excepción, 
     * el método se corta y la excepción sube para que la maneje el sistema.
     * Si los 4 se ejecutan con éxito, se confirma el pago devolviendo true.
     */
    public void iniciarProcesoPago(float cantidad) {
        
    	
        this.validarDatos();
        this.reservarFondos();
        this.ejecutarTransicion(cantidad);
        this.notificarResultado();
        
    }
    
    // Métodos abstractos: cambie las firmas para que no devuelvan un boolean básico,
    // ya que ahora su éxito se mide en si terminan normalmente o lanzan excepción.
    public abstract void validarDatos();
    public abstract void reservarFondos();
    public abstract void ejecutarTransicion(float cantidad);
    public abstract void notificarResultado();
    
    public abstract void reembolsar(float cantidad);
}
