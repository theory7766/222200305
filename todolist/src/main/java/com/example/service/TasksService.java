package com.example.service;

import com.example.domain.Task;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public interface TasksService {

    /**
     * 创建任务
     * @param task
     * @return
     */
    public boolean saveTasks (Task task,String username);

    /**
     * 根据id修改status
     * @param id
     * @param status
     * @return
     */
    public boolean updateTasks(Integer id,String status,String username);

    /**
     * 根据id删除任务
     * @param id
     * @return
     */
    public boolean deleteTasks(Integer id,String username);

    /**
     * 根据status获取任务信息
     * @param status
     * @return
     */
    public List<Task> getTasksByStatus(String status,String username);

    /**
     * 校验token的正确性
     * @parameter token
     * @return
     */
    public String checkToken(HttpServletRequest request);
}
