package com.miapp.contactos.web;

import com.miapp.contactos.dto.VueloDTO;
import com.miapp.contactos.service.VueloService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/vuelos")
public class VueloResource {

    @Inject
    private VueloService vueloService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VueloDTO> listar() {
        return vueloService.listarVuelosParaLaWeb();
    }
}