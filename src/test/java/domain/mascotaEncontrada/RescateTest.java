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

public class RescateTest {
  @Test
  void noPuedoCrearUnRescateSinFotos() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Rescate(null, descripcion(), ubicacion(), fecha());
    });
    assertEquals(NOT_NULO.mensaje("fotos"), exception.getMessage());
  }

  @Test
  void noPuedoCrearUnaMascotaSinDescripcion() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Rescate(fotos(), null, ubicacion(), fecha());
    });
    assertEquals(NOT_NULO.mensaje("descripcion"), exception.getMessage());
  }

  @Test
  void noPuedoCrearUnaMascotaSinLugarDeEncuentro() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Rescate(fotos(), descripcion(), null, fecha());
    });
    assertEquals(NOT_NULO.mensaje("lugarEncuentro"), exception.getMessage());
  }

  @Test
  void noPuedoCrearUnaMascotaSinFecha() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> {
      new Rescate(fotos(), descripcion(), ubicacion(), null);
    });
    assertEquals(NOT_NULO.mensaje("fecha"), exception.getMessage());
  }

  protected List<Image> fotos() throws IOException {
    return new ArrayList<>(Collections.singletonList(ImageIO.read(
        new File("resources/images/perroEncontrado1.jpg")
    )));
  }

  protected String descripcion() {
    return "Canino macho, color negro, raza caniche";
  }

  protected LocalDate fecha() {
    return LocalDate.of(2021, 5, 4);
  }

  protected Ubicacion ubicacion() {
    return new Ubicacion(
        "El Rescatista 123",
        "1417",
        "CABA",
        BigDecimal.valueOf(56.54684),
        BigDecimal.valueOf(56.54684)
    );
  }

}