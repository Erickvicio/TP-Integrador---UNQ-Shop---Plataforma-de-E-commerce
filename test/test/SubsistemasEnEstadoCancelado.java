package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Cancelado;
import main.Confirmado;
import main.Direccion;
import main.Estado;
import main.Fidelizacion;
import main.GeneradordeFactura;
import main.MailSender;
import main.NotificadorDeMail;
import main.Pedido;
import main.Subsistema;

class SubsistemasEnEstadoCancelado {
	Pedido pedido;
	Estado cancelado;
	Subsistema notificadorDeMail;
	Subsistema fidelizacion;
	Subsistema generadorDeFactura;
	MailSender mailsender;
	Direccion dir;
	
	@BeforeEach
	void setUp() throws Exception {
		dir=mock(Direccion.class);
		pedido= new Pedido("lautaro@gmail.com",dir);
		cancelado= new Cancelado(pedido);
		mailsender= mock(MailSender.class);
		notificadorDeMail = new NotificadorDeMail(mailsender);
		fidelizacion = new Fidelizacion();
		generadorDeFactura=new GeneradordeFactura();
	}

	@Test
	void test() {
		pedido.setEstado(cancelado);
		pedido.agregarSubsitema(notificadorDeMail);
		pedido.agregarSubsitema(fidelizacion);
		pedido.agregarSubsitema(generadorDeFactura);
		pedido.notificarSubsitemas();
		
		//se agrego un archivo cupon por que solo el subsistema fidelizacion reacciona e el estado En_Preparacion
		assertEquals(pedido.getAdjuntos().size(),1);
	}

}
