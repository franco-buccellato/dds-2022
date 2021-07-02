package constants;

import static domain.Sexo.HEMBRA;
import static domain.SituacionMascota.*;
import static domain.TipoCaracteristica.*;
import static domain.TipoIdentificacion.DNI;
import static domain.TipoMascota.*;
import static domain.TipoUsuario.VOLUNTARIO;
import static domain.Vinculo.AMISTAD;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import domain.*;

public class Fixture {
  public final Opcion comportamientoTranquilo = new Opcion("Tranquilo");
  public final Opcion comportamientoPacifico = new Opcion("Pacifico");
  public final Opcion comportamientoAlborotado = new Opcion("Alborotado");
  public final Opcion comportamientoAgresivo = new Opcion("Agresivo");
  public final Opcion comportamientoAmistoso = new Opcion("Amistos");
  public final Opcion comportamientoManso = new Opcion("Manso");
  public final Opcion tamanioGrande = new Opcion("Grande");
  public final Opcion tamanioMediano = new Opcion("Mediano");
  public final Opcion tamanioChico = new Opcion("Chico");
  public final Opcion contexturaDelgado = new Opcion("Delgado");
  public final Opcion contexturaNormal = new Opcion("Normal");
  public final Opcion contexturaGordito = new Opcion("Gordito");
  public final Opcion tipoGato = new Opcion("Gato");
  public final Opcion tipoPerro = new Opcion("Perro");

  public Rescatista rescatista() {
    return new Rescatista(datoPersonal(), contacto(), ubicacion1(), mock(Usuario.class));
  }

  public Rescate rescateSinChapa() {
    return new RescateSinChapa(
        Arrays.asList(""),
        "Rescate sin chapa test",
        this.ubicacion1(),
        LocalDate.of(2021, 6, 3),
        this.mascota1(),
        this.rescatista());
  }

  public Rescate rescateConChapa() {
    Duenio duenio = this.duenio();
    Mascota mascota = this.mascota2();
    duenio.addMascota(mascota);
    return new RescateConChapa(
        Arrays.asList(""),
        "Rescate con chapa test",
        this.ubicacion1(),
        LocalDate.of(2021, 6, 3),
        mascota,
        this.rescatista());
  }

  public Voluntario voluntario() {
    return new Voluntario(
      new Usuario(
        "Voluntario1",
        "voluntariocorrecto",
          VOLUNTARIO),
      new Asociacion(
        "Asociacion1",
        ubicacionAsociacion1())
    );
  }

  public DatoPersonal datoPersonal() {
    return new DatoPersonal(
      "Juan",
      "Perez",
        DNI,
      "99919999",
      LocalDate.of(1995, 5, 24)
    );
  }

  public DatoPersonal datoPersonalAdoptante() {
    return new DatoPersonal(
        "Jose",
        "Perez",
        DNI,
        "99919998",
        LocalDate.of(1985, 5, 24)
    );
  }

  public Contacto contacto() {
    MedioNotificacion medioNotificacion = mock(MedioNotificacion.class);
    return new Contacto(
      "Pedro",
      "Gonzalez",
      "494949",
      "pedro@g.com",
      AMISTAD,
      medioNotificacion
    );
  }

  public Ubicacion ubicacion1() {
    return new Ubicacion(
      "El Rescatista 123",
      "1417",
      "CABA",
      BigDecimal.valueOf(-34.607415758394005),
      BigDecimal.valueOf(-58.37020660410098)
    );
  }

  public Ubicacion ubicacion2() {
    return new Ubicacion(
      "El Rescatista 123",
      "1417",
      "CABA",
      BigDecimal.valueOf(-34.607415758394005),
      BigDecimal.valueOf(-58.50000000000000)
    );
  }

  public Ubicacion ubicacion3() {
    return new Ubicacion(
      "El Distinto 999",
      "1417",
      "CABA",
      BigDecimal.valueOf(-34.70000000000000),
      BigDecimal.valueOf(-58.37020660410098)
    );
  }

  public Ubicacion ubicacionAsociacion1() {
    return new Ubicacion(
      "La Asociacion",
      "1",
      "CABA",
      BigDecimal.valueOf(50.54235),
      BigDecimal.valueOf(50.55621)
    );
  }

  public Ubicacion ubicacionAsociacion2() {
    return new Ubicacion(
      "La Asociacion",
      "2",
      "CABA",
      BigDecimal.valueOf(70.12548),
      BigDecimal.valueOf(80.36587)
    );
  }

  public Ubicacion ubicacionAsociacion3() {
    return new Ubicacion(
      "La Asociacion",
      "3",
      "CABA",
      BigDecimal.valueOf(75.58962),
      BigDecimal.valueOf(75.15963)
    );
  }

  public Ubicacion ubicacionRescatista() {
    return new Ubicacion(
      "Le Rescatiste",
      "1",
      "CABA",
      BigDecimal.valueOf(30.45216),
      BigDecimal.valueOf(30.33652)
    );
  }

  public Mascota mascota1() {
    return new Mascota(
      PERRO,
      "Canela",
      "Cane",
      5.2,
      HEMBRA,
      "Gordita",
      Arrays.asList("unaFoto"),
      null,
      EN_HOGAR_PROPIO
    );
  }

  public Mascota mascota2() {
    return new Mascota(
        GATO,
        "Minerva",
        "Mine",
        .7,
        HEMBRA,
        "Gordita",
        Arrays.asList("unaFoto"),
        null,
        PERDIDA
    );
  }

  public Mascota mascota3() {
    return new Mascota(
        GATO,
        "Iris",
        "Terremoto",
        .7,
        HEMBRA,
        "Atigrada",
        Arrays.asList("unaFoto"),
        null,
        PERDIDA
    );
  }

  public Duenio duenio() {
    return new Duenio(datoPersonal(), Arrays.asList(contacto()), null, null);
  }

  public Duenio adoptante() {
    return new Duenio(datoPersonal(), Arrays.asList(contacto()), null, null);
  }

  public CaracteristicaInput datosDeInteres() {
    return new CaracteristicaInput(TEXT, "Datos de interes", new Opcion(""), new HashSet<AlcanceCaracteristica>() {
    });
  }

  public CaracteristicaInput visitasAlVeterinarioUltimoAnio() {
    return new CaracteristicaInput(NUMBER, "Cantidad de consultas veterinarias", new Opcion("0"), new HashSet<AlcanceCaracteristica>());
  }

  public CaracteristicaChoice estaCastrada() {
    List<Opcion> opcionesBool = Arrays.asList(new Opcion("Si"), new Opcion("No"));

    return new CaracteristicaChoice(BOOLEAN, "Esta Castrada:", opcionesBool, new HashSet<AlcanceCaracteristica>());
  }

  public CaracteristicaChoice vacunas() {
    Opcion moquillo = new Opcion("Moquillo");
    Opcion hepatitis = new Opcion("Hepatitis");
    Opcion parvovirosis = new Opcion("Parvovirosis");
    Opcion rabia = new Opcion("Rabia");
    List<Opcion> vacunas = Arrays.asList(moquillo, hepatitis, parvovirosis, rabia);
    HashSet<AlcanceCaracteristica> alcanceCaracteristica = new HashSet<AlcanceCaracteristica>();
    alcanceCaracteristica.add(AlcanceCaracteristica.REGISTRO_MASCOTA);
    return new CaracteristicaChoice(CHECKBOX, "Vacunas administradas", vacunas, alcanceCaracteristica);
  }

  public CaracteristicaChoice comportamientoConNiños() {
    List<Opcion> comportamientos = Arrays.asList(comportamientoAmistoso, comportamientoManso, comportamientoAgresivo);
    HashSet<AlcanceCaracteristica> alcanceCaracteristica = new HashSet<AlcanceCaracteristica>();
    alcanceCaracteristica.add(AlcanceCaracteristica.REGISTRO_MASCOTA);
    alcanceCaracteristica.add(AlcanceCaracteristica.PREGUNTA_ADOPCION);
    alcanceCaracteristica.add(AlcanceCaracteristica.PREGUNTA_INTERES_ADOPCION);
    return new CaracteristicaChoice(BULLET, "Comportamiento con los niños", comportamientos, alcanceCaracteristica);
  }

  public CaracteristicaChoice contextura() {
    List<Opcion> contexturas = Arrays.asList(contexturaDelgado, contexturaNormal, contexturaGordito);
    HashSet<AlcanceCaracteristica> alcanceCaracteristica = new HashSet<AlcanceCaracteristica>();
    alcanceCaracteristica.add(AlcanceCaracteristica.REGISTRO_MASCOTA);
    alcanceCaracteristica.add(AlcanceCaracteristica.PREGUNTA_ADOPCION);
    return new CaracteristicaChoice(BULLET, "Contextura", contexturas, alcanceCaracteristica);
  }

  public CaracteristicaChoice tamanio() {
    List<Opcion> tamanios = Arrays.asList(tamanioGrande, tamanioMediano, tamanioChico);
    HashSet<AlcanceCaracteristica> alcanceCaracteristica = new HashSet<AlcanceCaracteristica>();
    alcanceCaracteristica.add(AlcanceCaracteristica.REGISTRO_MASCOTA);
    alcanceCaracteristica.add(AlcanceCaracteristica.PREGUNTA_ADOPCION);
    alcanceCaracteristica.add(AlcanceCaracteristica.PREGUNTA_INTERES_ADOPCION);
    return new CaracteristicaChoice(BULLET, "Tamanio de la mascota", tamanios, alcanceCaracteristica);
  }

  public CaracteristicaChoice comportamiento() {
    List<Opcion> comportamientos = Arrays.asList(comportamientoTranquilo, comportamientoPacifico, comportamientoAlborotado);
    HashSet<AlcanceCaracteristica> alcanceCaracteristica = new HashSet<AlcanceCaracteristica>();
    alcanceCaracteristica.add(AlcanceCaracteristica.REGISTRO_MASCOTA);
    alcanceCaracteristica.add(AlcanceCaracteristica.PREGUNTA_ADOPCION);
    return new CaracteristicaChoice(BULLET, "Comportamiento de la mascota", comportamientos, alcanceCaracteristica);
  }

  public CaracteristicaChoice tipoMascota() {
    List<Opcion> tipos = Arrays.asList(tipoGato, tipoPerro);
    HashSet<AlcanceCaracteristica> alcanceCaracteristica = new HashSet<AlcanceCaracteristica>();
    alcanceCaracteristica.add(AlcanceCaracteristica.REGISTRO_MASCOTA);
    alcanceCaracteristica.add(AlcanceCaracteristica.PREGUNTA_ADOPCION);
    return new CaracteristicaChoice(BULLET, "Tipo mascota", tipos, alcanceCaracteristica);
  }

  public static int setOpcionRandom(List<Opcion> opciones) {
    Random random = new Random();
    int index = random.nextInt(opciones.size());

    opciones.get(index).setSeleccionada(true);
    return index;
  }

  public Asociacion asociacion() {
    return new Asociacion("Asociacion Protectora de Animales", this.ubicacionAsociacion3());
  }

  public Asociacion asociacionConPreguntasAdopcion() {
    Asociacion asociacion = new Asociacion("Asociacion Protectora de Animales", this.ubicacionAsociacion3());
    asociacion.setPreguntasAdopcion(Arrays.asList(this.estaCastrada(), this.vacunas()));
    return asociacion;
  }

  public PublicacionAdopcion publicacionAdopcion1() {
    CaracteristicaChoice comportamiento = comportamiento();
    comportamiento.seleccionarOpcion(comportamientoTranquilo, true);
    CaracteristicaChoice tamanios = tamanio();
    tamanios.seleccionarOpcion(tamanioChico, true);

    return new PublicacionAdopcion(
            duenio(),
            mascota1(),
            asociacion(),
            Arrays.asList(comportamiento, tamanios)
    );
  }

  public PublicacionAdopcion publicacionAdopcion2() {
    CaracteristicaChoice comportamiento = comportamiento();
    comportamiento.seleccionarOpcion(comportamientoTranquilo, true);
    CaracteristicaChoice tamanios = tamanio();
    tamanios.seleccionarOpcion(tamanioChico, true);
    CaracteristicaChoice tipos = tipoMascota();
    tipos.seleccionarOpcion(tipoGato, true);

    return new PublicacionAdopcion(
            duenio(),
            mascota1(),
            asociacion(),
            Arrays.asList(comportamiento, tamanios, tipos)
    );
  }

  public PublicacionAdopcion publicacionAdopcion3() {
    CaracteristicaChoice estaCastrado = estaCastrada();
    estaCastrado.seleccionarOpcion((Opcion) estaCastrado.getOpciones().get(0), true);

    return new PublicacionAdopcion(
            duenio(),
            mascota1(),
            asociacion(),
            Arrays.asList(estaCastrado)
            );
  }

  public PublicacionInteresAdopcion publicacionInteresAdopcion1() {
    CaracteristicaChoice tipos = tipoMascota();
    tipos.seleccionarOpcion(tipoGato, true);

    return new PublicacionInteresAdopcion(adoptante(), Arrays.asList(tipos));
  }

  public PublicacionInteresAdopcion publicacionInteresAdopcion2() {
    CaracteristicaChoice comportamiento = comportamiento();
    comportamiento.seleccionarOpcion(comportamientoTranquilo, true);
    CaracteristicaChoice tamanios = tamanio();
    tamanios.seleccionarOpcion(tamanioGrande, true);

    return new PublicacionInteresAdopcion(adoptante(), Arrays.asList(comportamiento, tamanios));
  }

  public PublicacionInteresAdopcion publicacionInteresAdopcion3() {
    CaracteristicaChoice estaCastrado = estaCastrada();
    estaCastrado.seleccionarOpcion((Opcion) estaCastrado.getOpciones().get(0), true);
    CaracteristicaChoice tamanios = tamanio();
    tamanios.seleccionarOpcion(tamanioChico, true);

    return new PublicacionInteresAdopcion(adoptante(), Arrays.asList(estaCastrado, tamanios));
  }
}
