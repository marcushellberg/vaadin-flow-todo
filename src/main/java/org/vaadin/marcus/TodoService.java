package org.vaadin.marcus;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TodoService {
    private ArrayList<Todo> todos = new ArrayList<>();

    @PostConstruct
    void init() {
        todos.add(new Todo("Build todo app"));
    }


    public List<Todo> getTodos() {
        return todos;
    }

    public Todo addTodo(String task) {
        Todo todo = new Todo(task);
        todos.add(todo);
        return todo;
    }

    public void deleteTodo(UUID id) {
        todos.removeIf(t -> t.getId().equals(id));
    }
}
