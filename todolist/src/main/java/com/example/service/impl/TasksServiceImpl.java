package com.example.service.impl;

import com.example.controller.Code;
import com.example.dao.TasksDao;
import com.example.domain.Task;
import com.example.domain.User;
import com.example.exception.BusinessException;
import com.example.service.TasksService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

@Service
public class TasksServiceImpl implements TasksService {
    @Autowired
    private TasksDao tasksDao;

    // 判断闰年
    public boolean isLeapYear(int year) {
        return (year % 400 == 0 || year % 4 == 0 && year % 100 != 0);
    }

    // 对时间进行校验
    public boolean timeCheck(String time) {
        if (time.isBlank()) {
            throw new BusinessException(Code.SAVE_TASK_ERROR, "time cannot be empty");
        } else if (!time.matches("\\d+")) {
            // 全数字的判断,使用正则表达式
            throw new BusinessException(Code.SAVE_TASK_ERROR, "time must be in full digits");
        } else {
            // 字符串转换年月日
            Integer year = 0, mon = 0, day = 0;
            for (int i = 0; i < 4; i++) year = year * 10 + (time.charAt(i) - '0');
            for (int i = 4; i < 6; i++) mon = mon * 10 + (time.charAt(i) - '0');
            for (int i = 6; i < 8; i++) day = day * 10 + (time.charAt(i) - '0');
//           System.out.println("year:"+year+",mon:"+mon+",day:"+day);
            int mon_day[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int day_max = mon_day[mon] + (mon == 2 && isLeapYear(year) ? 1 : 0);
            //进行年月日的校验
            if (year != 0 && mon >= 1 && mon <= 12 && day >= 1 && day <= day_max) {
                return true;
            } else {
                throw new BusinessException(Code.SAVE_TASK_ERROR, "incorrect time");
            }
        }
    }

    @Override
    public boolean saveTasks(Task task,String username) {
        if (!task.getStatus().equals("1") && !task.getStatus().equals("0")) {
            // status必须为1或0
            throw new BusinessException(Code.SAVE_TASK_ERROR, "status can only be 1 or 0");
        } else if (task.getContent().isBlank() || task.getTitle().isBlank()) {
            // 标题或内容不可为空
            throw new BusinessException(Code.SAVE_TASK_ERROR, "title or content cannot be empty");
        } else if (timeCheck(task.getStart_time()) && timeCheck(task.getEnd_time())) {
            // 在保证时间正确的情况下，对时间先后进行比较
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date start_date, end_date;
            try {
                start_date = sdf.parse(task.getStart_time());
                end_date = sdf.parse(task.getEnd_time());
//                System.out.println(start_date);
//                System.out.println(end_date);
            } catch (ParseException e) {
                throw new BusinessException(Code.SAVE_TASK_ERROR, "time change error");
            }
            if (!start_date.before(end_date))
                throw new BusinessException(Code.SAVE_TASK_ERROR,
                        "start_time must be earlier than end_time");
        }
        tasksDao.save(task,username);
        return true;
    }

    @Override
    public boolean updateTasks(Integer id, String status,String username) {
        if (id <= 0) {
            throw new BusinessException(Code.UPDATE_TASK_ERROR, "id must bigger than 0");
        } else if (!status.equals("1") && !status.equals("0")) {
            throw new BusinessException(Code.UPDATE_TASK_ERROR, "status can only be 1 or 0");
        } else {
            int update_num = tasksDao.update(id, status,username);
            if (update_num == 0)
                throw new BusinessException(Code.UPDATE_TASK_ERROR,
                        "update task failed: task with id " + id + " doesn't exist");
            return true;
        }
    }

    @Override
    public boolean deleteTasks(Integer id,String username) {
        if (id <= 0) {
            throw new BusinessException(Code.DELETE_TASK_ERROR, "id must bigger than 0");
        } else {
            int delete_num = tasksDao.delete(id,username);
            if (delete_num == 0)
                throw new BusinessException(Code.DELETE_TASK_ERROR,
                        "delete task failed: task with id " + id + " doesn't exist");
            return true;
        }
    }

    @Override
    public List<Task> getTasksByStatus(String status,String username) {
        if (!status.equals("1") && !status.equals("0")) {
            throw new BusinessException(Code.GET_TASK_ERROR, "status can only be 1 or 0");
        } else {
            List<Task> taskList = tasksDao.getByStatus(status, username);
            if (taskList.isEmpty() || taskList == null) {
                throw new BusinessException(Code.GET_TASK_ERROR,
                        "query by status failed: task with status " + status + " doesn't exist");
            } else {
                return taskList;
            }
        }
    }

    // 校验token,返回解析出来的username
    @Override
    public String checkToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token==null){
            throw new BusinessException(Code.REQUEST_DATA_ERROR,"token message is empty");
        }else {
            String username = tasksDao.getByToken(token);
            if (username==null){
                throw new BusinessException(Code.REQUEST_DATA_ERROR,"token message error");
            }else {
                return username;
            }
        }
    }
}
