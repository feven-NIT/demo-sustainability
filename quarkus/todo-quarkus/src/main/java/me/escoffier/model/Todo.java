package me.escoffier.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Sort;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Entity
public class Todo extends PanacheEntity {

    @NotBlank
    public String title;

    public boolean completed;

    @Column(name = "ordering")
    public int order;

    public static List<Todo> getTodos() {
        return listAll(Sort.by("order"));
    }

    public static void clearCompleted() {
        delete("completed", true);
    }

    public static Optional<Todo> getTodoById(Long id) {
        return findByIdOptional(id);
    }
}
