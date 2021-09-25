package db;

import static org.junit.jupiter.api.Assertions.*;

import domain.TipoUsuario;
import domain.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import javax.persistence.EntityTransaction;

public class UsuarioTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  EntityTransaction tran;

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
  public void puedoPersistirUnUsuario(){
    Usuario dario = new Usuario("Dario","Passw0rd", TipoUsuario.ADMINISTRADOR);
    entityManager().persist(dario);

    assertEquals("Dario", entityManager().createQuery("from Usuario ", Usuario.class)
        .getSingleResult().getNombreUsuario());
  }
}


