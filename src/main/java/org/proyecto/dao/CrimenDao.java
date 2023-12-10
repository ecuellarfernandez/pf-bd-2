package org.proyecto.dao;

import org.proyecto.dto.Crimen;

import java.util.ArrayList;

public abstract class CrimenDao {
    public abstract void pro_insert(Crimen obj) throws Exception;

    public abstract void insert(Crimen obj) throws Exception;
    public abstract void update(Crimen obj) throws Exception;
    public abstract void delete(String id) throws Exception;
    public abstract Crimen get(String id) throws Exception;
    public abstract ArrayList<Crimen> getList() throws Exception;
}
