package com.miapp.contactos.dao;

import com.miapp.contactos.model.Contacto;

import java.util.List;

public interface ContactoDAO {

    Contacto findById(Integer id);

    List<Contacto> findAll();

    void insert(Contacto contacto);

    Contacto update(Contacto contacto);

    void delete(Integer id);
}
