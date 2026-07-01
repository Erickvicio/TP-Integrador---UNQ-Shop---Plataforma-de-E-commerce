package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import main.Direccion;

import main.Fidelizacion;
import main.GeneradordeFactura;
import main.MailSender;
import main.NotificadorDeMail;
import main.Pedido;
import main.Subsistema;

class QuitarSubsistemaDePedidoTest {
	Pedido pedido;
	Subsistema notificadorDeMail;
	Subsistema fidelizacion;
	Subsistema generadorDeFactura;
	MailSender mailsender;
	Direccion dir;
	

	@BeforeEach
	void setUp() throws Exception {
		dir=mock(Direccion.class);
		pedido= new Pedido("lautaro@gmail.com",dir);
		mailsender= mock(MailSender.class);
		notificadorDeMail = new NotificadorDeMail(mailsender);
		fidelizacion = new Fidelizacion();
		generadorDeFactura=new GeneradordeFactura();
	}

	@Test
	void test() {
		pedido.agregarSubsitema(notificadorDeMail);
		pedido.agregarSubsitema(fidelizacion);
		pedido.agregarSubsitema(generadorDeFactura);

		pedido.quitarSubsistma(fidelizacion);
		pedido.quitarSubsistma(notificadorDeMail);
		assertEquals(pedido.getSubsistemas().size(), 1);
	}

}
