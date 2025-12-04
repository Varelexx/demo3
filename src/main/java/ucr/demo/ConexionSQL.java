package ucr.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {

    private static final String URL =
            "jdbc:sqlserver://DESKTOP-E3D4MLJ:1433;"
                    + "databaseName=ProyectoUber;"
                    + "instanceName=SQLEXPRESS;"
                    + "encrypt=false;trustServerCertificate=true;";


    private static final String USER = "sa";
    private static final String PASS = "12345678";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión exitosa a SQL Server");
            return conn;
        } catch (SQLException e) {
            System.out.println("❌ Error conectando a SQL Server");
            e.printStackTrace();
            return null;
        }
    }
}
