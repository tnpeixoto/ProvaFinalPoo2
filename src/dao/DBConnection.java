package dao;

import java.sql.*;

public class DBConnection {

    private static final String URL_MYSQL = "jdbc:mariadb://localhost:3306/investimento";
    private static final String DRIVER_CLASS_MYSQL = "org.mariadb.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "K{RUmWXBUi/X4]K%";

    public static Connection getConnection() {
        System.out.println("Conectando ao Banco de Dados");
        try {
            Class.forName(DRIVER_CLASS_MYSQL);
            return DriverManager.getConnection(URL_MYSQL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (conn!= null) {
                conn.close();
            }

            if (stmt!= null) {
                stmt.close();
            }

            if (rs!= null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable() {
        Connection connection = getConnection();
        PreparedStatement stmt = null;
        String sql = "CREATE TABLE IF NOT EXISTS carteira (" +
                "  id int(20) NOT NULL AUTO_INCREMENT," +
                "  ticker VARCHAR(255) NOT NULL," +
                "  alocacao VARCHAR(255) NOT NULL," +
                "  precoentrada VARCHAR (255) NOT NULL," +
                "  CONSTRAINT PK_USER PRIMARY KEY (id)"+
                ");";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Tables Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }
    }
}
