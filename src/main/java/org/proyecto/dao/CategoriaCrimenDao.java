package org.proyecto.dao;

import org.proyecto.dto.CategoriaCrimen;

import java.util.ArrayList;

public abstract class CategoriaCrimenDao {
    public abstract void pro_insert(CategoriaCrimen obj) throws Exception;

    public abstract void insert(CategoriaCrimen obj) throws Exception;
    public abstract void update(CategoriaCrimen obj) throws Exception;
    public abstract void delete(String id) throws Exception;
    public abstract CategoriaCrimen get(String id) throws Exception;
    public abstract ArrayList<CategoriaCrimen> getList() throws Exception;
}
