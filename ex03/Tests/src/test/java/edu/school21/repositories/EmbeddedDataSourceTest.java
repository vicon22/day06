package edu.school21.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

class EmbeddedDataSourceTest {

    DataSource dataSource;

    @BeforeEach
    public void init(){
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        dataSource = embeddedDatabaseBuilder
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("/schema.sql")
                .addScript("/data.sql")
                .build();
    }

    @Test
    public void testConnection() throws SQLException {
        assertNotNull(dataSource.getConnection());
    }

}