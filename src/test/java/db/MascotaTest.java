package db;

import static org.junit.jupiter.api.Assertions.*;

import constants.Fixture;
import domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityTransaction;
import java.util.Collections;
import java.util.List;

public class MascotaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  EntityTransaction tran;
  Fixture fixture = new Fixture();

  @BeforeEach
  public void iniciarTransaccion() {
    fixture.generalSetup();
    tran = entityManager().getTransaction();
    tran.begin();
  }

  @AfterEach
  public void commitTransaccion() {
    tran.rollback();
  }

  @Test
  public void puedoPersistirUnaMascota() {
    List<RespuestaCaracteristicaMascota> caracteristicas = Collections.singletonList(
        new RespuestaCaracteristicaMascota(fixture.estaCastrada, Collections.singletonList(fixture.no))
    );

    Mascota mascota = new Mascota(
        TipoMascota.PERRO,
        "Roque",
        "Cachito",
        2.5,
        Sexo.MACHO,
        "Gordo y cacheton",
        Collections.singletonList("unaFoto"),
        caracteristicas,
        SituacionMascota.EN_HOGAR_PROPIO
    );

    entityManager().persist(mascota);

    assertEquals("Roque", entityManager().createQuery("from mascotas ", Mascota.class).getSingleResult().getNombre());
    assertEquals(fixture.no, entityManager().createQuery("from mascotas ", Mascota.class).getSingleResult().getCaracteristicas().get(0).getSelecciones().get(0));
  }

}
