package org.proyecto.dao.impl;

import org.proyecto.Conexion;
import org.proyecto.dao.OrdenDeBusquedaDao;
import org.proyecto.dto.OrdenDeBusqueda;

import java.sql.*;
import java.util.ArrayList;

public class OrdenDeBusquedaDaoMySql extends OrdenDeBusquedaDao {
    private static final String INSERT_QUERY = "INSERT INTO orden_de_busqueda (estado, idAgente, idSospechoso, idInvestigacion) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE orden_de_busqueda SET estado = ?, idAgente = ?, idSospechoso = ?, idInvestigacion = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM orden_de_busqueda WHERE idOrden = ?";
    private static final String SELECT_QUERY = "SELECT * FROM orden_de_busqueda WHERE idOrden = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM orden_de_busqueda";

    @Override
    public void insert(OrdenDeBusqueda obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY);
            stmt.setString(1, obj.getEstado());
            stmt.setString(2, obj.getIdAgente());
            stmt.setString(3, obj.getIdSospechoso());
            stmt.setString(4, obj.getIdInvestigacion());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al insertar la orden en la base de datos");
        }
    }

    @Override
    public void update(OrdenDeBusqueda obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY);
            stmt.setString(2, obj.getId());
            stmt.setString(1, obj.getEstado());
            stmt.setString(2, obj.getIdAgente());
            stmt.setString(3, obj.getIdSospechoso());
            stmt.setString(4, obj.getIdInvestigacion());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar la orden en la base de datos");
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
    public OrdenDeBusqueda get(String id) throws Exception {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Conexion objConexion = Conexion.getOrCreate();
        Connection conn = objConexion.conectarPostgreSQL();
        try {
            statement = conn.prepareStatement(SELECT_QUERY);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createOrdenFromResultSet(resultSet);
            } else {
                throw new Exception("No se encontró la categoria con el ID proporcionado.");
            }
        } finally {
            closeResources(statement, resultSet);
        }
    }

    @Override
    public ArrayList<OrdenDeBusqueda> getList() throws Exception {
        ArrayList<OrdenDeBusqueda> ordenes = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Conexion objConexion = Conexion.getOrCreate();
        Connection conn = objConexion.conectarPostgreSQL();
        try {
            statement = conn.prepareStatement(SELECT_ALL_QUERY);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ordenes.add(createOrdenFromResultSet(resultSet));
            }
            return ordenes;
        } finally {
            closeResources(statement, resultSet);
        }
    }

    private OrdenDeBusqueda createOrdenFromResultSet(ResultSet resultSet) throws SQLException {
        OrdenDeBusqueda orden = new OrdenDeBusqueda();
        orden.setId(resultSet.getString("idOrden"));
        orden.setEstado(resultSet.getString("estado"));
        orden.setIdAgente(resultSet.getString("idAgente"));
        orden.setIdSospechoso(resultSet.getString("idSospechoso"));
        orden.setIdInvestigacion(resultSet.getString("idInvestigacion"));
        return orden;
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