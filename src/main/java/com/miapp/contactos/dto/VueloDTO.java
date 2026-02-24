package com.miapp.contactos.dto;

public class VueloDTO {
    private Integer id;
    private String numeroVuelo;
    private String nombreEmpresa;
    private String modeloAvion;
    private String aeropuertoOrigen;
    private String ciudadOrigen;
    private String terminalOrigen;
    private String puertaOrigen;
    private String ciudadDestino;
    private String horaSalida;

    public VueloDTO() {}

    public VueloDTO(Integer id, String numeroVuelo, String nombreEmpresa, String modeloAvion,
                    String aeropuertoOrigen, String ciudadOrigen, String terminalOrigen,
                    String puertaOrigen, String ciudadDestino, String horaSalida) {
        this.id = id;
        this.numeroVuelo = numeroVuelo;
        this.nombreEmpresa = nombreEmpresa;
        this.modeloAvion = modeloAvion;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.ciudadOrigen = ciudadOrigen;
        this.terminalOrigen = terminalOrigen;
        this.puertaOrigen = puertaOrigen;
        this.ciudadDestino = ciudadDestino;
        this.horaSalida = horaSalida;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNumeroVuelo() { return numeroVuelo; }
    public void setNumeroVuelo(String numeroVuelo) { this.numeroVuelo = numeroVuelo; }

    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }

    public String getModeloAvion() { return modeloAvion; }
    public void setModeloAvion(String modeloAvion) { this.modeloAvion = modeloAvion; }

    public String getAeropuertoOrigen() { return aeropuertoOrigen; }
    public void setAeropuertoOrigen(String aeropuertoOrigen) { this.aeropuertoOrigen = aeropuertoOrigen; }

    public String getCiudadOrigen() { return ciudadOrigen; }
    public void setCiudadOrigen(String ciudadOrigen) { this.ciudadOrigen = ciudadOrigen; }

    public String getTerminalOrigen() { return terminalOrigen; }
    public void setTerminalOrigen(String terminalOrigen) { this.terminalOrigen = terminalOrigen; }

    public String getPuertaOrigen() { return puertaOrigen; }
    public void setPuertaOrigen(String puertaOrigen) { this.puertaOrigen = puertaOrigen; }

    public String getCiudadDestino() { return ciudadDestino; }
    public void setCiudadDestino(String ciudadDestino) { this.ciudadDestino = ciudadDestino; }

    public String getHoraSalida() { return horaSalida; }
    public void setHoraSalida(String horaSalida) { this.horaSalida = horaSalida; }
}