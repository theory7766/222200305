package com.example;

import com.example.domain.Task;
import com.example.service.TasksService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("com.example.dao")
public class TasksServiceTest {
    @Autowired
    private TasksService tasksService;
//    @Test
//    public void testGetByStatus(){
//        List<Task> tasks = tasksService.getTasksByStatus("0");
//        for (int i=0;i<tasks.size();i++){
//            System.out.println(tasks.get(i));
//        }
//    }
//    @Test
//    public void testSaveTask(){
//        String token = " dJd29db6Ue/mtU/PcbgjCMZodYlc3AXefLqYk/hQfYRypKEZKXL2XMBjEUk3vn3MRBvKKKu6TAV8jbMB5h/HVx0O" +
//                "ZRGFRZf4TV2eqEh1Z7tOCTLXCp0KCot8pfBDqb35Nm8KH0k8zw0pM57E5dEZkcbqjc8frIMeuPw1C3Xxl/M=";
//        String username = tasksService.checkToken();
//        Task task = new Task("震惊！","一男子深夜进入女子家中竟然做出这种事！",
//                    "20000229","20240127","1");
//        boolean flag = tasksService.saveTasks(task);
//        System.out.println(flag);
//    }
//    @Test
//    public void testUpdateTask(){
//        boolean flag = tasksService.updateTasks(6,"1");
//        System.out.println(flag);
//    }
//    @Test
//    public void testDeleteTask(){
//        boolean flag = tasksService.deleteTasks(7);
//        System.out.println(flag);
//    }

}
