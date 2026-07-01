package metodoDePago;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import metodoDePago.Ticket;


class TicketTest {

    @Test
    void testGettersYSetters_DeberianDefinirYDevolverLosValoresCorrectamente() {
        // Instancia vacía
        Ticket ticket = new Ticket();

        // Probamos Setters
        ticket.setNumeroDeTicket(111222);
        ticket.setPrecioPagado(4500);

        // Probamos Getters
        assertEquals(111222, ticket.getNumeroDeTicket());
        assertEquals(4500, ticket.getPrecioPagado());
    }

    @Test
    void testConstructorConParametros_DeberiaInicializarLosAtributos() {
        // Probamos el constructor completo
        Ticket ticket = new Ticket(999888, 7500);

        assertEquals(999888, ticket.getNumeroDeTicket());
        assertEquals(7500, ticket.getPrecioPagado());
    }
}