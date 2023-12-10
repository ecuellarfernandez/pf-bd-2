package org.proyecto.dao;

import org.proyecto.dto.Equipo;

import java.util.ArrayList;

public abstract class EquipoDao {
    public abstract void insert(Equipo obj) throws Exception;
    public abstract void update(Equipo obj) throws Exception;
    public abstract void delete(String id) throws Exception;
    public abstract Equipo get(String id) throws Exception;
    public abstract ArrayList<Equipo> getList() throws Exception;
}
