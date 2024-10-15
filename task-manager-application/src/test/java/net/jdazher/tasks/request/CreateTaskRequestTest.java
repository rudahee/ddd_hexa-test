package net.jdazher.tasks.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.jdazher.domain.tasks.error.IllegalDateException;
import net.jdazher.domain.tasks.model.Task;
import net.jdazher.domain.tasks.model.TaskStatus;
import net.jdazher.application.tasks.request.CreateTaskRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CreateTaskRequestTest {


    @Test
    void testConstructorWithValidTask() {
        try {
            // Crear un Task válido
            Task task = new Task("Test Task", "Test Description", LocalDateTime.now().plusDays(1), new ArrayList<>());

            // Crear la solicitud
            CreateTaskRequest request = new CreateTaskRequest(task);

            // Verificar que el objeto fue creado correctamente
            assertNotNull(request);
            assertEquals(task, request.getTask());
        } catch (IllegalDateException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testConstructorWithNullTask() {
        // Probar que se lanza una IllegalArgumentException cuando el task es null
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new CreateTaskRequest(null)
        );

        assertEquals("task cannot be null", exception.getMessage());
    }

    @Test
    void testJsonDeserialization() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        // Crear un JSON de ejemplo
        String json = "{\"task\": {\"title\": \"Test Task\", \"description\": \"Test Description\", \"dueDate\": \"2023-12-31T10:00:00\", \"status\": \"CREATED\", \"tags\": []}}";

        // Deserializar JSON a CreateTaskRequest
        CreateTaskRequest request = objectMapper.readValue(json, CreateTaskRequest.class);

        // Verificar que se deserializó correctamente
        assertNotNull(request.getTask());
        assertEquals("Test Task", request.getTask().getTitle());
        assertEquals("Test Description", request.getTask().getDescription());
        assertEquals(TaskStatus.CREATED, request.getTask().getStatus());
    }

    @Test
    void testJsonSerialization() throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();

            // Crear un Task de prueba
            Task task = new Task("Test Task", "Test Description", LocalDateTime.now().plusDays(1), new ArrayList<>());
            CreateTaskRequest request = new CreateTaskRequest(task);

            // Serializar el objeto a JSON
            String json = objectMapper.writeValueAsString(request);

            // Verificar que el JSON generado contiene los datos correctos
            assertTrue(json.contains("\"title\":\"Test Task\""));
            assertTrue(json.contains("\"description\":\"Test Description\""));
        } catch (IllegalDateException e) {
            fail(e.getMessage());
        }
    }
}