const API_URL = 'http://localhost:8080/api/usuarios';

export async function obtenerUsuarios() {
  try {
    const response = await fetch(API_URL);
    return response.status === 204 ? [] : await response.json();
  } catch (error) {
    console.error("Error al obtener usuarios:", error);
    return [];
  }
}

export async function obtenerUsuarioPorId(id) {
  try {
    const response = await fetch(`${API_URL}/${id}`);
    const data = await response.json();
    return response.ok ? data : null;
  } catch (error) {
    console.error("Error al buscar usuario:", error);
  }
}

export async function registrarUsuario(usuario) {
  try {
    const response = await fetch(`${API_URL}/registrar`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(usuario)
    });
    return await response.json();
  } catch (error) {
    console.error("Error en el registro:", error);
  }
}

export async function login(email, password) {
  try {
    const response = await fetch(`${API_URL}/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, password })
    });
    return await response.json();
  } catch (error) {
    console.error("Error al iniciar sesión:", error);
  }
}

export async function actualizarUsuario(usuarioActualizado) {
  try {
    const response = await fetch(`${API_URL}/${usuarioActualizado.id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(usuarioActualizado)
    });
    
    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(errorText || 'Error al actualizar usuario');
    }
    
    return await response.json();
  } catch (error) {
    console.error("Error al actualizar usuario:", error);
    throw error;  // para propagar el error a quien llama
  }
}

export async function eliminarUsuario(id) {
  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: 'DELETE'
    });
    return await response.text();
  } catch (error) {
    console.error("Error al eliminar usuario:", error);
  }
}
