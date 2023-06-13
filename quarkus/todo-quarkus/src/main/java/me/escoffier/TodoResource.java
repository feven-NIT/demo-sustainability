package me.escoffier;

import me.escoffier.helper.Exceptions;
import me.escoffier.model.Todo;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static me.escoffier.helper.Exceptions.notFound;

@Path("/api")
public class TodoResource {

    @GET
    public List<Todo> getAll() {
        return Todo.getTodos();
    }

    @GET
    @Path("/{id}")
    public Todo getOne(@PathParam("id") long id) {
        return Todo.getTodoById(id)
                .orElseThrow(() -> Exceptions.notFound(id));
    }

    @POST
    @Transactional
    public Response create(@Valid Todo todo) throws URISyntaxException {
        todo.persist();
        return Response.created(new URI("/api/" + todo.id)).entity(todo).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Response update(@Valid Todo todo, @PathParam("id") Long id) {
        Todo entity = Todo.getTodoById(id)
                .orElseThrow(() -> notFound(id));
        entity.completed = todo.completed;
        entity.order = todo.order;
        entity.title = todo.title;
        return Response.ok().build();
    }

    @DELETE
    @Transactional
    public Response deleteCompleted() {
        Todo.clearCompleted();
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteOne(@PathParam("id") Long id) {
        Todo entity = Todo.getTodoById(id)
                .orElseThrow(() -> notFound(id));
        entity.delete();
        return Response.noContent().build();
    }
}
