package TaskFlow;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {
     List<Task> findByUser(User user);
}
