package org.proyecto.dao;

import org.proyecto.dto.OrdenDeBusqueda;

import java.util.ArrayList;

public abstract class OrdenDeBusquedaDao {
    public abstract void insert(OrdenDeBusqueda obj) throws Exception;
    public abstract void update(OrdenDeBusqueda obj) throws Exception;
    public abstract void delete(String id) throws Exception;
    public abstract ArrayList<OrdenDeBusqueda> getList() throws Exception;
    public abstract OrdenDeBusqueda get(String id) throws Exception;
}
