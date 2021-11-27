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
import com.task.backend.task.domain.request.TaskRequest;

@RestController
public class TaskContoroller {
    
    @Autowired
	TaskService taskService;

    //全エンティティを返す
	@GetMapping("/task") 
	public List<TaskEntity>getTask(){
        List<TaskEntity> task = taskService.getAll();
        return task;
    }

    //詳細データを1件返す
    @GetMapping("/{taskId}")
	public TaskEntity showTaskDetail(@PathVariable int taskId) {
        return taskService.getTaskEntity(taskId);
	}

    //登録
	@PostMapping("/task/new")
	public TaskEntity registTask(@RequestBody TaskRequest taskRequest) {
        
        String date = taskRequest.getDate();
        String time = taskRequest.getTime();

        TaskEntity task = new TaskEntity();
       
        task.setCompleteFlag(taskRequest.isComplete());
        task.setTaskId(taskRequest.getId());
        task.setTaskName(taskRequest.getName());
        task.setTaskPlace(taskRequest.getPlace());
  
        //date:桁数一致していれば型のみ変換 2021-09-16
        if(date != null && date.length() == 10 ){
			task.setTaskDate(Date.valueOf(date));
        }

        //date:SQLの形式だったら、切り取って変換2021-09-16T15:00:00.000+00:00
        if(date != null && date.length() > 10){
           task.setTaskDate(Date.valueOf(date.substring(0,10)));
        }
    
        //time
        if(time != null && time.length()  == 5){
            task.setTaskTime(Time.valueOf(time + ":00"));
        }
    
        //time
        if(time != null && time.length()  == 8){
            task.setTaskTime(Time.valueOf(time));
        }

        taskService.regist(task);
		return task;
    }

    //更新
	@PostMapping("/task/update")
	public TaskEntity updateTask(@RequestBody TaskRequest taskRequest) {
        
        String date = taskRequest.getDate();
        String time = taskRequest.getTime();

        TaskEntity task = new TaskEntity();
       
        task.setTaskId(taskRequest.getId());
        task.setCompleteFlag(taskRequest.isComplete());
        task.setTaskId(taskRequest.getId());
        task.setTaskName(taskRequest.getName());
        task.setTaskPlace(taskRequest.getPlace());
        
        //date:桁数一致していれば型のみ変換 2021-09-16
        if(date != null && date.length() == 10 ){
			task.setTaskDate(Date.valueOf(date));
        }
        //date:SQLの形式だったら、切り取って変換2021-09-16T15:00:00.000+00:00
        if(date != null && date.length() > 10){
           task.setTaskDate(Date.valueOf(date.substring(0,10)));
        }
        //time
        if(time != null && time.length()  == 5){
            task.setTaskTime(Time.valueOf(time + ":00"));
        }
        //time
        if(time != null && time.length()  == 8){
            task.setTaskTime(Time.valueOf(time));
        }
        taskService.update(task);
		return task;
    }

    //削除
	@PostMapping("/task/delete/{taskId}")
	public void deleteTask(@PathVariable int taskId) {
        TaskEntity task = taskService.getTaskEntity(taskId);
		taskService.delete(task);			
    }

    //完了
    @PostMapping("/task/done/{taskId}")
	public TaskEntity taskDone(@PathVariable int taskId) {
		TaskEntity task = taskService.getTaskEntity(taskId);
        task.setCompleteFlag(!task.getCompleteFlag());
		return taskService.done(task);    	
    }
}
