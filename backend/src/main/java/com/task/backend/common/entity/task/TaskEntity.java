package com.task.backend.common.entity.task;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name ="tasks")
public class TaskEntity { 
    @Id
	@Column(name = "task_id")		
	private int taskId;
	
	//　Util.Dateをインポートする
	@Column(name= "task_date")
	private Date taskDate;
	
	@Column(name = "task_name")
	private String taskName;
	
	//sql.Time
	@Column(name ="task_time")
	private Time taskTime;
	
	@Column(name = "task_place")
	private String taskPlace;
	
	@Column(name = "complete_flag")
	private Boolean completeFlag;
}
