const vista = document.getElementById('vista-actual');

const secciones = {
  usuario: '🧑‍💼 Gestión de Usuarios - aquí va el CRUD de usuarios',
  autor: '🖋️ Gestión de Autores - aquí va el CRUD de autores',
  libro: '📖 Gestión de Libros - aquí va el CRUD de libros',
  editorial: '🏛️ Gestión de Editoriales - aquí va el CRUD de editoriales',
  prestamo: '📄 Gestión de Préstamos - aquí va el CRUD de préstamos',
};

// ✅ Agregamos el evento a cada botón del panel
document.querySelectorAll('button[data-section]').forEach(btn => {
  btn.addEventListener('click', () => {
    const key = btn.dataset.section;
    cargarVista(key); // Llama a la función que carga la vista correspondiente
  });
});

// ✅ Lógica para cerrar sesión
document.getElementById('logout').addEventListener('click', () => {
  if (confirm('¿Deseas cerrar sesión?')) {
    // Limpiar sesión y redirigir
    alert('Sesión cerrada');
    window.location.href = '../Usuario/login.html';
  }
});

// ✅ Función que carga dinámicamente la vista según la sección
async function cargarVista(seccion) {
 switch (seccion) {
  case 'usuario':
   const moduloUsuario = await import('../Usuario/usuariocrud.js');
    vista.innerHTML = `
    <div id="buscarUsuarioContainer"></div>
    <div id="eliminarUsuarioContainer"></div>
    <div id="actualizarUsuarioContainer"></div>
`;
    moduloUsuario.renderBuscarUsuario(document.getElementById('buscarUsuarioContainer'));
    moduloUsuario.renderEliminarUsuario(document.getElementById('eliminarUsuarioContainer'));
    moduloUsuario.renderActualizarUsuario(document.getElementById('actualizarUsuarioContainer'));
    break;

    case 'autor':
      const moduloAutor = await import('../Autor/autorcrud.js');
    vista.innerHTML = `
    <div id="crearAutorContainer"></div>
    <div id="buscarAutorContainer"></div>
    <div id="buscarListaAutorContainer"></div>
    <div id="actualizarAutorContainer"></div>
    <div id="eliminarAutorContainer"></div>
`;
    
    moduloAutor.renderCrearAutor(document.getElementById('crearAutorContainer'));
    moduloAutor.renderBuscarAutor(document.getElementById('buscarAutorContainer'));
    moduloAutor.renderListarAutores(document.getElementById('buscarListaAutorContainer'));
    moduloAutor.renderActualizarAutor(document.getElementById('actualizarAutorContainer'));
    moduloAutor.renderEliminarAutor(document.getElementById('eliminarAutorContainer'));
    break;
    case 'libro':
      const moduloLibro = await import('../Libro/librocrud.js');
    vista.innerHTML = `
    <div id="crearLibroContainer"></div>
    <div id="buscarLibroContainer"></div>
    <div id="buscarListaLibrosContainer"></div>
    <div id="actualizarLibroContainer"></div>
    <div id="eliminarLibroContainer"></div>
`;
    moduloLibro.renderCrearLibro(document.getElementById('crearLibroContainer'));
    moduloLibro.renderBuscarLibro(document.getElementById('buscarLibroContainer'));
    moduloLibro.renderListarLibros(document.getElementById('buscarListaLibrosContainer'));
    moduloLibro.renderActualizarLibro(document.getElementById('actualizarLibroContainer'));
    moduloLibro.renderEliminarLibro(document.getElementById('eliminarLibroContainer'));
    break;
    case 'editorial':
    case 'prestamo':
      vista.innerHTML = `
        <h2>${secciones[seccion]}</h2>
        <p>💡 Próximamente aquí se integrará el CRUD de <strong>${seccion}</strong>.</p>
      `;
      break;

    default:
      vista.innerHTML = `<h2>Sección no encontrada</h2>`;
  }
};