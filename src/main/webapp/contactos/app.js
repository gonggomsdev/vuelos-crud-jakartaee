const API_URL = window.location.origin + '/api/vuelos';

document.addEventListener('DOMContentLoaded', () => {
    const tablaVuelos = document.getElementById('tabla-vuelos');
    cargarVuelos();

    async function cargarVuelos() {
        try {
            console.log("Consultando API en:", API_URL);
            const respuesta = await fetch(API_URL);

            if (!respuesta.ok) {
                throw new Error(`Error HTTP: ${respuesta.status}`);
            }

            const vuelos = await respuesta.json();
            tablaVuelos.innerHTML = '';

            if (vuelos.length === 0) {
                tablaVuelos.innerHTML = '<tr><td colspan="10" style="text-align:center;">No hay vuelos disponibles</td></tr>';
                return;
            }

            vuelos.forEach(v => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td data-label="ID">${v.id}</td>
                    <td data-label="Vuelo"><span class="badge-vuelo">${v.numeroVuelo}</span></td>
                    <td data-label="Hora">${v.horaSalida || '--:--'}</td>
                    <td data-label="Compañía">${v.nombreEmpresa}</td>
                    <td data-label="Avión">${v.modeloAvion}</td>
                    <td data-label="Aeropuerto">${v.aeropuertoOrigen}</td>
                    <td data-label="Term.">${v.terminalOrigen}</td>
                    <td data-label="Puerta">${v.puertaOrigen}</td>
                    <td data-label="Origen">${v.ciudadOrigen}</td>
                    <td data-label="Destino">${v.ciudadDestino}</td>
                `;
                tablaVuelos.appendChild(tr);
            });

        } catch (error) {
            console.error("Error al cargar vuelos:", error);
            tablaVuelos.innerHTML = `
                <tr>
                    <td colspan="10" style="text-align:center; color:red; padding: 20px;">
                        Error al conectar con el servidor. <br>
                        <small>${error.message}</small>
                    </td>
                </tr>`;
        }
    }
});