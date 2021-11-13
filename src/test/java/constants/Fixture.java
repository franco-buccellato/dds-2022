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
  public final Opcion si = new Opcion("Si");
  public final Opcion no = new Opcion("No");

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
    return new CaracteristicaInput(TEXT, "Datos de interes");
  }

  public CaracteristicaInput visitasAlVeterinarioUltimoAnio() {
    return new CaracteristicaInput(NUMBER, "Cantidad de consultas veterinarias");
  }

  public CaracteristicaChoice estaCastrada() {
    List<Opcion> opcionesBool = Arrays.asList(si, no);

    return new CaracteristicaChoice(BOOLEAN, "Esta Castrada:", opcionesBool, true);
  }

  public Pregunta preguntaEstaCastrada() {
    List<Opcion> opcionesBool = Arrays.asList(si, no);

    return new Pregunta("Esta Castrada:", opcionesBool, AlcancePregunta.PREGUNTA_PREFERENCIA, true);
  }

  public CaracteristicaChoice vacunas() {
    Opcion moquillo = new Opcion("Moquillo");
    Opcion hepatitis = new Opcion("Hepatitis");
    Opcion parvovirosis = new Opcion("Parvovirosis");
    Opcion rabia = new Opcion("Rabia");
    List<Opcion> vacunas = Arrays.asList(moquillo, hepatitis, parvovirosis, rabia);

    return new CaracteristicaChoice(CHECKBOX, "Vacunas administradas", vacunas, true);
  }

  public Pregunta preguntaVacunas() {
    Opcion moquillo = new Opcion("Moquillo");
    Opcion hepatitis = new Opcion("Hepatitis");
    Opcion parvovirosis = new Opcion("Parvovirosis");
    Opcion rabia = new Opcion("Rabia");
    List<Opcion> vacunas = Arrays.asList(moquillo, hepatitis, parvovirosis, rabia);

    return new Pregunta(
        "Vacunas administradas",
        vacunas,
        AlcancePregunta.PREGUNTA_PREFERENCIA,
        true
    );
  }

  public CaracteristicaChoice comportamientoConNinios() {
    List<Opcion> comportamientos = Arrays.asList(
        comportamientoAmistoso,
        comportamientoManso,
        comportamientoAgresivo
    );

    return new CaracteristicaChoice(BULLET, "Comportamiento con los niños", comportamientos, true);
  }

  public Pregunta preguntaComportamientoConNinios() {
    List<Opcion> comportamientos = Arrays.asList(
        comportamientoAmistoso,
        comportamientoManso,
        comportamientoAgresivo
    );

    return new Pregunta(
        "Comportamiento con los niños",
        comportamientos,
        AlcancePregunta.PREGUNTA_PREFERENCIA,
        true
    );
  }

  public CaracteristicaChoice contextura() {
    List<Opcion> contexturas = Arrays.asList(
        contexturaDelgado,
        contexturaNormal,
        contexturaGordito
    );

    return new CaracteristicaChoice(BULLET, "Contextura", contexturas, true);
  }

  public Pregunta preguntaContextura() {
    List<Opcion> contexturas = Arrays.asList(
        contexturaDelgado,
        contexturaNormal,
        contexturaGordito
    );

    return new Pregunta("Contextura", contexturas, AlcancePregunta.PREGUNTA_PREFERENCIA, true);
  }

  public CaracteristicaChoice tamanio() {
    List<Opcion> tamanios = Arrays.asList(tamanioGrande, tamanioMediano, tamanioChico);

    return new CaracteristicaChoice(BULLET, "Tamanio de la mascota", tamanios, true);
  }

  public Pregunta preguntaTamanio() {
    List<Opcion> tamanios = Arrays.asList(tamanioGrande, tamanioMediano, tamanioChico);

    return new Pregunta(
        "Tamanio de la mascota",
        tamanios,
        AlcancePregunta.PREGUNTA_PREFERENCIA,
        true
    );
  }

  public CaracteristicaChoice comportamiento() {
    List<Opcion> comportamientos = Arrays.asList(
        comportamientoTranquilo,
        comportamientoPacifico,
        comportamientoAlborotado
    );

    return new CaracteristicaChoice(BULLET, "Comportamiento de la mascota", comportamientos, true);
  }

  public Pregunta preguntaComportamiento() {
    List<Opcion> comportamientos = Arrays.asList(
        comportamientoTranquilo,
        comportamientoPacifico,
        comportamientoAlborotado
    );

    return new Pregunta(
        "Comportamiento de la mascota",
        comportamientos,
        AlcancePregunta.PREGUNTA_PREFERENCIA,
        true
    );
  }

  public CaracteristicaChoice tipoMascota() {
    List<Opcion> tipos = Arrays.asList(tipoGato, tipoPerro);

    return new CaracteristicaChoice(BULLET, "Tipo mascota", tipos, true);
  }

  public Pregunta preguntaTipoMascota() {
    List<Opcion> tipos = Arrays.asList(tipoGato, tipoPerro);
    return new Pregunta("Tipo mascota", tipos, AlcancePregunta.PREGUNTA_PREFERENCIA, true);
  }

  public Asociacion asociacion() {
    return new Asociacion("Asociacion Protectora de Animales", this.ubicacionAsociacion3());
  }

  public Asociacion asociacionConPreguntasAdopcion() {
    Asociacion asociacion = new Asociacion(
        "Asociacion Protectora de Animales",
        this.ubicacionAsociacion3()
    );
    asociacion.setPreguntasAdopcion(Arrays.asList(preguntaEstaCastrada(), preguntaVacunas()));

    return asociacion;
  }

  public PublicacionAdopcion publicacionAdopcion1() {
    Mascota mascota = mascota1();

    mascota.addCaracteristica(
        new MascotaCaracteristica(tipoMascota(), tipoPerro.getDescripcion())
    );
    mascota.addCaracteristica(
        new MascotaCaracteristica(comportamiento(), comportamientoTranquilo.getDescripcion())
    );
    mascota.addCaracteristica(
        new MascotaCaracteristica(tamanio(), tamanioGrande.getDescripcion())
    );

    return new PublicacionAdopcion(
        duenio(),
        mascota,
        asociacion(),
        Collections.emptyList()
    );
  }

  public PublicacionAdopcion publicacionAdopcion2() {
    Mascota mascota = mascota2();

    mascota.addCaracteristica(
        new MascotaCaracteristica(tipoMascota(), tipoGato.getDescripcion())
    );
    mascota.addCaracteristica(
        new MascotaCaracteristica(comportamiento(), comportamientoTranquilo.getDescripcion())
    );
    mascota.addCaracteristica(
        new MascotaCaracteristica(tamanio(), tamanioGrande.getDescripcion())
    );

    return new PublicacionAdopcion(
        duenio(),
        mascota,
        asociacion(),
        Collections.emptyList()
    );
  }

  public PublicacionAdopcion publicacionAdopcion3() {
    List<Opcion> opcionesBool = Arrays.asList(si, no);
    Pregunta estaCastrado = new Pregunta(
        "Esta Castrada:",
        opcionesBool,
        AlcancePregunta.PREGUNTA_PREFERENCIA,
        true
    );

    PreguntaAdopcion pregunta = new PreguntaAdopcion(preguntaEstaCastrada(), si.getDescripcion());

    return new PublicacionAdopcion(
        duenio(),
        mascota1(),
        asociacion(),
        Collections.singletonList(pregunta)
    );
  }

  public PreguntaInteresAdopcion preguntaInteresAdopcionTipoGato() {
    Pregunta tipos = preguntaTipoMascota();

    return new PreguntaInteresAdopcion(tipos, tipoGato.getDescripcion());
  }

  public PreguntaInteresAdopcion preguntaInteresComportamientoTranquilo() {
    Pregunta comportamiento = preguntaComportamiento();

    return new PreguntaInteresAdopcion(comportamiento, comportamientoTranquilo.getDescripcion());
  }

  public PreguntaInteresAdopcion preguntaInteresAdopcionTamanioGrande() {
    Pregunta tamanio = preguntaTamanio();

    return new PreguntaInteresAdopcion(tamanio, tamanioGrande.getDescripcion());
  }

  public PreguntaInteresAdopcion preguntaInteresAdopcionTamanioChico() {
    Pregunta tamanio = preguntaTamanio();

    return new PreguntaInteresAdopcion(tamanio, tamanioChico.getDescripcion());
  }

  public PreguntaInteresAdopcion preguntaInteresAdopcionEstaCastradaSi() {
    Pregunta estaCastrado = preguntaEstaCastrada();
    String respuestaSi = estaCastrado.getOpciones().get(0).getDescripcion();

    return new PreguntaInteresAdopcion(estaCastrado, respuestaSi);
  }

  public PublicacionInteresAdopcion publicacionInteresAdopcion1() {
    PreguntaInteresAdopcion tipos = preguntaInteresAdopcionTipoGato();

    return new PublicacionInteresAdopcion(adoptante(), Arrays.asList(tipos));
  }


  public PublicacionInteresAdopcion publicacionInteresAdopcion2() {
    PreguntaInteresAdopcion comportamiento = preguntaInteresComportamientoTranquilo();
    PreguntaInteresAdopcion tamanio = preguntaInteresAdopcionTamanioGrande();

    return new PublicacionInteresAdopcion(adoptante(), Arrays.asList(comportamiento, tamanio));
  }

  public PublicacionInteresAdopcion publicacionInteresAdopcion3() {
    PreguntaInteresAdopcion estaCastrado = preguntaInteresAdopcionEstaCastradaSi();
    PreguntaInteresAdopcion tamanioChico = preguntaInteresAdopcionTamanioChico();

    return new PublicacionInteresAdopcion(adoptante(), Arrays.asList(estaCastrado, tamanioChico));
  }
}
