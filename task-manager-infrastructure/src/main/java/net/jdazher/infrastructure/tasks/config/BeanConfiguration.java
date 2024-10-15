package net.jdazher.infrastructure.tasks.config;

import net.jdazher.domain.tasks.repository.TaskRepository;
import net.jdazher.domain.tasks.service.TaskService;
import net.jdazher.domain.tasks.service.DomainTaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for defining application beans.
 *
 * <p>This class uses Spring's {@link Configuration} annotation to indicate that it contains
 * bean definitions for the application context.</p>
 *
 * <p>It provides a method to create and configure a {@link TaskService} bean, which is an
 * instance of {@link DomainTaskService} that requires a {@link TaskRepository} as a dependency.</p>
 */

@Configuration
public class BeanConfiguration {

    /**
     * Creates a {@link TaskService} bean.
     *
     * <p>This method is annotated with {@link Bean}, which indicates to the Spring container
     * that it should manage the lifecycle of the returned {@code DomainTaskService} instance.</p>
     *
     * @param repository The {@link TaskRepository} instance to be injected into the
     *                   {@code DomainTaskService}.
     * @return A configured {@link TaskService} instance.
     */
    @Bean
    public TaskService taskService(TaskRepository repository) {
        return new DomainTaskService(repository);
    }
}
