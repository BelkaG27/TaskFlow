package TaskFlow;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import jakarta.validation.Valid;



@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("Utilisateur non trouvé"));
    }

    @GetMapping("/tasks")
    public List<Task> getTasks(){
        User currentUser = getCurrentUser();
        return taskRepository.findByUser(currentUser);
    }  

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskByID(@PathVariable int id){
        User currentUser = getCurrentUser();

        return taskRepository.findById(id)
        .filter(task->task.getUser().getId()==currentUser.getId())
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task task){
        User currentUser = getCurrentUser();
        task.setUser(currentUser);
        return taskRepository.save(task);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id,@Valid @RequestBody Task updatedTask){
        User currentUser = getCurrentUser();
        return taskRepository.findById(id)
               .filter(task->task.getUser().getId()==currentUser.getId())
               .map(t->{
                    t.setTitre(updatedTask.getTitre());
                    t.setDescription(updatedTask.getDescription());
                    t.setTerminee(updatedTask.isTerminee());
                    Task saved = taskRepository.save(t);
                    return ResponseEntity.ok(saved);
               })
               .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id){
        User currentUser = getCurrentUser();
        return taskRepository.findById(id)
                .filter(task -> task.getUser().getId() == currentUser.getId())
                .map(task-> {
                    taskRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());  
    } 
    

}
