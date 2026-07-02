package notificacionesDePedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cicloDeVidaPedido.Entregado;
import cicloDeVidaPedido.Estado;
import cicloDeVidaPedido.Pedido;
import main.Direccion;
import notificacionesDePedido.Fidelizacion;
import notificacionesDePedido.GeneradordeFactura;
import notificacionesDePedido.MailSender;
import notificacionesDePedido.NotificadorDeMail;
import notificacionesDePedido.Subsistema;

class SubsistemasEnEstadoEntregadoTest {
	Pedido pedido;
	Estado entregado;
	Subsistema notificadorDeMail;
	Subsistema fidelizacion;
	Subsistema generadorDeFactura;
	MailSender mailsender;
	Direccion dir;

	@BeforeEach
	void setUp() throws Exception {
		dir=mock(Direccion.class);
		pedido= new Pedido("carlos@gmail.com",dir, null);
		entregado= new Entregado(pedido);
		mailsender= mock(MailSender.class);
		notificadorDeMail = new NotificadorDeMail(mailsender);
		fidelizacion = new Fidelizacion();
		generadorDeFactura=new GeneradordeFactura();
	}

	@Test
	void test() {
		pedido.setEstado(entregado);
		pedido.agregarSubsitema(notificadorDeMail);
		pedido.agregarSubsitema(fidelizacion);
		pedido.agregarSubsitema(generadorDeFactura);
		pedido.notificarSubsitemas();
		
		//se agrego un ArchivoAdjunto mail y un comprobante fiscal  por que son los unicos subsistemas que reaccionan ante el estado Enviado
		assertEquals(pedido.getAdjuntos().size(),2);
		verify(mailsender).enviarMail("carlos@gmail.com", "Mail de cambio de Estado", "te informamos que tu pedido cambio de estado",null);
	}

}
