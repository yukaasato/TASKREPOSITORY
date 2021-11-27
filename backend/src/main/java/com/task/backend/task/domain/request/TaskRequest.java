package com.task.backend.task.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TaskRequest {
    private int id;
    private String name;
    private String date;
    private String time;
    private String place;
    private boolean complete;
    
}
