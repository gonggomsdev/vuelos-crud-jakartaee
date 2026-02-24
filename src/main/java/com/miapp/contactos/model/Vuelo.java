package com.miapp.contactos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vuelos")
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vuelo")
    private Integer id;

    @Column(name = "numero_vuelo")
    private String numeroVuelo;

    @Column(name = "id_avion")
    private Integer idAvion;

    @Column(name = "id_puerta_origen")
    private Integer idPuertaOrigen;

    @Column(name = "id_puerta_destino")
    private Integer idPuertaDestino;

    @Column(name = "fecha_salida")
    private LocalDateTime fechaSalida;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public Integer getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(Integer idAvion) {
        this.idAvion = idAvion;
    }

    public Integer getIdPuertaOrigen() {
        return idPuertaOrigen;
    }

    public void setIdPuertaOrigen(Integer idPuertaOrigen) {
        this.idPuertaOrigen = idPuertaOrigen;
    }

    public Integer getIdPuertaDestino() {
        return idPuertaDestino;
    }

    public void setIdPuertaDestino(Integer idPuertaDestino) {
        this.idPuertaDestino = idPuertaDestino;
    }
}