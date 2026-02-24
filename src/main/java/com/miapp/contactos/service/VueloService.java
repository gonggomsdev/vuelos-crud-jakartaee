package com.miapp.contactos.service;

import com.miapp.contactos.dao.VueloDAO;
import com.miapp.contactos.dto.VueloDTO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;

@RequestScoped
public class VueloService {

    @Inject
    private VueloDAO vueloDAO;

    public List<VueloDTO> listarVuelosParaLaWeb() {
        return vueloDAO.findAllVuelosCompletos();
    }
}