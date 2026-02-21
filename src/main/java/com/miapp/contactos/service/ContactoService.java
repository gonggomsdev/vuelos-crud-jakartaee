package com.miapp.contactos.service;

import com.miapp.contactos.dao.ContactoDAO;
import com.miapp.contactos.model.Contacto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@RequestScoped
public class ContactoService {

    @Inject
    private ContactoDAO contactoDAO;

    public List<Contacto> listarTodos() {
        return contactoDAO.findAll();
    }

    public Contacto buscarPorId(Integer id) {
        return contactoDAO.findById(id);
    }

    @Transactional
    public Contacto crear(Contacto contacto) {
        contactoDAO.insert(contacto);
        return contacto;
    }

    @Transactional
    public Contacto actualizar(Contacto contacto) {
        return contactoDAO.update(contacto);
    }

    @Transactional
    public void eliminar(Integer id) {
        contactoDAO.delete(id);
    }
}
