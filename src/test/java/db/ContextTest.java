package db;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;


public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

  @Test
  public void contextUp() {
    assertNotNull(entityManager());
  }
}
