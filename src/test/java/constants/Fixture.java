package constants;

import static domain.Sexo.HEMBRA;
import static domain.SituacionMascota.*;
//import static domain.TipoCaracteristica.*;
import static domain.TipoIdentificacion.DNI;
import static domain.TipoMascota.*;
import static domain.TipoUsuario.VOLUNTARIO;
import static domain.Vinculo.AMISTAD;
import static domain.ObjetivoPregunta.*;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import domain.*;
import org.junit.jupiter.api.BeforeEach;

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
  public final Opcion moquillo = new Opcion("Moquillo");
  public final Opcion hepatitis = new Opcion("Hepatitis");
  public final Opcion parvovirosis = new Opcion("Parvovirosis");
  public final Opcion rabia = new Opcion("Rabia");
  public final List<ObjetivoPregunta> objetivosPreguntas = Arrays.asList(
      CARACTERISTICA_MASCOTA,
      PREGUNTA_ASOCIACION,
      PUBLICACION_ADOPCION,
      PUBLICACION_INTERES_ADOPCION_COMODIDAD,
      PUBLICACION_INTERES_ADOPCION_PREFERENCIA
  );
  public final List<Opcion> opcionesBool = Arrays.asList(si, no);
  public final List<Opcion> contexturas = Arrays.asList(
      contexturaDelgado,
      contexturaNormal,
      contexturaGordito
  );
  public final List<Opcion> comportamientos = Arrays.asList(
      comportamientoAmistoso,
      comportamientoManso,
      comportamientoAgresivo
  );
  public final List<Opcion> opcionesVacunas = Arrays.asList(moquillo,
                                                            hepatitis,
                                                            parvovirosis,
                                                            rabia);
  public final PreguntaBullet estaCastrada = new PreguntaBullet(
      objetivosPreguntas,
      "Esta Castrada:",
      opcionesBool,
      true
  );
  public final PreguntaText datosDeInteres = new PreguntaText(
      objetivosPreguntas,
      "Datos de interes",
      Collections.emptyList(),
      true
  );
  public final PreguntaText visitasAlVeterinarioUltimoAnio = new PreguntaText(
      objetivosPreguntas,
      "Cantidad de consultas veterinarias",
      Collections.emptyList(),
      true
  );
  public final PreguntaBullet contextura = new PreguntaBullet(
      objetivosPreguntas,
      "Contextura",
      contexturas,
      true
  );
  public final PreguntaBullet comportamientoConNinios = new PreguntaBullet(
      objetivosPreguntas,
      "Comportamiento con los niños",
      comportamientos,
      true
  );
  public final PreguntaCheckBox vacunas = new PreguntaCheckBox(
      objetivosPreguntas,
      "Vacunas administradas",
      opcionesVacunas,
      true
  );


  @BeforeEach
  public void generalSetup() {
    comportamientoTranquilo.setId(1L);
    comportamientoPacifico.setId(2L);
    comportamientoAlborotado.setId(3L);
    comportamientoAgresivo.setId(4L);
    comportamientoAmistoso.setId(5L);
    comportamientoManso.setId(6L);
    tamanioGrande.setId(7L);
    tamanioMediano.setId(8L);
    tamanioChico.setId(9L);
    contexturaDelgado.setId(10L);
    contexturaNormal.setId(11L);
    contexturaGordito.setId(12L);
    tipoGato.setId(13L);
    tipoPerro.setId(14L);
    si.setId(15L);
    no.setId(16L);
    moquillo.setId(17L);
    hepatitis.setId(18L);
    parvovirosis.setId(19L);
    rabia.setId(20L);

    estaCastrada.setId(1L);
    datosDeInteres.setId(2L);
    visitasAlVeterinarioUltimoAnio.setId(3L);
    contextura.setId(4L);
    comportamientoConNinios.setId(5L);
    vacunas.setId(6L);
  }

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

  public PreguntaBullet tamanio() {
    List<Opcion> tamanios = Arrays.asList(tamanioGrande, tamanioMediano, tamanioChico);

    return new PreguntaBullet(objetivosPreguntas, "Tamanio de la mascota", tamanios, true);
  }

  public PreguntaBullet comportamiento() {
    List<Opcion> comportamientos = Arrays.asList(
        comportamientoTranquilo,
        comportamientoPacifico,
        comportamientoAlborotado
    );

    return new PreguntaBullet(
        objetivosPreguntas,
        "Comportamiento de la mascota",
        comportamientos,
        true
    );
  }

  public PreguntaBullet tipoMascota() {
    List<Opcion> tipos = Arrays.asList(tipoGato, tipoPerro);

    return new PreguntaBullet(objetivosPreguntas, "Tipo mascota", tipos, true);
  }

  public Asociacion asociacion() {
    return new Asociacion("Asociacion Protectora de Animales", this.ubicacionAsociacion3());
  }

  public Asociacion asociacionConPreguntasAdopcion() {
    Asociacion asociacion = new Asociacion(
        "Asociacion Protectora de Animales",
        this.ubicacionAsociacion3()
    );
    asociacion.setPreguntasAdopcion(Arrays.asList(estaCastrada, vacunas));

    return asociacion;
  }

  public PublicacionAdopcion publicacionAdopcion1() {
    Mascota mascota = mascota1();

    mascota.addCaracteristica(
        new SeleccionCaracteristicaMascota(tipoMascota(), Arrays.asList(tipoPerro))
    );
    mascota.addCaracteristica(
        new SeleccionCaracteristicaMascota(comportamiento(), Arrays.asList(comportamientoTranquilo))
    );
    mascota.addCaracteristica(
        new SeleccionCaracteristicaMascota(tamanio(), Arrays.asList(tamanioGrande))
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
        new SeleccionCaracteristicaMascota(tipoMascota(), Arrays.asList(tipoGato))
    );
    mascota.addCaracteristica(
        new SeleccionCaracteristicaMascota(comportamiento(), Arrays.asList(comportamientoTranquilo))
    );
    mascota.addCaracteristica(
        new SeleccionCaracteristicaMascota(tamanio(), Arrays.asList(tamanioGrande))
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
    Pregunta estaCastrado = new PreguntaBullet(
        objetivosPreguntas,
        "Esta Castrada:",
        opcionesBool,
        true
    );

    SeleccionPublicacionAdopcion pregunta = new SeleccionPublicacionAdopcion(estaCastrado, Arrays.asList(si));

    return new PublicacionAdopcion(
        duenio(),
        mascota1(),
        asociacion(),
        Collections.singletonList(pregunta)
    );
  }

  public SeleccionInteresAdopcion preguntaInteresAdopcionTipoGato() {
    Pregunta tipos = tipoMascota();

    return new SeleccionInteresAdopcion(tipos, Arrays.asList(tipoGato));
  }

  public SeleccionInteresAdopcion preguntaInteresComportamientoTranquilo() {
    Pregunta comportamiento = comportamiento();

    return new SeleccionInteresAdopcion(comportamiento, Arrays.asList(comportamientoTranquilo));
  }

  public SeleccionInteresAdopcion preguntaInteresAdopcionTamanioGrande() {
    Pregunta tamanio = tamanio();

    return new SeleccionInteresAdopcion(tamanio, Arrays.asList(tamanioGrande));
  }

  public SeleccionInteresAdopcion preguntaInteresAdopcionTamanioChico() {
    Pregunta tamanio = tamanio();

    return new SeleccionInteresAdopcion(tamanio, Arrays.asList(tamanioChico));
  }

  public SeleccionInteresAdopcion preguntaInteresAdopcionEstaCastradaSi() {
    Pregunta estaCastrado = estaCastrada;

    return new SeleccionInteresAdopcion(estaCastrado, Arrays.asList(si));
  }

  public PublicacionInteresAdopcion publicacionInteresAdopcion1() {
    SeleccionInteresAdopcion tipos = preguntaInteresAdopcionTipoGato();

    return new PublicacionInteresAdopcion(adoptante(), Arrays.asList(tipos));
  }


  public PublicacionInteresAdopcion publicacionInteresAdopcion2() {
    SeleccionInteresAdopcion comportamiento = preguntaInteresComportamientoTranquilo();
    SeleccionInteresAdopcion tamanio = preguntaInteresAdopcionTamanioGrande();

    return new PublicacionInteresAdopcion(adoptante(), Arrays.asList(comportamiento, tamanio));
  }

  public PublicacionInteresAdopcion publicacionInteresAdopcion3() {
    SeleccionInteresAdopcion estaCastrado = preguntaInteresAdopcionEstaCastradaSi();
    SeleccionInteresAdopcion tamanioChico = preguntaInteresAdopcionTamanioChico();

    return new PublicacionInteresAdopcion(adoptante(), Arrays.asList(estaCastrado, tamanioChico));
  }

  public SeleccionCaracteristicaMascota seleccionVacunas() {
    List<Opcion> vacunasDadas = Arrays.asList(moquillo, rabia);

    return new SeleccionCaracteristicaMascota(vacunas, vacunasDadas);
  }

  public SeleccionCaracteristicaMascota seleccionComportamientoConNinios() {
    return new SeleccionCaracteristicaMascota(
        comportamiento(),
        Arrays.asList(comportamientoTranquilo)
    );
  }

  public SeleccionCaracteristicaMascota seleccionEstaCastrado() {
    return new SeleccionCaracteristicaMascota(estaCastrada, Arrays.asList(si));
  }

  public SeleccionCaracteristicaMascota seleccionDatoDeInteres() {
    return new SeleccionCaracteristicaMascota(
        datosDeInteres,
        Arrays.asList(new Opcion("Le gusta perseguir motos"))
    );
  }

  public SeleccionCaracteristicaMascota seleccionContextura() {
    return new SeleccionCaracteristicaMascota(
        contextura,
        Arrays.asList(contexturaGordito)
    );
  }
}
