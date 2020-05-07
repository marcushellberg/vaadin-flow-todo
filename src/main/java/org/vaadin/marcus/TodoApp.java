package org.vaadin.marcus;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.UUID;

@Route("")
public class TodoApp extends VerticalLayout {
    private TextField task = new TextField();
    private Button button = new Button("Add");
    private UnorderedList taskList = new UnorderedList();
    private TodoService service;

    TodoApp(TodoService service) {
        this.service = service;
        add(
            new H1("Todo"),
            new HorizontalLayout(task, button),
            taskList
        );

        button.addClickListener(this::addTask);
        updateTasks();
    }


    private void updateTasks() {
        taskList.removeAll();

        for (Todo todo : service.getTodos()) {
            HorizontalLayout taskLayout = new HorizontalLayout(
                new Span(todo.getTask()),
                new Button("Delete", e -> deleteTask(todo.getId()))
            );
            taskLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
            taskList.add(new ListItem(taskLayout));
        }
    }

    private void addTask(ClickEvent<Button> e) {
        service.addTodo(task.getValue());
        task.clear();
        updateTasks();
    }

    private void deleteTask(UUID id) {
        service.deleteTodo(id);
        updateTasks();
    }
}