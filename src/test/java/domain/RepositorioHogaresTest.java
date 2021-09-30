package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import domain.*;
import domain.repositorios.RepositorioHogares;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.Fixture;
import servicio.HogarTransitoServicio;

public class RepositorioHogaresTest extends Fixture {
  private HogarTransitoServicio hogares;
  private RepositorioHogares repositorioHogares;
  private Rescate rescatePerro;
  private Rescate rescateGato;
  private CaracteristicaChoice vacunas;
  private CaracteristicaChoice contexturas;
  private CaracteristicaChoice comportamientos;
  private CaracteristicaChoice tamanios;

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
    contexturas = contextura();
    comportamientos = comportamientoConNiños();
    tamanios = tamanio();
    vacunas = vacunas();
    Opcion vacunaSeleccionada = (Opcion) vacunas.getOpciones().get(0);
    vacunas.seleccionarOpcion(vacunaSeleccionada, true);
  }

  @Test
  public void puedoEncontrarHogaresParaPerrosChicos() {
    tamanios.seleccionarOpcion(tamanioChico, true);
    contexturas.seleccionarOpcion(contexturaDelgado, true);
    comportamientos.seleccionarOpcion(comportamientoManso, true);
    rescatePerro.getMascota().setCaracteristicas(Arrays.asList(contexturas, comportamientos, tamanios));

    assertEquals( 13, repositorioHogares.getHogaresParaRescate(rescatePerro, 500).size());
  }

  @Test
  public void puedoEncontrarHogaresParaPerrosGrandes() {
    tamanios.seleccionarOpcion(tamanioGrande, true);
    comportamientos.seleccionarOpcion(comportamientoManso, true);
    rescatePerro.getMascota().setCaracteristicas(Arrays.asList(contexturas, comportamientos, tamanios));

    assertEquals( 11, repositorioHogares.getHogaresParaRescate(rescatePerro, 500).size());
  }

  @Test
  public void puedoEncontrarHogaresParaPerrosAgresivos() {
    comportamientos.seleccionarOpcion(comportamientoAgresivo, true);
    tamanios.seleccionarOpcion(tamanioGrande, true);
    rescatePerro.getMascota().setCaracteristicas(Arrays.asList(contexturas, comportamientos, tamanios));

    assertEquals( 11, repositorioHogares.getHogaresParaRescate(rescatePerro, 500).size());
  }

  @Test
  public void noPuedoEncontrarHogaresParaPerrosMuyCercaDeMiUbicacion() {
    tamanios.seleccionarOpcion(tamanioGrande, true);
    comportamientos.seleccionarOpcion(comportamientoManso, true);
    rescatePerro.getMascota().setCaracteristicas(Arrays.asList(contexturas, comportamientos, tamanios));

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
    tamanios.seleccionarOpcion(tamanioGrande, true);
    comportamientos.seleccionarOpcion(comportamientoManso, true);
    rescateAlaska.getMascota().setCaracteristicas(Arrays.asList(contexturas, comportamientos, tamanios));

    assertEquals( 0, repositorioHogares.getHogaresParaRescate(rescateAlaska, 10000).size());
  }

  @Test
  public void puedoEncontrarHogaresParaGatosTranquilos() {
    tamanios.seleccionarOpcion(tamanioChico, true);
    CaracteristicaChoice comportamiento = comportamiento();
    comportamiento.seleccionarOpcion(comportamientoTranquilo, true);
    rescateGato.getMascota().setCaracteristicas(Arrays.asList(comportamiento, tamanios));

    assertEquals( 14, repositorioHogares.getHogaresParaRescate(rescateGato, 500).size());
  }

  @Test
  public void puedoEncontrarHogaresParaGatosAlborotados() {
    CaracteristicaChoice comportamiento = comportamiento();
    comportamiento.seleccionarOpcion(comportamientoAlborotado, true);
    rescateGato.getMascota().setCaracteristicas(Arrays.asList(comportamiento, tamanios));

    assertEquals( 10, repositorioHogares.getHogaresParaRescate(rescateGato, 500).size());
  }

  @Test
  public void noPuedoEncontrarHogaresParaGatosMuyCercaDeMiUbicacion() {
    CaracteristicaChoice comportamiento = comportamiento();
    comportamiento.seleccionarOpcion(comportamientoTranquilo, true);
    tamanios.seleccionarOpcion(tamanioChico, true);
    rescateGato.getMascota().setCaracteristicas(Arrays.asList(comportamiento, tamanios));

    assertEquals( 0, repositorioHogares.getHogaresParaRescate(rescateGato, 1).size());
  }
}
