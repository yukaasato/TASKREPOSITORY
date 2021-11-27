package com.task.backend.task.application;

import com.task.backend.common.entity.task.TaskEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer>{ 

    @Query(value ="SELECT "
    + "DISTINCT taskEntity "
    + "FROM "
    + "TaskEntity taskEntity "
    + "WHERE "
    + "taskEntity.taskId = :taskId")					
public TaskEntity findByTaskId(@Param("taskId") int taskId);

}
