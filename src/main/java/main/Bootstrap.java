package main;

import static domain.ObjetivoPregunta.CARACTERISTICA_MASCOTA;
import static domain.ObjetivoPregunta.PREGUNTA_ASOCIACION_PREFERENCIAS;

import domain.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
  public static void main(String[] args) {
    new Bootstrap().run();
  }

  public void run() {
    Usuario userDuenio = new Usuario("duenio", "duenio2021", TipoUsuario.ESTANDAR);

    withTransaction(() -> {
      persist(new Usuario("user", "user2021", TipoUsuario.ESTANDAR));
      persist(new Usuario("admin", "admin2021", TipoUsuario.ADMINISTRADOR));
      persist(userDuenio);

      persist(
          new Duenio(
              new DatoPersonal(
                  "Juan",
                  "Perez",
                  TipoIdentificacion.DNI,
                  "99999999",
                  LocalDate.of(1960, 12, 12)
              ),
              Arrays.asList(
                  new Contacto(
                      "Jose",
                      "Perez",
                      "4444-4444",
                      "jp@gmail.com",
                      Vinculo.FAMILIAR,
                      null
                  )
              ),
              null,
              userDuenio
          )
      );
      persist(TipoPreguntaFactory.makePregunta(
          TipoPregunta.BULLET,
          Collections.singletonList(CARACTERISTICA_MASCOTA),
          "Tipo",
          true,
          Arrays.asList(
              new Opcion("Gato"),
              new Opcion("Perro")
          )
      ));
      persist(TipoPreguntaFactory.makePregunta(
          TipoPregunta.BULLET,
          Collections.singletonList(CARACTERISTICA_MASCOTA),
          "Tamaño",
          true,
          Arrays.asList(
              new Opcion("Grande"),
              new Opcion("Mediano"),
              new Opcion("Pequeño")
          )
      ));
      persist(TipoPreguntaFactory.makePregunta(
          TipoPregunta.CHECKBOX,
          Collections.singletonList(CARACTERISTICA_MASCOTA),
          "Vacunas",
          false,
          Arrays.asList(
              new Opcion("Moquillo"),
              new Opcion("Hepatitis"),
              new Opcion("Parvovirosis"),
              new Opcion("Rabia")
          )
      ));
      persist(TipoPreguntaFactory.makePregunta(
          TipoPregunta.TEXT,
          Arrays.asList(CARACTERISTICA_MASCOTA, PREGUNTA_ASOCIACION_PREFERENCIAS),
          "Dato de Interes",
          false,
          null
      ));
      persist(TipoPreguntaFactory.makePregunta(
          TipoPregunta.NUMBER,
          Collections.singletonList(PREGUNTA_ASOCIACION_PREFERENCIAS),
          "Edad en anios",
          true,
          null
      ));
    });
  }
}
