package com.task.backend.task.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import com.task.backend.common.entity.task.TaskEntity;
import com.task.backend.task.application.TaskService;
import com.task.backend.task.domain.TaskFactory;
import com.task.backend.task.domain.request.TaskRequest;
import com.task.backend.task.domain.response.TaskResponse;

@RestController
public class TaskContoroller {
    
    @Autowired private TaskService  taskService;
    @Autowired private TaskFactory  taskFactory;
    
    
    //全エンティティを返す
    @GetMapping("/task") 
	public List<TaskResponse>getTask(){
        return taskFactory.createTaskResponse(taskService.getAll()); 
    }
    
    //詳細データを1件返す
    @GetMapping("/{taskId}")
	public TaskResponse showTaskDetail(@PathVariable int taskId) {
         return taskFactory.createTaskRespone(taskService.getTaskEntity(taskId));
	}

    //登録
    @PostMapping("/task/new")
    public TaskResponse registTask(@RequestBody TaskRequest taskRequest) {
        
        String date = taskRequest.getDate();
        String time = taskRequest.getTime();

        TaskEntity task = new TaskEntity();
       
        task.setCompleteFlag(taskRequest.isComplete());
        task.setTaskId(taskRequest.getId());
        task.setTaskName(taskRequest.getName());
        task.setTaskPlace(taskRequest.getPlace());
  
        if(date != null && date.length() == 10 ){
			task.setTaskDate(Date.valueOf(date));
        }
        if(date != null && date.length() > 10){
           task.setTaskDate(Date.valueOf(date.substring(0,10)));
        }

        if(time != null && time.length()  == 5){
            task.setTaskTime(Time.valueOf(time + ":00"));
        }
        if(time != null && time.length()  == 8){
            task.setTaskTime(Time.valueOf(time));
        }
       
        return taskFactory.createTaskRespone(taskService.regist(task));
    }

    //更新
    @PostMapping("/task/update")
	public TaskResponse updateTask(@RequestBody TaskRequest taskRequest) {
        
        String date = taskRequest.getDate();
        String time = taskRequest.getTime();

        TaskEntity task = new TaskEntity();
       
        task.setTaskId(taskRequest.getId());
        task.setCompleteFlag(taskRequest.isComplete());
        task.setTaskId(taskRequest.getId());
        task.setTaskName(taskRequest.getName());
        task.setTaskPlace(taskRequest.getPlace());
        
        if(date != null && date.length() == 10 ){
			task.setTaskDate(Date.valueOf(date));
        }
        if(date != null && date.length() > 10){
           task.setTaskDate(Date.valueOf(date.substring(0,10)));
        }
        if(time != null && time.length()  == 5){
            task.setTaskTime(Time.valueOf(time + ":00"));
        }
        if(time != null && time.length()  == 8){
            task.setTaskTime(Time.valueOf(time));
        }
		return taskFactory.createTaskRespone(taskService.update(task));
    }

    //削除
	@PostMapping("/task/delete/{taskId}")
	public void deleteTask(@PathVariable int taskId) {
        TaskEntity task = taskService.getTaskEntity(taskId);
		taskService.delete(task);			
    }

    //完了
    @PostMapping("/task/done/{taskId}")
	public TaskResponse taskDone(@PathVariable int taskId) {
		TaskEntity task = taskService.getTaskEntity(taskId);
        task.setCompleteFlag(!task.getCompleteFlag());
		return taskFactory.createTaskRespone(taskService.done(task));    	
    }
}
