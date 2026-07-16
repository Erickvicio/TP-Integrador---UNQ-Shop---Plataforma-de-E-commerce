package metodoDePago;

public class BilleteraVirtual extends MetodosDePago {

    private ApiBilleteraVirtual apiBilletera;

    public BilleteraVirtual(ApiBilleteraVirtual apiBilletera) {
        this.apiBilletera = apiBilletera;
    }

   
    public void validarDatos() {
        boolean tieneSaldo = this.apiBilletera.verificarSaldoSuficiente();
        if (!tieneSaldo) {
            throw new RuntimeException("Error: Saldo insuficiente en la cuenta.");
        }
    }
  
   
    public void reservarFondos() {
        this.apiBilletera.bloquearSaldoHastaConfirmar();
    }

    
    public void ejecutarTransicion(float cantidad) {
        this.apiBilletera.acreditarEnTiempoRealAlVendedor();
    }

    
    public void notificarResultado() {
        this.apiBilletera.enviarNotificacionPush();
    } 
    
    public void reembolsar(float cantidad) {
    	// Falta implementacion por parte de la API
    	this.apiBilletera.reembolsarCantidad(cantidad);
    }
}