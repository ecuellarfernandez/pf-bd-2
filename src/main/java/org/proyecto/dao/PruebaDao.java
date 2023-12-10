package org.proyecto.dao;
import org.proyecto.dto.Prueba;
import java.util.ArrayList;
public abstract class PruebaDao {
    public abstract void insert(Prueba obj) throws Exception;
    public abstract void update(Prueba obj) throws Exception;
    public abstract void delete(String id) throws Exception;
    public abstract Prueba get(String id) throws Exception;
    public abstract ArrayList<Prueba> getList() throws Exception;
}
