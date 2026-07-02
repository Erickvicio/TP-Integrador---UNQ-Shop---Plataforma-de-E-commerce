package metodoDePago;

public class Ticket {
	
	public int numeroTicket;
	public double precioPagado;

	public Ticket(int numeroTicket, double precioPagado) {
		this.numeroTicket = numeroTicket;
		this.precioPagado = precioPagado;
	}

	public Ticket() {
		// TODO Auto-generated constructor stub
	}

	public void setNumeroDeTicket(int numeroTicket) {
		this.numeroTicket = numeroTicket;
	}

	public void setPrecioPagado(double precioPagado) {
		this.precioPagado = precioPagado;
	} 

	public Integer getNumeroDeTicket() {
		return numeroTicket;
	}

	public double getPrecioPagado() {
		return precioPagado;
	}

}
