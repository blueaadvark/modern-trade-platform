package com.crd.projectname;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * Reusable extension to start local PostgreSQL instance for µservices based on
 * its databases.
 */
public final class PostgresDbExtension implements BeforeAllCallback, AfterAllCallback {

  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14")
      .withUsername("testcontainers")
      .withPassword("testcontainers")
      .withDatabaseName("testcontainers");

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    postgres.start();
    System.setProperty("DATABASE_JDBC", postgres.getJdbcUrl());
    System.setProperty("DATABASE_USERNAME", postgres.getUsername());
    System.setProperty("DATABASE_PASSWORD", postgres.getPassword());
    System.setProperty("DATABASE_SCHEMA", "public");
  }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    postgres.stop();
  }


  // @Autowired
  // SpringLiquibase liquibaseBean;

  // // We need rollback for database operations so that rollback procedure is
  // required for all liquibase changesets.
  // @Test
  // @SneakyThrows
  // void should_rollback_all_changes() {
  // var dataSource = liquibaseBean.getDataSource();
  // var connection = dataSource.getConnection();
  // var database =
  // DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new
  // JdbcConnection(connection));
  // @Cleanup
  // var liquibase = new Liquibase("db/changelog-master.yaml", new
  // ClassLoaderResourceAccessor(), database);
  // var initialTag = "v0"; // well-known tag in database used for rollback tests
  // Assertions
  // .assertThatCode(() -> liquibase.rollback(initialTag, (String) null))
  // .as("All operation should include rollback operation")
  // .doesNotThrowAnyException();
  // }

}
