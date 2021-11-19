package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import constants.Fixture;
import domain.repositorios.RepositorioHogares;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicio.HogarTransitoServicio;

public class RepositorioHogaresTest extends Fixture {
  private HogarTransitoServicio hogares;
  private RepositorioHogares repositorioHogares;
  private Rescate rescatePerro;
  private Rescate rescateGato;

  @BeforeEach
  public void setup() throws KeyManagementException, NoSuchAlgorithmException {

    new Asociacion("Asosiacion1", ubicacionAsociacion1());
    new Asociacion("Asosiacion2", ubicacionAsociacion2());
    new Asociacion("Asosiacion3", ubicacionAsociacion3());

    hogares = new HogarTransitoServicio();
    repositorioHogares = new RepositorioHogares(hogares.hogaresDisponibles());
    rescatePerro = new RescateSinChapa(
        new ArrayList<>(Collections.singletonList("unaFoto")),
        "Canino macho, color negro, raza caniche",
        ubicacion1(),
        LocalDate.of(2021, 5, 4),
        mascota1(),
        rescatista()
    );
    rescateGato = new RescateSinChapa(
        new ArrayList<>(Collections.singletonList("unaFoto")),
        "Gata peluda, gordita y gris",
        ubicacion1(),
        LocalDate.of(2021, 5, 4),
        mascota2(),
        rescatista()
    );
  }

  @Test
  public void puedoEncontrarHogaresParaPerrosChicos() {
    rescatePerro.getMascota().setCaracteristicas(
        Arrays.asList(
            respuestaTamanioChico(),
            respuestaComportamientoConNiniosManso(),
            respuestaComportamientoConNiniosManso()
        )
    );

    assertEquals(13, repositorioHogares.getHogaresParaRescate(rescatePerro, 500).size());
  }

  @Test
  public void puedoEncontrarHogaresParaPerrosGrandes() {
    rescatePerro.getMascota().setCaracteristicas(Arrays.asList(
        respuestaContexturaDelgada(),
        respuestaComportamientoConNiniosManso(),
        respuestaTamanioGrande()
    ));

    assertEquals(11, repositorioHogares.getHogaresParaRescate(rescatePerro, 500).size());
  }

  @Test
  public void puedoEncontrarHogaresParaPerrosAgresivos() {
    rescatePerro.getMascota().setCaracteristicas(
        Arrays.asList(
            respuestaContexturaDelgada(),
            respuestaComportamientoConNiniosAgresivo(),
            respuestaTamanioGrande()
        )
    );

    assertEquals(11, repositorioHogares.getHogaresParaRescate(rescatePerro, 500).size());
  }

  @Test
  public void noPuedoEncontrarHogaresParaPerrosMuyCercaDeMiUbicacion() {
    rescatePerro.getMascota().setCaracteristicas(
        Arrays.asList(
            respuestaContexturaDelgada(),
            respuestaComportamientoConNiniosManso(),
            respuestaTamanioGrande()
        )
    );

    assertEquals(0, repositorioHogares.getHogaresParaRescate(rescatePerro, 1).size());
  }

  @Test
  public void noPuedoEncontrarHogaresParaPerrosSiEstoyLejos() {
    Ubicacion alaska = new Ubicacion(
        "Sin calle",
        null,
        null,
        BigDecimal.valueOf(64.2008),
        BigDecimal.valueOf(-149.4937)
    );
    RescateSinChapa rescateAlaska = new RescateSinChapa(
        new ArrayList<>(Collections.singletonList("unaFoto")),
        "Perro peludo",
        alaska,
        LocalDate.of(2021, 5, 4),
        mascota1(),
        rescatista()
    );
    rescateAlaska.getMascota().setCaracteristicas(
        Arrays.asList(
            respuestaContexturaDelgada(),
            respuestaComportamientoConNiniosManso(),
            respuestaTamanioGrande()
        )
    );

    assertEquals(0, repositorioHogares.getHogaresParaRescate(rescateAlaska, 10000).size());
  }

  @Test
  public void puedoEncontrarHogaresParaGatosTranquilos() {
    rescateGato.getMascota().setCaracteristicas(
        Arrays.asList(
            respuestaComportamientoTranquilo(),
            respuestaTamanioChico()
        )
    );

    assertEquals(14, repositorioHogares.getHogaresParaRescate(rescateGato, 500).size());
  }

  @Test
  public void puedoEncontrarHogaresParaGatosAlborotados() {
    rescateGato.getMascota().setCaracteristicas(
        Arrays.asList(
            respuestaComportamientoAlborotado(),
            respuestaTamanioChico()
        )
    );

    assertEquals(14, repositorioHogares.getHogaresParaRescate(rescateGato, 500).size());
  }

  @Test
  public void noPuedoEncontrarHogaresParaGatosMuyCercaDeMiUbicacion() {
    rescateGato.getMascota().setCaracteristicas(
        Arrays.asList(
            respuestaComportamientoTranquilo(),
            respuestaTamanioChico()
        )
    );

    assertEquals(0, repositorioHogares.getHogaresParaRescate(rescateGato, 1).size());
  }
}
