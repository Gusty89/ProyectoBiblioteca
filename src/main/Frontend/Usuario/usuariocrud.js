import { obtenerUsuarioPorId, eliminarUsuario, actualizarUsuario } from './usuarioService.js';

// 🔍 Buscar Usuario
export function renderBuscarUsuario(container) {
  container.innerHTML = `
    <h2>🔍 Buscar Usuario</h2>
    <form id="formBuscarUsuario" class="form-busqueda">
      <input type="number" id="buscarIdUsuario" placeholder="Ingrese ID de usuario" required />
      <button type="submit">Buscar</button>
    </form>
    <div id="resultadoBuscarUsuario"></div>
  `;

  document.getElementById("formBuscarUsuario").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("buscarIdUsuario").value;
    const resultado = document.getElementById("resultadoBuscarUsuario");
    resultado.innerHTML = "";

    const user = await obtenerUsuarioPorId(id);
    if (user) {
      resultado.innerHTML = `
        <div class="user-card">
          <div class="user-info">
            <h3>${user.nombre} ${user.apellido}</h3>
            <p><strong>ID:</strong> ${user.id}</p>
            <p><strong>Email:</strong> ${user.email}</p>
            <p><strong>Teléfono:</strong> ${user.telefono}</p>
            <p><strong>Dirección:</strong> ${user.direccion}</p>
            <p><strong>Fecha de Registro:</strong> ${user.fechaRegistro}</p>
          </div>
        </div>
      `;
    } else {
      resultado.innerHTML = `<p class="error-message">⚠️ Usuario no encontrado.</p>`;
    }

    e.target.reset();
  });
}

// 🗑️ Eliminar Usuario
export function renderEliminarUsuario(container) {
  container.innerHTML = `
    <h2>🗑️ Eliminar Usuario</h2>
    <form id="formEliminarUsuario" class="form-busqueda">
      <input type="number" id="buscarIdEliminarUsuario" placeholder="Ingrese ID de usuario" required />
      <button type="submit">Buscar</button>
    </form>
    <div id="resultadoEliminarUsuario"></div>
  `;

  document.getElementById("formEliminarUsuario").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("buscarIdEliminarUsuario").value;
    const resultado = document.getElementById("resultadoEliminarUsuario");
    resultado.innerHTML = "";

    const user = await obtenerUsuarioPorId(id);
    if (user) {
      resultado.innerHTML = `
        <div class="user-card">
          <div class="user-info">
            <h3>${user.nombre} ${user.apellido}</h3>
            <p><strong>ID:</strong> ${user.id}</p>
            <p><strong>Email:</strong> ${user.email}</p>
            <p><strong>Teléfono:</strong> ${user.telefono}</p>
            <p><strong>Dirección:</strong> ${user.direccion}</p>
            <p><strong>Fecha de Registro:</strong> ${user.fechaRegistro}</p>
            <button id="btnConfirmarEliminarUsuario" class="formbold-btn" style="background-color:#e74c3c; margin-top:15px;">Eliminar Usuario</button>
          </div>
        </div>
      `;

      document.getElementById("btnConfirmarEliminarUsuario").addEventListener("click", async () => {
        const confirmar = confirm(`¿Deseas eliminar a ${user.nombre} ${user.apellido}?`);
        if (!confirmar) return;

        try {
          await eliminarUsuario(id);
          resultado.innerHTML = `<p class="success-message">✅ Usuario eliminado correctamente.</p>`;
        } catch (error) {
          resultado.innerHTML = `<p class="error-message">⚠️ Error al eliminar el usuario: ${error.message}</p>`;
        }
      });
    } else {
      resultado.innerHTML = `<p class="error-message">⚠️ Usuario no encontrado.</p>`;
    }

    e.target.reset();
  });
}

// ✏️ Actualizar Usuario
export function renderActualizarUsuario(container) {
  container.innerHTML = `
    <h2>✏️ Actualizar Usuario</h2>
    <form id="formBuscarActualizarUsuario" class="form-busqueda">
      <input type="number" id="buscarIdActualizarUsuario" placeholder="Ingrese ID de usuario" required />
      <button type="submit">Buscar</button>
    </form>
    <div id="resultadoActualizarUsuario"></div>
  `;

  document.getElementById("formBuscarActualizarUsuario").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("buscarIdActualizarUsuario").value;
    const resultado = document.getElementById("resultadoActualizarUsuario");
    resultado.innerHTML = "";

    try {
      const user = await obtenerUsuarioPorId(id);
      if (!user) {
        resultado.innerHTML = `<p class="error-message">⚠️ Usuario no encontrado.</p>`;
        return;
      }

      resultado.innerHTML = `
        <form id="formActualizarUsuario" class="form-actualizar">
          <label>Nombre:<br /><input type="text" id="nombreActualizarUsuario" value="${user.nombre}" required /></label><br />
          <label>Apellido:<br /><input type="text" id="apellidoActualizarUsuario" value="${user.apellido}" required /></label><br />
          <label>Email:<br /><input type="email" id="emailActualizarUsuario" value="${user.email}" required /></label><br />
          <label>Teléfono:<br /><input type="text" id="telefonoActualizarUsuario" value="${user.telefono}" /></label><br />
          <label>Dirección:<br /><input type="text" id="direccionActualizarUsuario" value="${user.direccion}" /></label><br />
          <button type="submit" class="formbold-btn" style="margin-top:15px;">Actualizar Usuario</button>
        </form>
        <div id="mensajeActualizarUsuario"></div>
      `;

      document.getElementById("formActualizarUsuario").addEventListener("submit", async (ev) => {
        ev.preventDefault();

        const usuarioActualizado = {
          id: user.id,
          nombre: document.getElementById("nombreActualizarUsuario").value.trim(),
          apellido: document.getElementById("apellidoActualizarUsuario").value.trim(),
          email: document.getElementById("emailActualizarUsuario").value.trim(),
          telefono: document.getElementById("telefonoActualizarUsuario").value.trim(),
          direccion: document.getElementById("direccionActualizarUsuario").value.trim(),
        };
        await actualizarUsuario(usuarioActualizado);
        const mensaje = document.getElementById("mensajeActualizarUsuario");
        mensaje.innerHTML = "";

        try {
          await actualizarUsuario(usuarioActualizado);
          mensaje.innerHTML = `<p class="success-message">✅ Usuario actualizado correctamente.</p>`;
        } catch (error) {
          mensaje.innerHTML = `<p class="error-message">⚠️ Error al actualizar: ${error.message}</p>`;
        }
      });

    } catch (error) {
      resultado.innerHTML = `<p class="error-message">⚠️ Error: ${error.message}</p>`;
    }

    
  });
}
