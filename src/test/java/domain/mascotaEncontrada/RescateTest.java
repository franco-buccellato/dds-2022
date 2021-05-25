package domain.mascotaEncontrada;

import domain.Rescate;
import domain.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static domain.exception.Mensajes.NOT_NULO;
import static org.junit.jupiter.api.Assertions.*;

public class RescateTest{

    Rescate unaRescate;
    Image fotoPerro1Encontrado;
    List<Image> fotos1;

    @BeforeEach
    public void setup() throws IOException {

        fotoPerro1Encontrado = ImageIO.read(new File("resources/images/perroEncontrado1.jpg"));
        fotos1 = new ArrayList<>(Collections.singletonList(fotoPerro1Encontrado));
        unaRescate = new Rescate(fotos1, "Canino macho, color negro, raza caniche",
            new Ubicacion("El Rescatista 123", "1417", "CABA", BigDecimal.valueOf(56.54684), BigDecimal.valueOf(56.54684)), LocalDate.of(2021, 5, 4));
    }

    @Test
    void puedoCrearUnaMascotaEncontrada(){ assertNotNull(unaRescate); }

    @Test
    void noPuedoCrearUnaMascotaSinFotos(){
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Rescate(null, "Canino macho, color negro, raza caniche",
                new Ubicacion("El Rescatista 123", "1417", "CABA", BigDecimal.valueOf(56.54684), BigDecimal.valueOf(56.54684)), LocalDate.of(2021, 5, 4));
        });
        assertEquals(NOT_NULO.mensaje("fotos"), exception.getMessage());
    }

    @Test
    void noPuedoCrearUnaMascotaSinDescripcion(){
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Rescate(fotos1, null,
                new Ubicacion("El Rescatista 123", "1417", "CABA", BigDecimal.valueOf(56.54684), BigDecimal.valueOf(56.54684)), LocalDate.of(2021, 5, 4));
        });
        assertEquals(NOT_NULO.mensaje("descripcion"), exception.getMessage());
    }

    @Test
    void noPuedoCrearUnaMascotaSinLugarDeEncuentro(){
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Rescate(fotos1, "Canino macho, color negro, raza caniche",
                    null, LocalDate.of(2021, 5, 4));
        });
        assertEquals(NOT_NULO.mensaje("lugarEncuentro"), exception.getMessage());
    }

    @Test
    void noPuedoCrearUnaMascotaSinFecha(){
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            new Rescate(fotos1, "Canino macho, color negro, raza caniche",
                new Ubicacion("El Rescatista 123", "1417", "CABA", BigDecimal.valueOf(56.54684), BigDecimal.valueOf(56.54684)), null);
        });
        assertEquals(NOT_NULO.mensaje("fecha"), exception.getMessage());
    }

}