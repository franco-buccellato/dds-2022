package domain.rescatista;

import domain.mascotaEncontrada.MascotaEncontrada;
import domain.usuario.Contacto;

import static constants.Mensajes.NOT_NULO;
import static constants.TipoIdentificacion.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

public class RescatistaTest {

    Rescatista donRescatista;
    Rescatista doniaRescatista;
    MascotaEncontrada caniche;
    Contacto contactoDon;
    @BeforeEach
    public void setup() throws IOException {
        caniche = new MascotaEncontrada(null ,"Canino macho, color negro, raza caniche",
            "Estacion de Tren 'El Rescate'", LocalDate.of(2021, 4, 4));
        contactoDon = new Contacto("don", "Rescatista", "1234", "donrescatista@rescatista.com", "El Rescatista 123");
        donRescatista = new Rescatista("Don", "Rescatista", DNI,
                "99999999", LocalDate.of(1995, 5, 5), caniche, contactoDon);
        doniaRescatista = new Rescatista("Donia", "Rescatista", DNI,
                "88888888",  LocalDate.of(1995, 4, 4),
                new MascotaEncontrada(null ,"Canino hembra, color blanco, raza caniche",
                "Estacion de Tren 'El Rescatado'", LocalDate.of(2021, 5, 4)),
                new Contacto("don", "Rescatista", "1234", "doniarescatista@rescatista.com", "La Rescatista 321"));
    }

    @Test
    void puedoCrearUnRescatista(){assertNotNull(donRescatista);}

    @Test
    void noPuedoCrearUnRescatistaSiFaltanUnParametroRequerido(){
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Rescatista("Don", null, DNI,
                    "99999999", LocalDate.of(1995, 5, 5),
                    new MascotaEncontrada(null ,"Canino macho, color negro, raza caniche",
                            "Estacion de Tren 'El Rescate'", LocalDate.of(2021, 4, 4)),
                    new Contacto("don", "Rescatista", "1234", "donrescatista@rescatista.com", "El Rescatista 123"));
        });
        assertEquals(NOT_NULO.mensaje("apellido"), exception.getMessage());
    }

    @Test
    void puedoObtenerDatosDeUnRescatista(){
        assertEquals("Don", donRescatista.getNombre());
        assertEquals("Rescatista", donRescatista.getApellido());
        assertEquals(DNI, donRescatista.getTipoIdentificacion());
        assertEquals("99999999", donRescatista.getNumeroIdentificacion());
        assertEquals(caniche, donRescatista.getMascotaEncontrada());
        assertEquals(contactoDon, donRescatista.getContacto());
        assertEquals(LocalDate.of(1995, 5, 5), donRescatista.getFechaNacimiento());
    }
}
