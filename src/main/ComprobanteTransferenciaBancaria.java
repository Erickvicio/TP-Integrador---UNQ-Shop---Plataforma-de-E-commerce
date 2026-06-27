package main;

public class ComprobanteTransferenciaBancaria {
    public int numeroOperacion;

    // Constructor vacío por defecto
    public ComprobanteTransferenciaBancaria() {}

    // Constructor cómodo para pruebas
    public ComprobanteTransferenciaBancaria(int numeroOperacion) {
        this.numeroOperacion = numeroOperacion;
    }

    // Getter y Setter
    public int getNumeroOperacion() { return numeroOperacion; }
    public void setNumeroOperacion(int numeroOperacion) { this.numeroOperacion = numeroOperacion; }
}