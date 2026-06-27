package main;

public class Cupon {
    public int numeroDeCupon;
    public int precioPagado;

    // Constructor vacío por defecto
    public Cupon() {}

    // Constructor cómodo para pruebas
    public Cupon(int numeroDeCupon, int precioPagado) {
        this.numeroDeCupon = numeroDeCupon;
        this.precioPagado = precioPagado;
    }

    // Getters y Setters
    public int getNumeroDeCupon() { return numeroDeCupon; }
    public void setNumeroDeCupon(int numeroDeCupon) { this.numeroDeCupon = numeroDeCupon; }

    public int getPrecioPagado() { return precioPagado; }
    public void setPrecioPagado(int precioPagado) { this.precioPagado = precioPagado; }
}