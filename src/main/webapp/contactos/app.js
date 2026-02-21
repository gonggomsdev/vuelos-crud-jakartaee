// Ajusta el contexto si tu aplicación no está en la raíz.
// Por ejemplo, si el WAR es contactos-crud-jakartaee, y lo despliegas tal cual,
// normalmente la URL base será: /contactos-crud-jakartaee/api/contactos
const API_URL = window.location.pathname.includes('/contactos-crud-jakartaee/api/contactos')
    ? window.location.pathname.split('/contactos-crud-jakartaee/api/contactos')[0] + '/contactos-crud-jakartaee/api/contactos'
    : '/contactos-crud-jakartaee/api/contactos';

document.addEventListener('DOMContentLoaded', () => {
    const tablaBody       = document.getElementById('tabla-contactos');
    const form            = document.getElementById('form-contacto');
    const inputId         = document.getElementById('contacto-id');
    const inputNombre     = document.getElementById('contacto-nombre');
    const inputTelefono   = document.getElementById('contacto-telefono');
    const formTitle       = document.getElementById('form-title');
    const btnCancelar     = document.getElementById('btn-cancelar');

    cargarContactos();

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const id       = inputId.value;
        const nombre   = inputNombre.value.trim();
        const telefono = inputTelefono.value.trim();

        if (!nombre || !telefono) {
            alert('Nombre y teléfono son obligatorios');
            return;
        }

        const contacto = {
            id: id ? parseInt(id) : null,
            nombre: nombre,
            telefono: parseInt(telefono)
        };

        try {
            if (id) {
                await actualizarContacto(contacto);
            } else {
                await crearContacto(contacto);
            }

            await cargarContactos();
            resetFormulario();

        } catch (error) {
            console.error(error);
            alert('Error guardando el contacto');
        }
    });

    btnCancelar.addEventListener('click', () => {
        resetFormulario();
    });

    async function cargarContactos() {
        try {
            const respuesta = await fetch(API_URL);
            if (!respuesta.ok) {
                throw new Error('Error al cargar contactos');
            }
            const contactos = await respuesta.json();
            pintarTabla(contactos);
        } catch (error) {
            console.error(error);
            alert('No se pudieron cargar los contactos');
        }
    }

    function pintarTabla(contactos) {
        tablaBody.innerHTML = '';

        contactos.forEach(contacto => {
            const tr = document.createElement('tr');

            const tdId       = document.createElement('td');
            const tdNombre   = document.createElement('td');
            const tdTelefono = document.createElement('td');
            const tdAcciones = document.createElement('td');

            tdId.textContent       = contacto.id;
            tdNombre.textContent   = contacto.nombre;
            tdTelefono.textContent = contacto.telefono;

            tdAcciones.classList.add('acciones');

            const btnEditar = document.createElement('button');
            btnEditar.textContent = 'Editar';
            btnEditar.addEventListener('click', () => {
                cargarEnFormulario(contacto);
            });

            const btnBorrar = document.createElement('button');
            btnBorrar.textContent = 'Eliminar';
            btnBorrar.addEventListener('click', async () => {
                const confirmar = confirm(`¿Eliminar contacto "${contacto.nombre}"?`);
                if (confirmar) {
                    try {
                        await eliminarContacto(contacto.id);
                        await cargarContactos();
                    } catch (error) {
                        console.error(error);
                        alert('Error eliminando el contacto');
                    }
                }
            });

            tdAcciones.appendChild(btnEditar);
            tdAcciones.appendChild(btnBorrar);

            tr.appendChild(tdId);
            tr.appendChild(tdNombre);
            tr.appendChild(tdTelefono);
            tr.appendChild(tdAcciones);

            tablaBody.appendChild(tr);
        });
    }

    function cargarEnFormulario(contacto) {
        inputId.value       = contacto.id;
        inputNombre.value   = contacto.nombre;
        inputTelefono.value = contacto.telefono;

        formTitle.textContent = 'Editar contacto';
        btnCancelar.style.display = 'inline-block';
    }

    function resetFormulario() {
        inputId.value       = '';
        inputNombre.value   = '';
        inputTelefono.value = '';
        formTitle.textContent = 'Nuevo contacto';
        btnCancelar.style.display = 'none';
    }

    async function crearContacto(contacto) {
        const respuesta = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(contacto)
        });

        if (!respuesta.ok) {
            throw new Error('Error al crear contacto');
        }
        return respuesta.json();
    }

    async function actualizarContacto(contacto) {
        const url = `${API_URL}/${contacto.id}`;
        const respuesta = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(contacto)
        });

        if (!respuesta.ok) {
            throw new Error('Error al actualizar contacto');
        }
        return respuesta.json();
    }

    async function eliminarContacto(id) {
        const url = `${API_URL}/${id}`;
        const respuesta = await fetch(url, {
            method: 'DELETE'
        });

        if (!respuesta.ok) {
            throw new Error('Error al eliminar contacto');
        }
    }
});
