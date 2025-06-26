import {
  crearLibro,
  buscarPorId,
  obtenerLibros,
  actualizarLibro,
  eliminarLibro
} from '../Libro/libroService.js';


// ➕ Crear Libro
export function renderCrearLibro(container) {
  container.innerHTML = `
    <h2>➕ Crear Libro</h2>
    <form id="formCrearLibro" class="form-crear">
      <label>Título:<br /><input type="text" id="tituloNuevoLibro" required /></label><br />
      <label>Año:<br /><input type="text" id="anioNuevoLibro" /></label><br />
      <label>Estado:<br /><input type="text" id="estadoNuevoLibro" /></label><br />
      <button type="submit" class="formbold-btn">Registrar Libro</button>
    </form>
    <div id="mensajeCrearLibro"></div>
  `;

  document.getElementById("formCrearLibro").addEventListener("submit", async (e) => {
    e.preventDefault();

    const libro = {
      titulo: document.getElementById("tituloNuevoLibro").value.trim(),
      anio: document.getElementById("anioNuevoLibro").value.trim(),
      estado: document.getElementById("estadoNuevoLibro").value.trim()
    };

    const mensaje = document.getElementById("mensajeCrearLibro");
    mensaje.innerHTML = "";

    try {
      await crearLibro(libro);
      mensaje.innerHTML = `<p class="success-message">✅ Libro registrado exitosamente.</p>`;
      e.target.reset();
    } catch (error) {
      mensaje.innerHTML = `<p class="error-message">⚠️ Error al registrar libro: ${error.message}</p>`;
    }
  });
}


// 🔍 Buscar Libro
export function renderBuscarLibro(container) {
  container.innerHTML = `
    <h2>🔍 Buscar Libro</h2>
    <form id="formBuscarLibro" class="form-busqueda">
      <input type="number" id="buscarIdLibro" placeholder="Ingrese ID de libro" required />
      <button type="submit">Buscar</button>
    </form>
    <div id="resultadoBuscarLibro"></div>
  `;

  document.getElementById("formBuscarLibro").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("buscarIdLibro").value;
    const resultado = document.getElementById("resultadoBuscarLibro");
    resultado.innerHTML = "";

    const libro = await buscarPorId(id);
    if (libro) {
      resultado.innerHTML = `
        <div class="user-card">
          <div class="user-info">
            <h3>${libro.titulo}</h3>
            <p><strong>ID:</strong> ${libro.id}</p>
            <p><strong>Año:</strong> ${libro.anio}</p>
            <p><strong>Estado:</strong> ${libro.estado}</p>
          </div>
        </div>
      `;
    } else {
      resultado.innerHTML = `<p class="error-message">⚠️ Libro no encontrado.</p>`;
    }

    e.target.reset();
  });
}


// 📚 Listar todos los libros
export function renderListarLibros(container) {
  container.innerHTML = `
    <h2>📚 Listado de Libros</h2>
    <button id="btnCargarLibros" class="formbold-btn">Cargar Libros</button>
    <div id="listaLibros" class="lista-entidades"></div>
  `;

  document.getElementById("btnCargarLibros").addEventListener("click", async () => {
    const contenedorLista = document.getElementById("listaLibros");
    contenedorLista.innerHTML = "";

    try {
      const libros = await obtenerLibros();

      if (libros.length === 0) {
        contenedorLista.innerHTML = `<p class="error-message">⚠️ No hay libros registrados.</p>`;
        return;
      }

      libros.forEach(libro => {
        contenedorLista.innerHTML += `
          <div class="user-card">
            <div class="user-info">
              <h3>${libro.titulo}</h3>
              <p><strong>ID:</strong> ${libro.id}</p>
              <p><strong>Año:</strong> ${libro.anio}</p>
              <p><strong>Estado:</strong> ${libro.estado}</p>
              <p><strong>Editorial:</strong> ${libro.editorial?.nombre || "Sin editorial"}</p>
              <p><strong>Autores:</strong> ${libro.autores?.map(a => a.nombre + ' ' + a.apellido).join(', ') || "Sin autores"}</p>
            </div>
          </div>
        `;
      });
    } catch (error) {
      contenedorLista.innerHTML = `<p class="error-message">⚠️ Error al obtener libros: ${error.message}</p>`;
    }
  });
}


// ✏️ Actualizar Libro
export function renderActualizarLibro(container) {
  container.innerHTML = `
    <h2>✏️ Actualizar Libro</h2>
    <form id="formBuscarActualizarLibro" class="form-busqueda">
      <input type="number" id="buscarIdActualizarLibro" placeholder="Ingrese ID de libro" required />
      <button type="submit">Buscar</button>
    </form>
    <div id="resultadoActualizarLibro"></div>
  `;

  document.getElementById("formBuscarActualizarLibro").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("buscarIdActualizarLibro").value;
    const resultado = document.getElementById("resultadoActualizarLibro");
    resultado.innerHTML = "";

    try {
      const libro = await buscarPorId(id);
      if (!libro) {
        resultado.innerHTML = `<p class="error-message">⚠️ Libro no encontrado.</p>`;
        return;
      }

      resultado.innerHTML = `
        <form id="formActualizarLibro" class="form-actualizar">
          <label>Título:<br /><input type="text" id="tituloActualizarLibro" value="${libro.titulo}" required /></label><br />
          <label>Año:<br /><input type="text" id="anioActualizarLibro" value="${libro.anio}" /></label><br />
          <label>Estado:<br /><input type="text" id="estadoActualizarLibro" value="${libro.estado}" /></label><br />
          <label>Editorial:<br /><input type="text" id="editorialActualizarLibro" value="${libro.editorial}" /></label><br />
          <label>Autor/es:<br /><input type="text" id="autoresActualizarLibro" value="${libro.autores}" /></label><br />
          <button type="submit" class="formbold-btn" style="margin-top:15px;">Actualizar Libro</button>
        </form>
        <div id="mensajeActualizarLibro"></div>
      `;

      document.getElementById("formActualizarLibro").addEventListener("submit", async (ev) => {
        ev.preventDefault();

        const libroActualizado = {
          id: libro.id,
          titulo: document.getElementById("tituloActualizarLibro").value.trim(),
          anio: document.getElementById("anioActualizarLibro").value.trim(),
          estado: document.getElementById("estadoActualizarLibro").value.trim(),
          editorial: document.getElementById("editorialActualizarLibro").value.trim(),
          autores: document.getElementById("autoresctualizarLibro").value.trim()
        };

        const mensaje = document.getElementById("mensajeActualizarLibro");
        mensaje.innerHTML = "";

        try {
          await actualizarLibro(libroActualizado);
          mensaje.innerHTML = `<p class="success-message">✅ Libro actualizado correctamente.</p>`;
        } catch (error) {
          mensaje.innerHTML = `<p class="error-message">⚠️ Error al actualizar libro: ${error.message}</p>`;
        }
      });

    } catch (error) {
      resultado.innerHTML = `<p class="error-message">⚠️ Error: ${error.message}</p>`;
    }
  });
}


// 🗑️ Eliminar Libro
export function renderEliminarLibro(container) {
  container.innerHTML = `
    <h2>🗑️ Eliminar Libro</h2>
    <form id="formEliminarLibro" class="form-busqueda">
      <input type="number" id="buscarIdEliminarLibro" placeholder="Ingrese ID de libro" required />
      <button type="submit">Buscar</button>
    </form>
    <div id="resultadoEliminarLibro"></div>
  `;

  document.getElementById("formEliminarLibro").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("buscarIdEliminarLibro").value;
    const resultado = document.getElementById("resultadoEliminarLibro");
    resultado.innerHTML = "";

    const libro = await buscarPorId(id);
    if (libro) {
      resultado.innerHTML = `
        <div class="user-card">
          <div class="user-info">
            <h3>${libro.titulo}</h3>
            <p><strong>ID:</strong> ${libro.id}</p>
            <button id="btnConfirmarEliminarLibro" class="formbold-btn" style="background-color:#e74c3c; margin-top:15px;">Eliminar Libro</button>
          </div>
        </div>
      `;

      document.getElementById("btnConfirmarEliminarLibro").addEventListener("click", async () => {
        const confirmar = confirm(`¿Deseas eliminar el libro "${libro.titulo}"?`);
        if (!confirmar) return;

        try {
          await eliminarLibro(id);
          resultado.innerHTML = `<p class="success-message">✅ Libro eliminado correctamente.</p>`;
        } catch (error) {
          resultado.innerHTML = `<p class="error-message">⚠️ Error al eliminar el libro: ${error.message}</p>`;
        }
      });
    } else {
      resultado.innerHTML = `<p class="error-message">⚠️ Libro no encontrado.</p>`;
    }

    e.target.reset();
  });
}
