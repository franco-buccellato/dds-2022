package domain.rescatista;

import domain.mascotaEncontrada.MascotaEncontrada;
import domain.usuario.Contacto;
import rescatista.Rescatista;

import static constants.Mensajes.NOT_NULO;
import static constants.TipoIdentificacion.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RescatistaTest {

    Rescatista donRescatista;
    Rescatista doniaRescatista;
    Image fotoPerro1Encontrado;
    List<Image> fotos1;

    @BeforeEach

    public void setup() throws IOException {

        fotoPerro1Encontrado = ImageIO.read(new File("resources/images/perroEncontrado1.jpg"));
        fotos1 = new ArrayList<>(Collections.singletonList(fotoPerro1Encontrado));

        donRescatista = new Rescatista("Don", "Rescatista", DNI,
                "99999999", LocalDate.of(1995, 5, 5),
                new MascotaEncontrada(fotos1 ,"Canino macho, color negro, raza caniche",
                        "Estacion de Tren 'El Rescate'", LocalDate.of(2021, 4, 4)),
                new Contacto("don", "Rescatista", "1234", "donrescatista@rescatista.com", "El Rescatista 123"));
        doniaRescatista = new Rescatista("Donia", "Rescatista", DNI,
                "88888888",  LocalDate.of(1995, 4, 4),
                new MascotaEncontrada(fotos1,"Canino hembra, color blanco, raza caniche",
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
                    new MascotaEncontrada(fotos1 ,"Canino macho, color negro, raza caniche",
                            "Estacion de Tren 'El Rescate'", LocalDate.of(2021, 4, 4)),
                    new Contacto("don", "Rescatista", "1234", "donrescatista@rescatista.com", "El Rescatista 123"));
        });
        assertEquals(NOT_NULO.mensaje("apellido"), exception.getMessage());
    }

    

}
