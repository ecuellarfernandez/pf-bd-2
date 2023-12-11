package org.proyecto.dao;

import org.proyecto.dto.Agente;

import java.util.ArrayList;

public abstract class AgenteDao {
//    public abstract void pro_Insertar(Agente obj) throws Exception;
    public abstract void insert(Agente obj) throws Exception;
    public abstract void update(Agente obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract Agente get(int id) throws Exception;
    public abstract ArrayList<Agente> getList() throws Exception;
    public abstract ArrayList<Agente> getListDisponible() throws Exception;
    public abstract ArrayList<Agente> getListOcupado() throws Exception;


}
