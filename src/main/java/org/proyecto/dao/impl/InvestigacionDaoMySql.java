package org.proyecto.dao.impl;

import org.proyecto.Conexion;
import org.proyecto.dao.InvestigacionDao;
import org.proyecto.dto.Investigacion;

import java.sql.*;
import java.util.ArrayList;

public class InvestigacionDaoMySql extends InvestigacionDao {
    private static final String INSERT_QUERY = "INSERT INTO investigacion (fechaInicio, fechaFin, idAgente, idCrimen) VALUES (?, ?, ?,?)";
    private static final String UPDATE_QUERY = "UPDATE investigacion SET fechaInicio = ?, fechaFin = ?, idAgente = ?, idCrimen = ? WHERE idInvestigacion = ?";
    private static final String DELETE_QUERY = "DELETE FROM investigacion WHERE idInvestigacion = ?";
    private static final String SELECT_QUERY = "SELECT * FROM investigacion WHERE idInvestigacion = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM investigacion";

    @Override
    public void insert(Investigacion obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY);
            stmt.setDate(1, obj.getFechaInicio());
            stmt.setDate(2, obj.getFechaFin());
            stmt.setString(3, obj.getIdAgente());
            stmt.setString(4, obj.getIdCrimen());
            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al insertar la investigacion en la base de datos");
        }
    }

    @Override
    public void update(Investigacion obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY);
            stmt.setString(5, obj.getId());
            stmt.setDate(1, obj.getFechaInicio());
            stmt.setDate(2, obj.getFechaFin());
            stmt.setString(3, obj.getIdAgente());
            stmt.setString(4, obj.getIdCrimen());
            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar la investigacion en la base de datos");
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
    public Investigacion get(String id) throws Exception {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Conexion objConexion = Conexion.getOrCreate();
        Connection conn = objConexion.conectarPostgreSQL();
        try {
            statement = conn.prepareStatement(SELECT_QUERY);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createInvestigacionFromResultSet(resultSet);
            } else {
                throw new Exception("No se encontró el departamento con el ID proporcionado.");
            }
        } finally {
            closeResources(statement, resultSet);
        }
    }

    @Override
    public ArrayList<Investigacion> getList() throws Exception {
        ArrayList<Investigacion> investigaciones = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Conexion objConexion = Conexion.getOrCreate();
        Connection conn = objConexion.conectarPostgreSQL();
        try {
            statement = conn.prepareStatement(SELECT_ALL_QUERY);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                investigaciones.add(createInvestigacionFromResultSet(resultSet));
            }
            return investigaciones;
        } finally {
            closeResources(statement, resultSet);
        }
    }

    private Investigacion createInvestigacionFromResultSet(ResultSet resultSet) throws SQLException {
        Investigacion investigacion = new Investigacion();
            investigacion.setId(resultSet.getString("idInvestigacion"));
            investigacion.setFechaInicio(resultSet.getDate("fechaInicio"));
            investigacion.setFechaFin(resultSet.getDate("fechaFin"));
            investigacion.setIdAgente(resultSet.getString("idAgente"));
            investigacion.setIdCrimen(resultSet.getString("idCrimen"));
            return investigacion;
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
