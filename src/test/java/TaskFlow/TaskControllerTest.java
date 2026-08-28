package TaskFlow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskController taskController;

    @Test
    void getTaskById_returns200withTaskWhenTaskExists(){
        Task task = new Task("Créer la BDD", "Description", false);
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        ResponseEntity<Task> response = taskController.getTaskByID(1);
        assertEquals(200,response.getStatusCode().value());
        assertEquals("Créer la BDD",response.getBody().getTitre());
    }

    @Test
    void getTaskById_returns404WhenTaskDoesNotExist(){
        when(taskRepository.findById(99)).thenReturn(Optional.empty());

        ResponseEntity<Task> response = taskController.getTaskByID(99);
        assertEquals(404,response.getStatusCode().value()); 
    
    }

    @Test
    void createTask_saveAndReturnTask(){
        Task task = new Task("Créer la BDD", "Description", false);
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskController.createTask(task);
        assertEquals("Créer la BDD", result.getTitre());
        verify(taskRepository, times(1)).save(task); 
    }

    @Test
    void getTasks_returnListOfTasks(){
        Task task1 = new Task("abcd", null, false);
        Task task2 = new Task("abcd", null, false);

        when(taskRepository.findAll()).thenReturn(List.of(task1,task2));

        List<Task> result = taskController.getTasks();
        assertEquals(2,result.size());
    }

}
