package net.javaguides.todo.controler;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    //Teste for git
    //Build Add Todo REST API
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto savedDto = todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    //build Get Todo REST API
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){
        TodoDto todoDto = todoService.getTodo(todoId);

        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    //build Get All Todos REST API
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAlltodos(){
        List<TodoDto> todos = todoService.getAllTodos();

        return new ResponseEntity<>(todos,HttpStatus.OK);
    }

    //Build update REST API
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable("id") Long todoId){
        TodoDto todoDtoUpdated = todoService.updateTodo(todoDto, todoId);

        return new ResponseEntity<>(todoDtoUpdated, HttpStatus.OK);
    }

    //Build Delete Todo API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deeleted successfully!");
    }

    //Build Complete REST API
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") long todoId){
        TodoDto todoDtoUpdated = todoService.completeTodo(todoId);

        return new ResponseEntity<>(todoDtoUpdated, HttpStatus.OK);
    }

    //Build inComplete REST API
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") long todoId){
        TodoDto todoDtoUpdated = todoService.inCompleteTodo(todoId);

        return new ResponseEntity<>(todoDtoUpdated, HttpStatus.OK);
    }
}
