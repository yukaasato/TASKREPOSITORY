package com.task.backend.task.application;

import javax.transaction.Transactional;

import com.task.backend.common.entity.task.TaskEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public List<TaskEntity> getAll(){
        return taskRepository.findAll(); 
    }
    public TaskEntity getTaskEntity(int taskId) {
	    return taskRepository.findByTaskId(taskId);
   }
   
	public TaskEntity regist(TaskEntity taskEntity) {
		return taskRepository.save(taskEntity);
    }

    public TaskEntity update(TaskEntity taskEntity) {
		return taskRepository.save(taskEntity);
    }

    public void delete(TaskEntity taskEntity){
        taskRepository.delete(taskEntity);
    }
    
    public TaskEntity done(TaskEntity taskEntity){
        taskRepository.save(taskEntity);
        return taskEntity;
    }
}

