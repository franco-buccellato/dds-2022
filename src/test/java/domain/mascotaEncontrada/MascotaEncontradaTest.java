package domain.mascotaEncontrada;

import domain.mascota.Mascota;
import domain.usuario.Contacto;
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

import static constants.Mensajes.NOT_NULO;
import static constants.TipoIdentificacion.DNI;
import static org.junit.jupiter.api.Assertions.*;

public class MascotaEncontradaTest {

    MascotaEncontrada unaMascotaEncontrada;
    Image fotoPerro1Encontrado;
    List<Image> fotos1;

    @BeforeEach
    public void setup() throws IOException {

        fotoPerro1Encontrado = ImageIO.read(new File("resources/images/perroEncontrado1.jpg"));
        fotos1 = new ArrayList<>(Collections.singletonList(fotoPerro1Encontrado));
        unaMascotaEncontrada = new MascotaEncontrada(fotos1, "Canino macho, color negro, raza caniche",
                "Estacion de Tren 'El Rescatado'", LocalDate.of(2021, 5, 4));
    }

    @Test
    void puedoCrearUnaMascotaEncontrada(){ assertNotNull(unaMascotaEncontrada); }

    @Test
    void noPuedoCrearUnaMascotaSinFotos(){
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new MascotaEncontrada(null, "Canino macho, color negro, raza caniche",
                    "Estacion de Tren 'El Rescatado'", LocalDate.of(2021, 5, 4));
        });
        assertEquals(NOT_NULO.mensaje("fotos"), exception.getMessage());
    }

    @Test
    void noPuedoCrearUnaMascotaSinDescripcion(){
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new MascotaEncontrada(fotos1, null,
                    "Estacion de Tren 'El Rescatado'", LocalDate.of(2021, 5, 4));
        });
        assertEquals(NOT_NULO.mensaje("descripcion"), exception.getMessage());
    }

    @Test
    void noPuedoCrearUnaMascotaSinLugarDeEncuentro(){
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new MascotaEncontrada(fotos1, "Canino macho, color negro, raza caniche",
                    null, LocalDate.of(2021, 5, 4));
        });
        assertEquals(NOT_NULO.mensaje("lugarEncuentro"), exception.getMessage());
    }

    @Test
    void noPuedoCrearUnaMascotaSinFecha(){
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new MascotaEncontrada(fotos1, "Canino macho, color negro, raza caniche",
                    "Estacion de Tren 'El Rescatado'", null);
        });
        assertEquals(NOT_NULO.mensaje("fecha"), exception.getMessage());
    }

}