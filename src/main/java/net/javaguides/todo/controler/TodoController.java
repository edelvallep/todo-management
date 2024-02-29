package net.javaguides.todo.controler;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    //Build Add Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto savedDto = todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    //build Get Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){
        TodoDto todoDto = todoService.getTodo(todoId);

        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    //build Get All Todos REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAlltodos(){
        List<TodoDto> todos = todoService.getAllTodos();

        return new ResponseEntity<>(todos,HttpStatus.OK);
    }

    //Build update REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable("id") Long todoId){
        TodoDto todoDtoUpdated = todoService.updateTodo(todoDto, todoId);

        return new ResponseEntity<>(todoDtoUpdated, HttpStatus.OK);
    }

    //Build Delete Todo API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deeleted successfully!");
    }

    //Build Complete REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") long todoId){
        TodoDto todoDtoUpdated = todoService.completeTodo(todoId);

        return new ResponseEntity<>(todoDtoUpdated, HttpStatus.OK);
    }

    //Build inComplete REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") long todoId){
        TodoDto todoDtoUpdated = todoService.inCompleteTodo(todoId);

        return new ResponseEntity<>(todoDtoUpdated, HttpStatus.OK);
    }
}
