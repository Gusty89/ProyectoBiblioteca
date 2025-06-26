const API_URL = 'http://localhost:8080/api/autores';

//Obtener lista de autores
export async function obtenerAutores() {
  try {
    const response = await fetch(`${API_URL}/listaAutores`);
    if (!response.ok) {
      throw new Error("Error al obtener autores");
    }
    return await response.json();
  } catch (error) {
    console.error("Error:", error);
    return [];
  }
}



// Obtener autor por ID
export async function buscarPorId(id) {
  try {
    const response = await fetch(`${API_URL}/${id}`);
    if (!response.ok) return null;
    return await response.json();
  } catch (error) {
    console.error("Error al buscar autor:", error);
    return null;
  }
}

// Crear autor
export async function crearAutor(autor) {
  try {
    const response = await fetch(`${API_URL}/crearAutor`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(autor)
    });

    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(errorText || 'Error al registrar autor');
    }

    return await response.json();
  } catch (error) {
    console.error("Error al registrar autor:", error);
    throw error;
  }
}

// Actualizar autor
export async function actualizarAutor(autorActualizado) {
  try {
    const response = await fetch(`${API_URL}/${autorActualizado.id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(autorActualizado)
    });

    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(errorText || 'Error al actualizar autor');
    }

    return await response.json();
  } catch (error) {
    console.error("Error al actualizar autor:", error);
    throw error;
  }
}

// Eliminar autor
export async function eliminarAutor(id) {
  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: 'DELETE'
    });

    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(errorText || 'Error al eliminar autor');
    }

    return await response.text();
  } catch (error) {
    console.error("Error al eliminar autor:", error);
    throw error;
  }
}
