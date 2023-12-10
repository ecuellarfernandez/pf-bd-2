package org.proyecto.dao;

import org.proyecto.dto.Sospechoso;

import java.util.ArrayList;

public abstract class SospechosoDao {
    public abstract void insert(Sospechoso obj) throws Exception;
    public abstract void update(Sospechoso obj) throws Exception;
    public abstract void delete(String id) throws Exception;
    public abstract Sospechoso get(String id) throws Exception;
    public abstract ArrayList<Sospechoso> getList() throws Exception;
}
