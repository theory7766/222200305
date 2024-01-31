package com.example.controller;

import com.example.domain.Task;
import com.example.exception.BusinessException;
import com.example.service.TasksService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TasksController {
    @Autowired
    private TasksService tasksService;
    @PostMapping
    public Result saveTask(HttpServletRequest request, @RequestBody Task task) throws ServletException, IOException {
        String username = tasksService.checkToken(request);
        tasksService.saveTasks(task,username);
        return new Result(Code.SUCCESS, "success");
    }

    @PutMapping("/{id}")
    public Result updateTask(HttpServletRequest request,@PathVariable Integer id,
                             @NonNull @RequestParam String status) throws ServletException, IOException {
        String username = tasksService.checkToken(request);
        tasksService.updateTasks(id, status,username);
        return new Result(Code.SUCCESS,"success");
    }

    @DeleteMapping("/{id}")
    public Result deleteTask(HttpServletRequest request,@PathVariable Integer id) throws ServletException, IOException {
        String username = tasksService.checkToken(request);
        tasksService.deleteTasks(id,username);
        return new Result(Code.SUCCESS, "success");
    }

    @GetMapping
    public QueryRequest getByStatus(HttpServletRequest request,
                                    @NonNull @RequestParam String status) throws ServletException, IOException {
        String username = tasksService.checkToken(request);
        List<Task> taskList = tasksService.getTasksByStatus(status,username);
        Integer code = Code.SUCCESS;
        String msg = "success";
        return new QueryRequest(code, msg, taskList, taskList.size());
    }
}