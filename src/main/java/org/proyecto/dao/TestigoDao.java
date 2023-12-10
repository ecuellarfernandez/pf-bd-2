package org.proyecto.dao;

import org.proyecto.dto.Testigo;

import java.util.ArrayList;

public abstract class TestigoDao {
    public abstract void insert(Testigo obj) throws Exception;
    public abstract void update(Testigo obj) throws Exception;
    public abstract void delete(String id) throws Exception;
    public abstract Testigo get(String id) throws Exception;
    public abstract ArrayList<Testigo> getList() throws Exception;
}
