package org.proyecto.dao.impl;

import org.proyecto.Conexion;
import org.proyecto.dao.AgenteDao;
import org.proyecto.dto.Agente;

import java.sql.*;
import java.util.ArrayList;

public class AgenteDaoMySql extends AgenteDao {
    private static final String view_Ocupado = "Select * from agentes_ocupados";
    private static final String view_Disponible = "Select * from agentes_disponibles";
    private static final String INSERT_PROCEDURE = "CALL insert_agente_p(?,?,?,?,?,?,?)";
    private static final String INSERT_QUERY = "INSERT INTO agente (nombre, apellidoPaterno, apellidoMaterno, idDepartamento,idRango, idEquipo ) VALUES (?, ?, ? ,? ,?, ?)";
    private static final String UPDATE_QUERY = "UPDATE agente SET nombre=?, apellidoPaterno=?, apellidoMaterno=?, idDepartamento=?, idRango=?, idEquipo=? WHERE idAgente=?";
    private static final String DELETE_QUERY = "DELETE FROM agente WHERE ci = ?";
    private static final String SELECT_QUERY = "SELECT * FROM agente WHERE ci = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM agente";

//    @Override
//    public void pro_Insertar(Agente obj) throws Exception {
//        CallableStatement stmt = null;
//        try {
//            Conexion objConexion = Conexion.getOrCreate();
//            Connection conn = objConexion.conectarPostgreSQL();
//            stmt = conn.prepareCall(INSERT_PROCEDURE);
//            stmt.setString(1, obj.getNombre());
//            stmt.setString(2, obj.getApellidoPaterno());
//            stmt.setString(3, obj.getApellidoMaterno());
//            stmt.setString(4, obj.getIdDepartamento());
//            stmt.setString(5, obj.getIdRango());
//            stmt.setString(6, obj.getIdEquipo());
//            stmt.setString(7, obj.getNombreUsuario());
//            stmt.executeUpdate();
//            objConexion.desconectar();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new Exception("Error al insertar el agente en la base de datos");
//        }finally {
//            closeResources(stmt, null);
//        }
//    }

    @Override
    public void insert(Agente obj) throws Exception {
        PreparedStatement stmt = null;
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            stmt = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNombre());
            stmt.setString(2, obj.getApellidoPaterno());
            stmt.setString(3, obj.getApellidoMaterno());

            int idDepartamento = obj.getIdDepartamento();
            int idRango = obj.getIdRango();
            int idEquipo = obj.getIdEquipo();

            if (idDepartamento > 0) {
                stmt.setInt(4, idDepartamento);
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }

            if (idRango > 0) {
                stmt.setInt(5, idRango);
            } else {
                throw new Exception("Se requiere proporcionar un rango válido para crear un agente.");
            }

            if (idEquipo > 0) {
                stmt.setInt(6, idEquipo);
            } else {
                stmt.setNull(6, java.sql.Types.INTEGER);
            }

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
                obj.setCi(id);
            }

            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al insertar el agente en la base de datos");
        }finally {
            closeResources(stmt, null);
        }
    }


    @Override
    public void update(Agente obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY);

            stmt.setString(1, obj.getNombre());
            stmt.setString(2, obj.getApellidoPaterno());
            stmt.setString(3, obj.getApellidoMaterno());
            stmt.setInt(4, obj.getIdDepartamento());
            int idDepartamento = obj.getIdDepartamento();
            int idRango = obj.getIdRango();
            int idEquipo = obj.getIdEquipo();

            if(idDepartamento > 0){
                stmt.setInt(4, idDepartamento);
            }else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }
            if(idRango > 0){
                stmt.setInt(5, idRango);
            }else {
                throw new Exception("Se requiere proporcionar un rango válido para crear un agente.");
            }
            if(idEquipo > 0){
                stmt.setInt(6, idEquipo);
            }else {
                stmt.setNull(6, java.sql.Types.INTEGER);
            }

            stmt.setInt(7, obj.getCi());
            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el agente en la base de datos");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            PreparedStatement stmt = conn.prepareStatement(DELETE_QUERY);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar el agente de la base de datos");
        }
    }

    @Override
    public Agente get(int id) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            stmt = conn.prepareStatement(SELECT_QUERY);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            System.out.println("AAAAAAAAAAAAAaa" + stmt);
            if (rs.next()) {
                Agente agente = new Agente();
                agente.setCi(rs.getInt("ci"));
                agente.setNombre(rs.getString("nombre"));
                agente.setApellidoPaterno(rs.getString("apellidoPaterno"));
                agente.setApellidoMaterno(rs.getString("apellidoMaterno"));
                agente.setIdDepartamento(rs.getInt("idDepartamento"));
                agente.setIdRango(rs.getInt("idRango"));
                agente.setIdEquipo(rs.getInt("idEquipo"));
                rs.close();
                stmt.close();
                objConexion.desconectar();
                return agente;
            } else {
                objConexion.desconectar();
                throw new Exception("No se encontró el agente en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el agente de la base de datos");
        }finally {
            closeResources(stmt, rs);
        }
    }

    @Override
    public ArrayList<Agente> getList() throws Exception{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            stmt = conn.prepareStatement(SELECT_ALL_QUERY);
            rs = stmt.executeQuery();

            ArrayList<Agente> agentes = new ArrayList<>();
            while (rs.next()) {
                Agente agente = createAgenteFromResultSet(rs);
                agentes.add(agente);
            }

            objConexion.desconectar();
            return agentes;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de agentes de la base de datos");
        }finally {
            closeResources(stmt, rs);
        }
    }

    @Override
    public ArrayList<Agente> getListDisponible() throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            stmt = conn.prepareStatement(view_Disponible);
            rs = stmt.executeQuery();

            ArrayList<Agente> agentes = new ArrayList<>();
            while (rs.next()) {
                Agente agente = createAgenteFromResultSet(rs);
                agentes.add(agente);
            }

            objConexion.desconectar();
            return agentes;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de agentes disponibles de la base de datos");
        }finally {
            closeResources(stmt, rs);
        }
    }

    @Override
    public ArrayList<Agente> getListOcupado() throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            stmt = conn.prepareStatement(view_Ocupado);
            rs = stmt.executeQuery();

            ArrayList<Agente> agentes = new ArrayList<>();
            while (rs.next()) {
                Agente agente = createAgenteFromResultSet(rs);
                agentes.add(agente);
            }

            objConexion.desconectar();
            return agentes;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de agentes ocupados de la base de datos");
        }finally {
            closeResources(stmt, rs);
        }
    }

    private Agente createAgenteFromResultSet(ResultSet resultSet) throws Exception {
        Agente agente = new Agente();
        agente.setCi(resultSet.getInt("ci"));
        agente.setNombre(resultSet.getString("nombre"));
        agente.setApellidoPaterno(resultSet.getString("apellidoPaterno"));
        agente.setApellidoMaterno(resultSet.getString("apellidoMaterno"));
        agente.setIdRango(resultSet.getInt("idRango"));
        agente.setIdEquipo(resultSet.getInt("idEquipo"));
        agente.setIdDepartamento(resultSet.getInt("idDepartamento"));

        return agente;
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
