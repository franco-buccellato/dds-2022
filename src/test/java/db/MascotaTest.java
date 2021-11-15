//package db;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import domain.Mascota;
//import domain.Opcion;
//import domain.Sexo;
//import domain.SituacionMascota;
//import domain.TipoMascota;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
//import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
//
//import javax.persistence.EntityTransaction;
//import java.util.Collections;
//import java.util.List;
//
//public class MascotaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
//  EntityTransaction tran;
//
//  @BeforeEach
//  public void iniciarTransaccion() {
//    tran = entityManager().getTransaction();
//    tran.begin();
//  }
//
//  @AfterEach
//  public void commitTransaccion() {
//    tran.rollback();
//  }
//
//  @Test
//  public void puedoPersistirUnaMascota() {
//    List<Caracteristica> caracteristicas = Collections.singletonList(
//        new CaracteristicaChoice(
//            TipoCaracteristica.BULLET,
//            "Tipo pelaje",
//            Collections.singletonList(new Opcion("Peludo"))
//        )
//    );
//
//    Mascota mascota = new Mascota(
//        TipoMascota.PERRO,
//        "Roque",
//        "Cachito",
//        2.5,
//        Sexo.MACHO,
//        "Gordo y cacheton",
//        Collections.singletonList("unaFoto"),
//        caracteristicas,
//        SituacionMascota.EN_HOGAR_PROPIO
//    );
//
//    entityManager().persist(mascota);
//
//    assertEquals(
//        "Roque",
//        entityManager().createQuery("from mascotas ", Mascota.class).getSingleResult().getNombre()
//    );
//  }
//
//}
