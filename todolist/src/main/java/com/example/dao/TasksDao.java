package com.example.dao;

import com.example.domain.Task;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper
public interface TasksDao {
    @Insert("insert into tasks (username,title,content,start_time,end_time,status) values " +
            "(#{username},#{task.title},#{task.content},#{task.start_time},#{task.end_time},#{task.status})")
    public int save (Task task,String username);
    @Update("update tasks set status=#{status} where id =#{id} and username=#{username}")
    public int update(Integer id,String status,String username);

    @Delete("delete from tasks where id = #{id} and username=#{username}")
    public int delete(Integer id,String username);
    @Select("SELECT * FROM tasks WHERE status = #{status} and username=#{username}")
    public List<Task> getByStatus(String status,String username);
    // 按token查询用户名
    @Select("select username from users_token where token = #{token}")
    public String getByToken(String token);
}
