package org.proyecto.dao.impl;

import org.proyecto.Conexion;
import org.proyecto.dao.DepartamentoDao;
import org.proyecto.dto.Departamento;

import java.sql.*;
import java.util.ArrayList;

public class DepartamentoDaoMySql extends DepartamentoDao {
    private static final String INSERT_PROCEDURE = "CALL insertar_departamento(?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE departamento SET nombre = ?, descripcion = ?, idAdministrador = ? WHERE idDepartamento = ?";
    private static final String DELETE_QUERY = "DELETE FROM departamento WHERE idDepartamento = ?";
    private static final String SELECT_QUERY = "SELECT * FROM departamento WHERE idDepartamento = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM departamento";
    private static final String UPDATE_AGENTE_QUERY = "UPDATE agente SET idDepartamento = ? WHERE idAgente = ?";
    @Override
    public void insert(Departamento departamento) throws Exception {
        PreparedStatement statement = null;
        Conexion objConexion = Conexion.getOrCreate();
        Connection conn = objConexion.conectarPostgreSQL();
        try {
            statement = conn.prepareStatement(INSERT_PROCEDURE);
            statement.setString(1, departamento.getNombre());
            statement.setString(2, departamento.getDescripcion());
            String idAdministrador = departamento.getIdAdministador();
            if(idAdministrador.length() > 0){
                statement.setString(3, idAdministrador);
            }else {
                statement.setNull(3, Types.VARCHAR);
            }
            statement.executeUpdate();
        } finally {
            closeResources(statement, null);
        }
    }

//    public void asignarDepartamentoAgente(int idDepartamento, int idAgente){
//        PreparedStatement statement = null;
//        Conexion objConexion = Conexion.getOrCreate();
//        Connection conn = objConexion.conectarMySQL();
//        try{
//            statement = conn.prepareStatement(UPDATE_AGENTE_QUERY);
//            statement.setInt(1, idDepartamento);
//            statement.setInt(2, idAgente);
//            System.out.println(statement);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }finally {
//            closeResources(statement, null);
//        }
//    }

    @Override
    public void update(Departamento departamento) throws Exception {
        PreparedStatement statement = null;
        Conexion objConexion = Conexion.getOrCreate();
        Connection conn = objConexion.conectarPostgreSQL();
        try {
            statement = conn.prepareStatement(UPDATE_QUERY);
            statement.setString(1, departamento.getNombre());
            statement.setString(2, departamento.getDescripcion());
            String idAdministrador = departamento.getIdAdministador();
            statement.setString(3, idAdministrador);
//            if(idAdministrador > 0){
//                statement.setInt(3, departamento.getIdAdministador());
//                asignarDepartamentoAgente(departamento.getId(), idAdministrador);
//            }else {
//                statement.setNull(3, java.sql.Types.INTEGER);
//            }

            statement.setString(4, departamento.getId());
            statement.executeUpdate();
        } finally {
            closeResources(statement, null);
        }
    }

    @Override
    public void delete(String idDepartamento) throws Exception {
        PreparedStatement statement = null;
        Conexion objConexion = Conexion.getOrCreate();
        Connection conn = objConexion.conectarPostgreSQL();
        try {
            statement = conn.prepareStatement(DELETE_QUERY);
            statement.setString(1, idDepartamento);
            statement.executeUpdate();
        } finally {
            closeResources(statement, null);
        }
    }

    @Override
    public Departamento get(String idDepartamento) throws Exception {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Conexion objConexion = Conexion.getOrCreate();
        Connection conn = objConexion.conectarPostgreSQL();
        try {
            statement = conn.prepareStatement(SELECT_QUERY);
            statement.setString(1, idDepartamento);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createDepartamentoFromResultSet(resultSet);
            } else {
                throw new Exception("No se encontró el departamento con el ID proporcionado.");
            }
        } finally {
            closeResources(statement, resultSet);
        }
    }

    @Override
    public ArrayList<Departamento> getList() throws Exception {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Conexion objConexion = Conexion.getOrCreate();
        Connection conn = objConexion.conectarPostgreSQL();
        try {
            statement = conn.prepareStatement(SELECT_ALL_QUERY);
            resultSet = statement.executeQuery();
            ArrayList<Departamento> departamentos = new ArrayList<>();
            while (resultSet.next()) {
                Departamento departamento = createDepartamentoFromResultSet(resultSet);
                departamentos.add(departamento);
            }
            return departamentos;
        } finally {
            closeResources(statement, resultSet);
        }
    }

    private Departamento createDepartamentoFromResultSet(ResultSet resultSet) throws Exception {
        Departamento departamento = new Departamento();
        departamento.setId(resultSet.getString("idDepartamento"));
        departamento.setNombre(resultSet.getString("nombre"));
        departamento.setDescripcion(resultSet.getString("descripcion"));
        departamento.setIdAdministador(resultSet.getString("agente_ci_administrador"));
        return departamento;
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
