package notificacionesDePedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cicloDeVidaPedido.En_Preparacion;
import cicloDeVidaPedido.Estado;
import cicloDeVidaPedido.Pedido;
import main.Direccion;
import notificacionesDePedido.Fidelizacion;
import notificacionesDePedido.GeneradordeFactura;
import notificacionesDePedido.MailSender;
import notificacionesDePedido.NotificadorDeMail;
import notificacionesDePedido.Subsistema;

class SubsistemasEnEstadoEn_PreparacionTest {
	
	Pedido pedido;
	Estado enPreparacion;
	Subsistema notificadorDeMail;
	Subsistema fidelizacion;
	Subsistema generadorDeFactura;
	MailSender mailsender;
	Direccion dir;

	@BeforeEach
	void setUp() throws Exception {
		dir=mock(Direccion.class);
		pedido= new Pedido("lautaro@gmail.com",dir);
		enPreparacion= new En_Preparacion(pedido);
		mailsender= mock(MailSender.class);
		notificadorDeMail = new NotificadorDeMail(mailsender);
		fidelizacion = new Fidelizacion();
		generadorDeFactura=new GeneradordeFactura();
	}

	@Test
	void test() {
		pedido.setEstado(enPreparacion);
		pedido.agregarSubsitema(notificadorDeMail);
		pedido.agregarSubsitema(fidelizacion);
		pedido.agregarSubsitema(generadorDeFactura);
		pedido.notificarSubsitemas();
		
		//No se agrego ningun archivo adjunto por que los susbsistemas no reaccionan la estado En_Preparacion
		assertEquals(pedido.getAdjuntos().size(),0);
		
	}

}
