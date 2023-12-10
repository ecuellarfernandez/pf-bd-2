package org.proyecto.dao.impl;

import org.proyecto.Conexion;
import org.proyecto.dao.CategoriaCrimenDao;
import org.proyecto.dto.CategoriaCrimen;

import java.sql.*;
import java.util.ArrayList;

public class CategoriaDaoMySql extends CategoriaCrimenDao {
    private static final String PROCEDURE_INSERT = "CALL insertar_categoria_crimen(?, ?)";
    private static final String INSERT_QUERY = "INSERT INTO categoria_crimen (nombre) VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE categoria_crimen SET nombre = ? WHERE idCategoria = ?";
    private static final String DELETE_QUERY = "DELETE FROM categoria_crimen WHERE idCategoria = ?";
    private static final String SELECT_QUERY = "SELECT * FROM categoria_crimen WHERE idCategoria = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM categoria_crimen";

    @Override
    public void pro_insert(CategoriaCrimen obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            CallableStatement stmt = conn.prepareCall(PROCEDURE_INSERT);
            stmt.setString(1, obj.getId());
            stmt.setString(2, obj.getNombre());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al insertar la categoria en la base de datos");
        }
    }

    @Override
    public void insert(CategoriaCrimen obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY);
            stmt.setString(1, obj.getNombre());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al insertar la categoria en la base de datos");
        }
    }

    @Override
    public void update(CategoriaCrimen obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY);
            stmt.setString(2, obj.getId());
            stmt.setString(1, obj.getNombre());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar la categoria en la base de datos");
        }
    }

    @Override
    public void delete(String id) throws Exception {
        PreparedStatement statement = null;
        Conexion objConexion = Conexion.getOrCreate();
        Connection conn = objConexion.conectarPostgreSQL();
        try {
            statement = conn.prepareStatement(DELETE_QUERY);
            statement.setString(1, id);
            statement.executeUpdate();
        } finally {
            closeResources(statement, null);
        }
    }

    @Override
    public CategoriaCrimen get(String id) throws Exception {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Conexion objConexion = Conexion.getOrCreate();
        Connection conn = objConexion.conectarPostgreSQL();
        try {
            statement = conn.prepareStatement(SELECT_QUERY);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createCategoriaFromResultSet(resultSet);
            } else {
                throw new Exception("No se encontró la categoria con el ID proporcionado.");
            }
        } finally {
            closeResources(statement, resultSet);
        }
    }

    @Override
    public ArrayList<CategoriaCrimen> getList() throws Exception {
        ArrayList<CategoriaCrimen> categorias = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Conexion objConexion = Conexion.getOrCreate();
        Connection conn = objConexion.conectarPostgreSQL();
        try {
            statement = conn.prepareStatement(SELECT_ALL_QUERY);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                categorias.add(createCategoriaFromResultSet(resultSet));
            }
            return categorias;
        } finally {
            closeResources(statement, resultSet);
        }
    }

    private CategoriaCrimen createCategoriaFromResultSet(ResultSet resultSet) throws SQLException {
        CategoriaCrimen categoriaCrimen = new CategoriaCrimen();
        categoriaCrimen.setId(resultSet.getString("idCategoria"));
        categoriaCrimen.setNombre(resultSet.getString("nombre"));
        return categoriaCrimen;
    }

    private void closeResources(PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
