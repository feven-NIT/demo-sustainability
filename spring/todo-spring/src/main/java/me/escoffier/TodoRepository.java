package me.escoffier;

import me.escoffier.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findById(Long id);

    @Modifying
    @Query("delete from Todo t where t.completed = true")
    void clearCompleted();

}
