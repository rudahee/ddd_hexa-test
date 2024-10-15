package net.jdazher.tasks.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.jdazher.domain.tasks.error.IllegalDateException;
import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.model.TaskStatus;
import net.jdazher.application.tasks.response.CreateTaskResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CreateTaskResponseTest {

    @Test
    void testConstructorWithValidTask() {

        try {
            // Crear un Task válido
            Task task = new Task("Test Task", "This is a test task", LocalDateTime.now().plusDays(1), new ArrayList<>());

            // Crear la respuesta
            CreateTaskResponse response = new CreateTaskResponse(task);

            // Verificar que el objeto fue creado correctamente
            assertNotNull(response);
            assertEquals(task, response.getTask());
        } catch (IllegalDateException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testConstructorWithNullTask() {
        // Probar que se lanza una IllegalArgumentException cuando el task es null
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new CreateTaskResponse(null)
        );

        assertEquals("task cannot be null", exception.getMessage());
    }

    @Test
    void testJsonDeserialization() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        // Crear un JSON de ejemplo
        String json = "{\"task\": {\"title\": \"Test Task\", \"description\": \"Test Description\", \"dueDate\": \"2023-12-31T10:00:00\", \"status\": \"CREATED\", \"tags\": []}}";

        // Deserializar JSON a CreateTaskResponse
        CreateTaskResponse response = objectMapper.readValue(json, CreateTaskResponse.class);

        // Verificar que se deserializó correctamente
        assertNotNull(response.getTask());
        assertEquals("Test Task", response.getTask().getTitle());
        assertEquals("Test Description", response.getTask().getDescription());
        assertEquals(TaskStatus.CREATED, response.getTask().getStatus());
    }

    @Test
    void testJsonSerialization() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        try {
            // Crear un Task de prueba
            Task task = new Task("Test Task", "This is a test task", LocalDateTime.now().plusDays(1), new ArrayList<>());
            CreateTaskResponse response = new CreateTaskResponse(task);

            // Serializar el objeto a JSON
            String json = objectMapper.writeValueAsString(response);

            // Verificar que el JSON generado contiene los datos correctos
            assertTrue(json.contains("\"title\":\"Test Task\""));
            assertTrue(json.contains("\"description\":\"This is a test task\""));
        } catch (IllegalDateException e) {
            fail(e.getMessage());
        }
    }
}