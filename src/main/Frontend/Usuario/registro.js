import { registrarUsuario } from './usuarioService.js';

document.getElementById("formRegistro").addEventListener("submit", async function (e) {
  e.preventDefault();

  const usuario = {
    nombre: document.getElementById("nombre").value,
    apellido: document.getElementById("apellido").value,
    direccion: document.getElementById("direccion").value,
    telefono: document.getElementById("telefono").value,
    email: document.getElementById("email").value,
    password: document.getElementById("password").value,
    fechaRegistro: document.getElementById("fechaRegistro").value
  };

  const res = await registrarUsuario(usuario);
  const resultado = document.getElementById("resultado");

  if (res && res.id) {
    resultado.innerHTML = `<span style="color:green;">Usuario registrado correctamente</span>`;
    e.target.reset();
  } else {
    resultado.innerHTML = `<span style="color:red;">Error: ${res?.message || "No se pudo registrar el usuario"}</span>`;
  }
});
