package com.task.backend.task.domain;

import java.util.List;
import com.task.backend.common.entity.task.TaskEntity;
import com.task.backend.task.domain.response.TaskResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
@Component
public class TaskFactory {
    
    @Autowired
    private ModelMapper mapper;

    public TaskResponse createTaskRespone(TaskEntity taskEntity){
        return mapper.map(taskEntity, TaskResponse.class);
    }

    public List<TaskResponse> createTaskResponse(List<TaskEntity> taskList) {
        return taskList.stream().map(taskEntity 
            -> mapper.map(taskEntity, TaskResponse.class)).collect(Collectors.toList());
    }


    // 解説
    // Factoryクラスでやること
    // ModeloMapperを利用してEntitiyをResponseに変換してControllerに返すこと
}
