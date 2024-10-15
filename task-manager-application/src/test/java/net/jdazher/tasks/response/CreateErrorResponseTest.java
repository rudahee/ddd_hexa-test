package net.jdazher.tasks.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.jdazher.application.tasks.response.CreateErrorResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateErrorResponseTest {
    @Test
    void testConstructorWithValidMessage() {
        // Crear un mensaje válido
        String validMessage = "An error occurred";

        // Crear la respuesta
        CreateErrorResponse response = new CreateErrorResponse(validMessage);

        // Verificar que el objeto fue creado correctamente
        assertNotNull(response);
        assertEquals(validMessage, response.getMessage());
    }

    @Test
    void testConstructorWithNullMessage() {
        // Probar que se lanza una IllegalArgumentException cuando el mensaje es null
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new CreateErrorResponse(null)
        );

        assertEquals("message cannot be null", exception.getMessage());
    }

    @Test
    void testConstructorWithBlankMessage() {
        // Probar que se lanza una IllegalArgumentException cuando el mensaje está vacío
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new CreateErrorResponse("   ") // Mensaje en blanco
        );

        assertEquals("message cannot be null", exception.getMessage());
    }

    @Test
    void testJsonDeserialization() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        // Crear un JSON de ejemplo
        String json = "{\"message\": \"An error occurred\"}";

        // Deserializar JSON a CreateErrorResponse
        CreateErrorResponse response = objectMapper.readValue(json, CreateErrorResponse.class);

        // Verificar que se deserializó correctamente
        assertNotNull(response);
        assertEquals("An error occurred", response.getMessage());
    }

    @Test
    void testJsonSerialization() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        // Crear una respuesta de error con un mensaje
        CreateErrorResponse response = new CreateErrorResponse("An error occurred");

        // Serializar el objeto a JSON
        String json = objectMapper.writeValueAsString(response);

        // Verificar que el JSON generado contiene los datos correctos
        assertTrue(json.contains("\"message\":\"An error occurred\""));
    }
}