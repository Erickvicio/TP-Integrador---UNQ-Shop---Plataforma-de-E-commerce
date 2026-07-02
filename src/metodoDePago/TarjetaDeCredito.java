package metodoDePago;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TarjetaDeCredito extends MetodosDePago {

    private int numero;
    private int cvv;
    private Date vencimiento;
    private ApiTarjetaDeCredito apiTarjeta;
    
    // Nueva lista para guardar los tickets registrados
    private List<Ticket> tickets;

    public TarjetaDeCredito(ApiTarjetaDeCredito apiTarjeta) {
        this.apiTarjeta = apiTarjeta;
        this.tickets = new ArrayList<>(); // Inicializamos la lista vacía
    }

     
    @Override
    public void validarDatos() {
        this.validarVencimiento();
        this.validarCVV();
        this.validarNumeroTarjeta();
        
        boolean esValidoApi = this.apiTarjeta.validarDatos(this.numero, this.cvv, this.vencimiento);
        
        if (!esValidoApi) {
            throw new RuntimeException("Error: La API externa rechazó la tarjeta.");
        }
    }
    
    private void validarVencimiento(){
    	if (this.vencimiento == null) {
            throw new RuntimeException("Error: La fecha de vencimiento es nula.");
        }
    }
    private void validarCVV(){
    	if (this.cvv <= 0) {
            throw new RuntimeException("Error: El código CVV es inválido.");
        }
    }
    private void validarNumeroTarjeta(){
        if (this.numero <= 0) {
            throw new RuntimeException("Error: El número de tarjeta es inválido.");
        }
    }
    
    @Override
    public void reservarFondos() {
    	this.apiTarjeta.preAutorizarBanco();
    }

    @Override
    public void ejecutarTransicion() {
    	this.apiTarjeta.transferenciaInmediata();
    }

    @Override
    public void notificarResultado(){
        // Generamos el cupón desde la API y lo registramos en nuestra lista interna
    	Ticket nuevoCupon = this.apiTarjeta.generarCupon();
        this.tickets.add(nuevoCupon);
    }

    public List<Ticket> getTickets() {
        return this.tickets;
    } 
    
    public void addTicket(Ticket t) {
    	tickets.add(t);
    }
    
    // Setters para los Tests
    public void setNumero(int numero) { this.numero = numero; }
    public void setCvv(int cvv) { this.cvv = cvv; }
    public void setVencimiento(Date vencimiento) { this.vencimiento = vencimiento; }

}