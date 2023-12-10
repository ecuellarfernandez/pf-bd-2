package org.proyecto.dao.impl;

import org.proyecto.Conexion;
import org.proyecto.dao.SospechosoTieneCrimenDao;
import org.proyecto.dto.SospechosoTieneCrimen;

import java.sql.*;
import java.util.ArrayList;

public class SospechosoTieneCrimenDaoMySql extends SospechosoTieneCrimenDao {
    private static final String INSERT_QUERY = "INSERT INTO sospechoso_tiene_crimen (idCrimen, idSospechoso) VALUES (?,?)";
    private static final String UPDATE_QUERY = "UPDATE sospechoso_tiene_crimen SET idCrimen = ?, idSospechoso = ? WHERE idRelacion = ?";
    private static final String DELETE_QUERY = "DELETE FROM sospechoso_tiene_crimen WHERE idRelacion = ?";
    private static final String SELECT_QUERY = "SELECT * FROM sospechoso_tiene_crimen WHERE idRelacion = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM sospechoso_tiene_crimen";

    @Override
    public void insert(SospechosoTieneCrimen obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY);
            stmt.setString(1, obj.getIdCrimen());
            stmt.setString(2, obj.getIdSospechoso());
            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al insertar la orden en la SospechosoTieneCrimen");
        }
    }

    @Override
    public void update(SospechosoTieneCrimen obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY);
            stmt.setString(3, obj.getId());
            stmt.setString(1, obj.getIdCrimen());
            stmt.setString(2, obj.getIdSospechoso());
            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar la orden en la SospechosoTieneCrimen");
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
    public SospechosoTieneCrimen get(String id) throws Exception {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Conexion objConexion = Conexion.getOrCreate();
        Connection conn = objConexion.conectarPostgreSQL();
        try {
            statement = conn.prepareStatement(SELECT_QUERY);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createSospechosoTieneCrimenFromResultSet(resultSet);
            } else {
                throw new Exception("No se encontró la SospechosoTieneCrimen con el ID proporcionado.");
            }
        } finally {
            closeResources(statement, resultSet);
        }
    }

    @Override
    public ArrayList<SospechosoTieneCrimen> getList() throws Exception {
        ArrayList<SospechosoTieneCrimen> sospechosoTieneCrimen = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Conexion objConexion = Conexion.getOrCreate();
        Connection conn = objConexion.conectarPostgreSQL();
        try {
            statement = conn.prepareStatement(SELECT_ALL_QUERY);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sospechosoTieneCrimen.add(createSospechosoTieneCrimenFromResultSet(resultSet));
            }
            return sospechosoTieneCrimen;
        } finally {
            closeResources(statement, resultSet);
        }
    }

    private SospechosoTieneCrimen createSospechosoTieneCrimenFromResultSet(ResultSet resultSet) throws SQLException {
        SospechosoTieneCrimen sospechosoTieneCrimen = new SospechosoTieneCrimen();
        sospechosoTieneCrimen.setId(resultSet.getString("idRelacion"));
        sospechosoTieneCrimen.setIdCrimen(resultSet.getString("idCrimen"));
        sospechosoTieneCrimen.setIdSospechoso(resultSet.getString("idSospechoso"));
        return sospechosoTieneCrimen;
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
