package db;

import constants.Fixture;
import domain.Pregunta;
import domain.PreguntaBullet;
import domain.TipoUsuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityTransaction;

import java.util.Collections;

import static domain.ObjetivoPregunta.CARACTERISTICA_MASCOTA;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PreguntaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  EntityTransaction tran;
  Fixture fixture;

  @BeforeEach
  public void iniciarTransaccion() {
    tran = entityManager().getTransaction();
    tran.begin();
  }

  @AfterEach
  public void commitTransaccion() {
    tran.rollback();
  }

  @Test
  public void puedoPersistirUnaPregunta(){
    PreguntaBullet preguntaBullet = new PreguntaBullet(Collections.singletonList(CARACTERISTICA_MASCOTA),"Una Pregunta Bullet", Collections.singletonList(fixture.tamanioChico), true);
    entityManager().persist(preguntaBullet);

    assertEquals("Una Pregunta Bullet", entityManager().createQuery("from preguntas ", PreguntaBullet.class)
        .getSingleResult().getDescripcion());
  }
}
