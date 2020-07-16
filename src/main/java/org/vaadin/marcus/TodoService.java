package org.vaadin.marcus;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private TodoRepository repo;

    TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public List<Todo> getTodos() {
        return repo.findAll();
    }

    public Todo saveTodo(Todo todo) {
        return repo.save(todo);
    }

    public void deleteTodo(Long id) {
        repo.deleteById(id);
    }
}