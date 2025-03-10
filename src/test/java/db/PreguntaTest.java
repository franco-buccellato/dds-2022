package db;

import static domain.ObjetivoPregunta.CARACTERISTICA_MASCOTA;
import static org.junit.jupiter.api.Assertions.assertEquals;

import constants.Fixture;
import domain.Pregunta;
import domain.PreguntaBullet;
import javax.persistence.EntityTransaction;
import java.util.Collections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;


public class PreguntaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  EntityTransaction tran;
  Fixture fixture = new Fixture();

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
  public void puedoPersistirUnaPregunta() {
    Pregunta preguntaBullet = new PreguntaBullet(
        Collections.singletonList(CARACTERISTICA_MASCOTA),
        "Una Pregunta Bullet",
        Collections.singletonList(fixture.tamanioChico),
        true
    );
    entityManager().persist(preguntaBullet);

    assertEquals(
        "Una Pregunta Bullet",
        entityManager().createQuery("from preguntas ", Pregunta.class)
            .getSingleResult()
            .getDescripcion()
    );
  }
}
