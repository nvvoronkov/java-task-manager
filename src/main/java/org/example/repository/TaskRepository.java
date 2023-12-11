package org.example.repository;

import org.example.model.Task;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByAuthor(User author);

    @Query(nativeQuery = true, value = """
                    select task_id from tasks_executor
                    where executor_id = :executorId
            """)
    List<Integer> findAllByExecutorId(@Param("executorId") int id);
}
