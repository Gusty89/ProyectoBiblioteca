const API_URL = 'http://localhost:8080/api/libros';

// Obtener libro por ID
export async function buscarPorId(id) {
  try {
    const response = await fetch(`${API_URL}/${id}`);
    if (!response.ok) return null;
    return await response.json();
  } catch (error) {
    console.error("Error al buscar libro:", error);
    return null;
  }
}

// Crear libro
export async function crearLibro(libro) {
  try {
    const response = await fetch(`${API_URL}/crearLibro`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(libro)
    });

    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(errorText || 'Error al registrar libro');
    }

    return await response.json();
  } catch (error) {
    console.error("Error al registrar libro:", error);
    throw error;
  }
}

// Actualizar libro
export async function actualizarLibro(libroActualizado) {
  try {
    const response = await fetch(`${API_URL}/${libroActualizado.id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(libroActualizado)
    });

    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(errorText || 'Error al actualizar libro');
    }

    return await response.json();
  } catch (error) {
    console.error("Error al actualizar libro:", error);
    throw error;
  }
}

// Eliminar libro
export async function eliminarLibro(id) {
  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: 'DELETE'
    });

    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(errorText || 'Error al eliminar libro');
    }

    return await response.text();
  } catch (error) {
    console.error("Error al eliminar libro:", error);
    throw error;
  }
}
