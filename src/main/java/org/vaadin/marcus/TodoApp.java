package org.vaadin.marcus;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;

@Route("")
public class TodoApp extends VerticalLayout {
  private TextField task = new TextField("Task");
  private Button button = new Button("Add");
  private UnorderedList taskList = new UnorderedList();
  private TodoService service;
  private Binder<Todo> binder = new BeanValidationBinder<>(Todo.class);

  TodoApp(TodoService service) {
    this.service = service;
    HorizontalLayout form = new HorizontalLayout(task, button);
    form.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

    add(new H1("Todo"), form, taskList);

    binder.bindInstanceFields(this);
    button.addClickListener(this::addTask);
    updateTasks();
  }

  private void updateTasks() {
    taskList.removeAll();

    for (Todo todo : service.getTodos()) {
      HorizontalLayout taskLayout = new HorizontalLayout(new Span(todo.getTask()),
          new Button("Delete", e -> deleteTask(todo.getId())));
      taskLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
      taskList.add(new ListItem(taskLayout));
    }
  }

  private void addTask(ClickEvent<Button> e) {
    Todo todo = new Todo();
    if (binder.writeBeanIfValid(todo)) {
      service.saveTodo(todo);
      binder.readBean(new Todo());
      updateTasks();
    }
  }

  private void deleteTask(Long id) {
    service.deleteTodo(id);
    updateTasks();
  }
}