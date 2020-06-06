/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.modelo.Producto;
import java.util.List;

public interface ProductoDAO {
    public void insert(Producto producto)throws Exception;
    public void update(Producto producto)throws Exception;
    public void delete(int id) throws Exception;
    public Producto getById(int id) throws Exception;
    public List<Producto> getAll() throws Exception;
    
}
