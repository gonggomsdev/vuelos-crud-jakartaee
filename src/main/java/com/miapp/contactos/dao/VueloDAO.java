package com.miapp.contactos.dao;

import com.miapp.contactos.dto.VueloDTO;
import java.util.List;

public interface VueloDAO {

    List<VueloDTO> findAllVuelosCompletos();
    VueloDTO findVueloById(Integer id);
}