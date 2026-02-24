package com.miapp.contactos.dao;

import com.miapp.contactos.dto.VueloDTO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class VueloDAOImpl implements VueloDAO {

    @PersistenceContext(unitName = "vuelosPU")
    private EntityManager em;

    @Override
    public List<VueloDTO> findAllVuelosCompletos() {
        String sql = "SELECT v.id_vuelo, v.numero_vuelo, c.nombre, a.modelo, " +
                "orig_aero.nombre, orig_aero.ciudad, t_ori.nombre, p_ori.codigo, " +
                "dest_aero.ciudad, v.fecha_salida " +
                "FROM vuelos v " +
                "INNER JOIN aviones a ON v.id_avion = a.id_avion " +
                "INNER JOIN companias c ON a.id_compania = c.id_compania " +
                "INNER JOIN puertas p_ori ON v.id_puerta_origen = p_ori.id_puerta " +
                "INNER JOIN terminales t_ori ON p_ori.id_terminal = t_ori.id_terminal " +
                "INNER JOIN aeropuertos orig_aero ON t_ori.id_aeropuerto = orig_aero.id_aeropuerto " +
                "INNER JOIN puertas p_des ON v.id_puerta_destino = p_des.id_puerta " +
                "INNER JOIN terminales t_des ON p_des.id_terminal = t_des.id_terminal " +
                "INNER JOIN aeropuertos dest_aero ON t_des.id_aeropuerto = dest_aero.id_aeropuerto";

        Query q = em.createNativeQuery(sql);
        List<Object[]> resultados = q.getResultList();
        List<VueloDTO> dtos = new ArrayList<>();

        for (Object[] fila : resultados) {
            // Obtenemos el objeto tal cual viene de la BD (sea LocalDateTime o Timestamp)
            // y lo convertimos a String directamente para no perder información
            String fechaCompleta = (fila[9] != null) ? fila[9].toString() : "N/A";

            dtos.add(new VueloDTO(
                    ((Number) fila[0]).intValue(),
                    (String) fila[1],
                    (String) fila[2],
                    (String) fila[3],
                    (String) fila[4],
                    (String) fila[5],
                    (String) fila[6],
                    (String) fila[7],
                    (String) fila[8],
                    fechaCompleta // Pasamos el String tal cual salió de la BD
            ));
        }
        return dtos;
    }

    @Override
    public VueloDTO findVueloById(Integer id) {
        return findAllVuelosCompletos().stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}