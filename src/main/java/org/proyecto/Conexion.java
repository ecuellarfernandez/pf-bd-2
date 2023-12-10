package org.proyecto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    private String host = "127.0.0.1";
    private String port = "5432";
    private String database = "proyecto";
    private String user = "postgres";
    private String pass = "root";
    private String schema = "mydb"; // Reemplaza "tuesquema" con el nombre de tu esquema
    private Connection objConnection;

    private static Conexion instance;

    private Conexion() {
        // Constructor privado para evitar instanciación externa
    }

    public static synchronized Conexion getOrCreate() {
        if (instance == null) {
            instance = new Conexion();
        }
        return instance;
    }

    public Connection conectarPostgreSQL() {
        try {
            Class.forName("org.postgresql.Driver");
            String connectionString = "jdbc:postgresql://" + host + ":" + port + "/" + database + "?currentSchema=" + schema;
            objConnection = DriverManager.getConnection(connectionString, user, pass);
            System.out.println("Conexion PostgreSQL - Java EXITOSA");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return objConnection;
    }

    public void ejecutarSQL(String sql) {
        try (Connection con = conectarPostgreSQL()) {
            Statement consulta = con.createStatement();
            consulta.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet ejecutar(String sql) {
        ResultSet resultSet = null;
        try {
            Connection con = conectarPostgreSQL();
            Statement consulta = con.createStatement();
            resultSet = consulta.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    public int ejecutarSimple(String query) {
        try {
            Statement stmt = objConnection.createStatement();
            int nb = stmt.executeUpdate(query);
            return nb;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int ejecutarInsert(String query) {
        try {
            Statement stmt = objConnection.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void desconectar() {
        try {
            if (estaConectado()) {
                objConnection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean estaConectado() {
        if (objConnection == null) {
            return false;
        }
        try {
            if (objConnection.isClosed()) {
                return false;
            }
        } catch (SQLException e) {
            objConnection = null;
            return false;
        }
        return true;
    }
}
