import {
  crearAutor,
  buscarPorId,
  actualizarAutor,
  eliminarAutor
} from '../Autor/autorService.js';

// 🔍 Buscar Autor
export function renderBuscarAutor(container) {
  container.innerHTML = `
    <h2>🔍 Buscar Autor</h2>
    <form id="formBuscarAutor" class="form-busqueda">
      <input type="number" id="buscarIdAutor" placeholder="Ingrese ID de autor" required />
      <button type="submit">Buscar</button>
    </form>
    <div id="resultadoBuscarAutor"></div>
  `;

  document.getElementById("formBuscarAutor").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("buscarIdAutor").value;
    const resultado = document.getElementById("resultadoBuscarAutor");
    resultado.innerHTML = "";

    const autor = await buscarPorId(id);
    if (autor) {
      resultado.innerHTML = `
        <div class="user-card">
          <div class="user-info">
            <h3>${autor.nombre} ${autor.apellido || ""}</h3>
            <p><strong>ID:</strong> ${autor.id}</p>
          </div>
        </div>
      `;
    } else {
      resultado.innerHTML = `<p class="error-message">⚠️ Autor no encontrado.</p>`;
    }

    e.target.reset();
  });
}

// 🗑️ Eliminar Autor
export function renderEliminarAutor(container) {
  container.innerHTML = `
    <h2>🗑️ Eliminar Autor</h2>
    <form id="formEliminarAutor" class="form-busqueda">
      <input type="number" id="buscarIdEliminarAutor" placeholder="Ingrese ID de autor" required />
      <button type="submit">Buscar</button>
    </form>
    <div id="resultadoEliminarAutor"></div>
  `;

  document.getElementById("formEliminarAutor").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("buscarIdEliminarAutor").value;
    const resultado = document.getElementById("resultadoEliminarAutor");
    resultado.innerHTML = "";

    const autor = await buscarPorId(id);
    if (autor) {
      resultado.innerHTML = `
        <div class="user-card">
          <div class="user-info">
            <h3>${autor.nombre} ${autor.apellido || ""}</h3>
            <p><strong>ID:</strong> ${autor.id}</p>
            <button id="btnConfirmarEliminarAutor" class="formbold-btn" style="background-color:#e74c3c; margin-top:15px;">Eliminar Autor</button>
          </div>
        </div>
      `;

      document.getElementById("btnConfirmarEliminarAutor").addEventListener("click", async () => {
        const confirmar = confirm(`¿Deseas eliminar a ${autor.nombre} ${autor.apellido}?`);
        if (!confirmar) return;

        try {
          await eliminarAutor(id);
          resultado.innerHTML = `<p class="success-message">✅ Autor eliminado correctamente.</p>`;
        } catch (error) {
          resultado.innerHTML = `<p class="error-message">⚠️ Error al eliminar el autor: ${error.message}</p>`;
        }
      });
    } else {
      resultado.innerHTML = `<p class="error-message">⚠️ Autor no encontrado.</p>`;
    }

    e.target.reset();
  });
}

// ✏️ Actualizar Autor
export function renderActualizarAutor(container) {
  container.innerHTML = `
    <h2>✏️ Actualizar Autor</h2>
    <form id="formBuscarActualizarAutor" class="form-busqueda">
      <input type="number" id="buscarIdActualizarAutor" placeholder="Ingrese ID de autor" required />
      <button type="submit">Buscar</button>
    </form>
    <div id="resultadoActualizarAutor"></div>
  `;

  document.getElementById("formBuscarActualizarAutor").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("buscarIdActualizarAutor").value;
    const resultado = document.getElementById("resultadoActualizarAutor");
    resultado.innerHTML = "";

    try {
      const autor = await buscarPorId(id);
      if (!autor) {
        resultado.innerHTML = `<p class="error-message">⚠️ Autor no encontrado.</p>`;
        return;
      }

      resultado.innerHTML = `
        <form id="formActualizarAutor" class="form-actualizar">
          <label>Nombre:<br /><input type="text" id="nombreActualizarAutor" value="${autor.nombre}" required /></label><br />
          <label>Apellido:<br /><input type="text" id="apellidoActualizarAutor" value="${autor.apellido || ''}" /></label><br />
          <button type="submit" class="formbold-btn" style="margin-top:15px;">Actualizar Autor</button>
        </form>
        <div id="mensajeActualizarAutor"></div>
      `;

      document.getElementById("formActualizarAutor").addEventListener("submit", async (ev) => {
        ev.preventDefault();

        const autorActualizado = {
          id: autor.id,
          nombre: document.getElementById("nombreActualizarAutor").value.trim(),
          apellido: document.getElementById("apellidoActualizarAutor").value.trim()
        };

        const mensaje = document.getElementById("mensajeActualizarAutor");
        mensaje.innerHTML = "";

        try {
          await actualizarAutor(autorActualizado);
          mensaje.innerHTML = `<p class="success-message">✅ Autor actualizado correctamente.</p>`;
        } catch (error) {
          mensaje.innerHTML = `<p class="error-message">⚠️ Error al actualizar autor: ${error.message}</p>`;
        }
      });

    } catch (error) {
      resultado.innerHTML = `<p class="error-message">⚠️ Error: ${error.message}</p>`;
    }
  });
}


// ➕ Crear Autor
export function renderCrearAutor(container) {
  container.innerHTML = `
    <h2>➕ Crear Autor</h2>
    <form id="formCrearAutor" class="form-crear">
      <label>Nombre:<br /><input type="text" id="nombreNuevoAutor" required /></label><br />
      <label>Apellido:<br /><input type="text" id="apellidoNuevoAutor" /></label><br />
      <button type="submit" class="formbold-btn">Registrar Autor</button>
    </form>
    <div id="mensajeCrearAutor"></div>
  `;

  document.getElementById("formCrearAutor").addEventListener("submit", async (e) => {
    e.preventDefault();

    const autor = {
      nombre: document.getElementById("nombreNuevoAutor").value.trim(),
      apellido: document.getElementById("apellidoNuevoAutor").value.trim(),
    };

    const mensaje = document.getElementById("mensajeCrearAutor");
    mensaje.innerHTML = "";

    try {
      await crearAutor(autor);
      mensaje.innerHTML = `<p class="success-message">✅ Autor registrado exitosamente.</p>`;
      e.target.reset();
    } catch (error) {
      mensaje.innerHTML = `<p class="error-message">⚠️ Error al registrar autor: ${error.message}</p>`;
    }
  });
}