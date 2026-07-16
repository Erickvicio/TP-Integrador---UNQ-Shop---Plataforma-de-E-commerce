package metodoDePago;

public interface ApiBilleteraVirtual {

    public boolean verificarSaldoSuficiente();

    public void bloquearSaldoHastaConfirmar();

    public void acreditarEnTiempoRealAlVendedor();

    public void enviarNotificacionPush();

	public float reembolsarCantidad(float cantidad);

}
