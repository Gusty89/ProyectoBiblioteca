// Login
import { login } from "./usuarioService.js";

document.getElementById("formLogin").addEventListener("submit", async (e) => {
  e.preventDefault();

  const email = loginEmail.value;
  const password = loginPassword.value;
  const res = await login(email, password);

  document.getElementById("resultado").textContent = JSON.stringify(res, null, 2);

  if (res && res.mensaje === "Login exitoso") {
  localStorage.setItem("usuarioLogueado", email);
  Swal.fire({
    title: "Login exitoso",
    text: "¡Bienvenido!",
    icon: "success",
    confirmButtonText: "Ir al Panel"
  }).then(() => {
    window.location.href = "../Panel/panel.html";
  });
} else {
  Swal.fire({
    title: "Error",
    text: res?.error || "Credenciales inválidas",
    icon: "error",
    confirmButtonText: "Intentar de nuevo"
  });
}
})