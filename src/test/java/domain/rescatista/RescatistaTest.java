package domain.rescatista;

import static domain.exception.Mensajes.NOT_NULO;
import static domain.usuario.TipoIdentificacion.DNI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.mascotaEncontrada.MascotaEncontrada;
import domain.usuario.Contacto;

public class RescatistaTest {
    Rescatista donRescatista;
    Rescatista doniaRescatista;
    MascotaEncontrada caniche;
    Contacto contactoDon;
    Image fotoPerro1Encontrado;
    List<Image> fotos1;

    @BeforeEach

    public void setup() throws IOException {
        fotoPerro1Encontrado = ImageIO.read(new File("resources/images/perroEncontrado1.jpg"));
        fotos1 = new ArrayList<>(Collections.singletonList(fotoPerro1Encontrado));
        caniche = new MascotaEncontrada(fotos1 ,"Canino macho, color negro, raza caniche",
        "Estacion de Tren 'El Rescate'", LocalDate.of(2021, 4, 4));
        contactoDon = new Contacto("don", "Rescatista", "1234", "donrescatista@rescatista.com", "El Rescatista 123");
        donRescatista = new Rescatista("Don", "Rescatista", DNI,
                "99999999", LocalDate.of(1995, 5, 5),
                caniche,
                contactoDon);
        doniaRescatista = new Rescatista("Donia", "Rescatista", DNI,
                "88888888",  LocalDate.of(1995, 4, 4),
                new MascotaEncontrada(fotos1,"Canino hembra, color blanco, raza caniche",
                "Estacion de Tren 'El Rescatado'", LocalDate.of(2021, 5, 4)),
                new Contacto("donia", "Rescatista", "1234", "doniarescatista@rescatista.com", "La Rescatista 321"));
    }

    @Test
    void puedoCrearUnRescatista(){assertNotNull(donRescatista);}

    @Test
    void noPuedoCrearUnRescatistaSiFaltanUnParametroRequerido(){
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Rescatista("Don", null, DNI,
                    "99999999", LocalDate.of(1995, 5, 5),
                    new MascotaEncontrada(fotos1 ,"Canino macho, color negro, raza caniche",
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
