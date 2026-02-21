package com.miapp.contactos.dao;

import com.miapp.contactos.model.Contacto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@RequestScoped
public class ContactoDAOImpl implements ContactoDAO {

    @PersistenceContext(unitName = "contactosPU")
    private EntityManager em;

    @Override
    public Contacto findById(Integer id) {
        return em.find(Contacto.class, id);
    }

    @Override
    public List<Contacto> findAll() {
        return em.createQuery("SELECT c FROM Contacto c", Contacto.class)
                .getResultList();
    }

    @Override
    public void insert(Contacto contacto) {
        em.persist(contacto);
    }

    @Override
    public Contacto update(Contacto contacto) {
        return em.merge(contacto);
    }

    @Override
    public void delete(Integer id) {
        Contacto c = em.find(Contacto.class, id);
        if (c != null) {
            em.remove(c);
        }
    }
}
