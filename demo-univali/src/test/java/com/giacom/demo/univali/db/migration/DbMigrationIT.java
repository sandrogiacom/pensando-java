package com.giacom.demo.univali.db.migration;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.flywaydb.core.Flyway;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.MySQLContainer;

public class DbMigrationIT {

    @Rule
    public MySQLContainer mySql = new MySQLContainer("mysql:5.6");

    @Test
    public void createSampleUserTable() throws InterruptedException, SQLException {
        Flyway flyway = new Flyway();
        flyway.setDataSource(mySql.getJdbcUrl(), mySql.getUsername(), mySql.getPassword());
        flyway.migrate();

        System.out.println("mysql --user=" + mySql.getUsername() +
                " --password=" + mySql.getPassword() + " " + mySql.getDatabaseName());

        List<String> columnNames = getColNames(flyway);

        Assertions.assertThat(columnNames)
                .hasSize(4)
                .containsExactlyInAnyOrder("id", "name", "last_name", "age");

        //Thread.sleep(60 * 2000);
    }

    private List<String> getColNames(Flyway flyway) throws SQLException {
        List<String> columnNames = new ArrayList<>();
        try (Connection con = flyway.getDataSource().getConnection()) {
            try (Statement st = con.createStatement()) {
                ResultSet rs = st.executeQuery("SELECT * from sample_user");
                int columns = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columns; i++) {
                    columnNames.add(rs.getMetaData().getColumnName(i));
                }
            }
        }
        return columnNames;
    }

}
