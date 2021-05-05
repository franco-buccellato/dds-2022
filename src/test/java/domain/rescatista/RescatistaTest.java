package domain.rescatista;

import Rescatista.Rescatista;

import static constants.Mensajes.NOT_NULO;
import static constants.TipoIdentificacion.*;
import static org.junit.jupiter.api.Assertions.*;

import domain.usuario.Persona;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

public class RescatistaTest {

    Rescatista donRescatista;
    Rescatista doniaRescatista;

    @BeforeEach
    public void setup() throws IOException {
        donRescatista = new Rescatista("Don", "Rescatista", DNI,
                "99999999", LocalDate.of(1995, 5, 5) );
        doniaRescatista = new Rescatista("Donia", "Rescatista", DNI,
                "88888888",  LocalDate.of(1995, 4, 4));
    }

    @Test
    void puedoCrearUnRescatista(){assertNotNull(donRescatista);}

    @Test
    void noPuedoCrearUnRescatistaSiFaltanUnParametroRequerido(){
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Rescatista("Don", null, DNI,
                    "99999999", LocalDate.of(1995, 5, 5) );
        });
        assertEquals(NOT_NULO.mensaje("apellido"), exception.getMessage());
    }

    

}
