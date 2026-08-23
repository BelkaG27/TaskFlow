package TaskFlow;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return taskRepository.findAll();
    }  

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskByID(@PathVariable int id){

        return taskRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task){
        return taskRepository.save(task);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id,@RequestBody Task updatedTask){
        return taskRepository.findById(id)
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
        if(!taskRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    } 
    

}
