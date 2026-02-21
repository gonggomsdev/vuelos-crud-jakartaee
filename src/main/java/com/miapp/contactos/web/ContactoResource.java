package com.miapp.contactos.web;

import com.miapp.contactos.dto.ContactoDTO;
import com.miapp.contactos.model.Contacto;
import com.miapp.contactos.service.ContactoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@Path("/contactos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactoResource {

    @Inject
    private ContactoService servicio;

    @GET
    public List<ContactoDTO> listar() {
        return servicio.listarTodos()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public ContactoDTO obtener(@PathParam("id") Integer id) {
        Contacto c = servicio.buscarPorId(id);
        if (c == null) {
            throw new NotFoundException("Contacto no encontrado");
        }
        return toDTO(c);
    }

    @POST
    public ContactoDTO crear(ContactoDTO dto) {
        Contacto c = toEntity(dto);
        servicio.crear(c);
        return toDTO(c);
    }

    @PUT
    @Path("/{id}")
    public ContactoDTO actualizar(@PathParam("id") Integer id, ContactoDTO dto) {
        Contacto existente = servicio.buscarPorId(id);
        if (existente == null) {
            throw new NotFoundException("Contacto no encontrado");
        }

        existente.setNombre(dto.getNombre());
        existente.setTelefono(dto.getTelefono());
        Contacto actualizado = servicio.actualizar(existente);

        return toDTO(actualizado);
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Integer id) {
        servicio.eliminar(id);
    }

    private ContactoDTO toDTO(Contacto c) {
        return new ContactoDTO(c.getId(), c.getNombre(), c.getTelefono());
    }

    private Contacto toEntity(ContactoDTO dto) {
        Contacto c = new Contacto();
        c.setId(dto.getId());
        c.setNombre(dto.getNombre());
        c.setTelefono(dto.getTelefono());
        return c;
    }
}
