package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransferenciaBancaria extends MetodosDePago{

    private int cvu;
    private String alias;
    private List<Object> comprobantesTransferencia;
    
    private ApiTransferenciaBancaria apiTransferenciaBancaria;
     
    public TransferenciaBancaria(ApiTransferenciaBancaria apiTransferenciaBancaria) {
        this.apiTransferenciaBancaria = apiTransferenciaBancaria;
        this.comprobantesTransferencia = new ArrayList<>();
    }
    
    public void validarDatos() {
    	this.validarCvu();
        this.validarAlias();
        
        boolean esValidoApi = this.apiTransferenciaBancaria.validarDatos(this.cvu, this.alias);
        
        if (!esValidoApi) {
            throw new RuntimeException("Error: La API externa rechazó la transfencia Bancaria.");
        }
    }
    
    private void validarCvu(){
    	if (this.cvu == 0) {
            throw new RuntimeException("Error: El cvu/Cbu es nulo.");
        }
    }
    private void validarAlias(){
    	if (this.alias == "") {
            throw new RuntimeException("Error: El alias es inválido.");
        }
    }
        
    public void reservarFondos() {
    	
    }

    
    public void ejecutarTransicion() {
    	
    	this.apiTransferenciaBancaria.transferenciaInmediataOProgramada();
    }

    
    public void notificarResultado() {
    	Object nuevoComprobante = this.apiTransferenciaBancaria.generarCupon();
        this.comprobantesTransferencia.add(nuevoComprobante);
    }

    public List<Object> getComprobantesTransferencia() {
        return this.comprobantesTransferencia;
    }

 // Setters para los Tests
    public void setcvu(int cvu) { this.cvu = cvu; }
    public void setAlias(String alias) { this.alias = alias; }

}