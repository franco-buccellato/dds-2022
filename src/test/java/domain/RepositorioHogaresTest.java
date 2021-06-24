package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.Fixture;
import servicio.HogarTransitoServicio;

public class RepositorioHogaresTest extends Fixture {
  private HogarTransitoServicio hogares;
  private RepositorioHogares repositorioHogares;
  private Rescate rescatePerro;
  private Rescate rescateGato;
  private Caracteristica vacunas;
  private Caracteristica contexturaDelgado;
  private Caracteristica comportamientoManso;
  private Caracteristica comportamientoAgresivo;
  private Caracteristica tamanioChico;
  private Caracteristica tamanioGrande;
  private Caracteristica comportamientoTranquilo;

  @BeforeEach
  public void setup() throws KeyManagementException, NoSuchAlgorithmException {

    new Asociacion("Asosiacion1", ubicacionAsociacion1());
    new Asociacion("Asosiacion2", ubicacionAsociacion2());
    new Asociacion("Asosiacion3", ubicacionAsociacion3());

    hogares = new HogarTransitoServicio();
    repositorioHogares = new RepositorioHogares(hogares.hogaresDisponibles());
    rescatePerro =  new RescateSinChapa(
        new ArrayList<>(Collections.singletonList("unaFoto")),
        "Canino macho, color negro, raza caniche",
        ubicacion1(),
        LocalDate.of(2021, 5, 4),
        mascota1(),
        rescatista()
    );
    rescateGato =  new RescateSinChapa(
        new ArrayList<>(Collections.singletonList("unaFoto")),
        "Gata peluda, gordita y gris",
        ubicacion1(),
        LocalDate.of(2021, 5, 4),
        mascota2(),
        rescatista()
    );
    vacunas = vacunas();
    vacunas.getOpciones().get(0).setSeleccionada(true);

    contexturaDelgado = contextura();
    contexturaDelgado.getOpciones().get(0).setSeleccionada(true);

    comportamientoManso = comportamientoConNiños();
    comportamientoManso.getOpciones().get(0).setSeleccionada(true);

    comportamientoAgresivo = comportamientoConNiños();
    comportamientoAgresivo.getOpciones().get(2).setSeleccionada(true);

    comportamientoTranquilo = comportamiento();
    comportamientoTranquilo.getOpciones().get(0).setSeleccionada(true);

    tamanioChico = tamanio();
    tamanioChico.getOpciones().get(2).setSeleccionada(true);

    tamanioGrande = tamanio();
    tamanioGrande.getOpciones().get(0).setSeleccionada(true);

  }

  @Test
  public void puedoEncontrarHogaresParaPerrosChicos() {
    rescatePerro.getMascota().setCaracteristicas(Arrays.asList(contexturaDelgado, comportamientoManso, tamanioChico));
    assertEquals( 14, repositorioHogares.getHogaresParaRescate(rescatePerro, 500).size());
  }

  @Test
  public void puedoEncontrarHogaresParaPerrosGrandes() {
    rescatePerro.getMascota().setCaracteristicas(Arrays.asList(contexturaDelgado, comportamientoManso, tamanioGrande));
    assertEquals( 12, repositorioHogares.getHogaresParaRescate(rescatePerro, 500).size());
  }

  @Test
  public void puedoEncontrarHogaresParaPerrosAgresivos() {
    rescatePerro.getMascota().setCaracteristicas(Arrays.asList(contexturaDelgado, comportamientoAgresivo, tamanioGrande));
    assertEquals( 11, repositorioHogares.getHogaresParaRescate(rescatePerro, 500).size());
  }

  @Test
  public void noPuedoEncontrarHogaresParaPerrosMuyCercaDeMiUbicacion() {
    rescatePerro.getMascota().setCaracteristicas(Arrays.asList(contexturaDelgado, comportamientoAgresivo, tamanioGrande));
    assertEquals( 0, repositorioHogares.getHogaresParaRescate(rescatePerro, 1).size());
  }

  @Test
  public void noPuedoEncontrarHogaresParaPerrosSiEstoyLejos() {
    Ubicacion alaska = new Ubicacion("Sin calle", null, null, BigDecimal.valueOf(64.2008), BigDecimal.valueOf(-149.4937));
    RescateSinChapa rescateAlaska =  new RescateSinChapa(
        new ArrayList<>(Collections.singletonList("unaFoto")),
        "Perro peludo",
        alaska,
        LocalDate.of(2021, 5, 4),
        mascota1(),
        rescatista()
    );

    rescateAlaska.getMascota().setCaracteristicas(Arrays.asList(contexturaDelgado, comportamientoManso, tamanioGrande));
    assertEquals( 0, repositorioHogares.getHogaresParaRescate(rescateAlaska, 10000).size());
  }

  @Test
  public void puedoEncontrarHogaresParaGatosTranquilos() {
    rescateGato.getMascota().setCaracteristicas(Arrays.asList(comportamientoTranquilo, tamanioChico));
    assertEquals( 15, repositorioHogares.getHogaresParaRescate(rescateGato, 500).size());
  }

  @Test
  public void puedoEncontrarHogaresParaGatosAlborotados() {
    CaracteristicaChoice comportamiento = comportamiento();
    comportamiento.getOpciones().get(2).setSeleccionada(true);

    rescateGato.getMascota().setCaracteristicas(Arrays.asList(comportamiento, tamanioGrande));
    assertEquals( 10, repositorioHogares.getHogaresParaRescate(rescateGato, 500).size());
  }

  @Test
  public void noPuedoEncontrarHogaresParaGatosMuyCercaDeMiUbicacion() {
    rescateGato.getMascota().setCaracteristicas(Arrays.asList(comportamientoTranquilo, tamanioChico));
    assertEquals( 0, repositorioHogares.getHogaresParaRescate(rescateGato, 1).size());
  }
}
