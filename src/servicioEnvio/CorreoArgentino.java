package servicioEnvio;

import main.Direccion;

public interface CorreoArgentino {
	
	public float estimarEnvio(float peso, Direccion direccionEnvio);
}
