package main;

import domain.*;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.Arrays;
import java.util.Collections;

import static domain.ObjetivoPregunta.CARACTERISTICA_MASCOTA;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
  public static void main(String[] args) {
    new Bootstrap().run();
  }

  public void run() {
    withTransaction(() -> {
      persist(new Usuario("user", "user2021", TipoUsuario.ESTANDAR));
      persist(new Usuario("admin", "admin2021", TipoUsuario.ADMINISTRADOR));
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
          Collections.singletonList(CARACTERISTICA_MASCOTA),
          "Dato de Interes",
          true,
          null
      ));
    });
  }
}
