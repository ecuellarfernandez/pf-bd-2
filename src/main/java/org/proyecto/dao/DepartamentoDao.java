package org.proyecto.dao;

import org.proyecto.dto.Departamento;

import java.util.ArrayList;

public abstract class DepartamentoDao {
    public abstract void insert(Departamento obj) throws Exception;
    public abstract void update(Departamento obj) throws Exception;
    public abstract void delete(String id) throws Exception;
    public abstract Departamento get(String id) throws Exception;
    public abstract ArrayList<Departamento> getList() throws Exception;

}
