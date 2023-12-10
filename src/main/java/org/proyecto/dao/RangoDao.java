package org.proyecto.dao;

import org.proyecto.dto.Rango;

import java.util.ArrayList;

public abstract class RangoDao {

        public abstract void insert(Rango obj) throws Exception;

        public abstract void update(Rango obj) throws Exception;

        public abstract void delete(String id) throws Exception;

        public abstract ArrayList<Rango> getList() throws Exception;

        public abstract Rango get(String id) throws Exception;
}
