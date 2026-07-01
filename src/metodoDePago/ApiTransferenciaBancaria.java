package metodoDePago;

public interface ApiTransferenciaBancaria  {
	
	public boolean validarDatos(int cbu, String alias);
	public boolean transferenciaInmediataOProgramada();
	public ComprobanteTransferenciaBancaria generarCupon();
	
}
