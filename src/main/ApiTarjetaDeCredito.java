package main;
import java.util.Date;


public interface ApiTarjetaDeCredito {
    
    // Método que vamos a verificar en el test
    public boolean validarDatos(int numeros,int cvv, Date vencimiento);
    
    // Los otros métodos que aparecen en tu diagrama
    public boolean preAutorizarBanco();
    public void transferenciaInmediata();
    public Cupon generarCupon();
}
