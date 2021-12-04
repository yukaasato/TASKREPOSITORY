package com.task.backend.task.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private int id;
    private String name;
    private String date;
    private String time;
    private String place;
    private boolean complete;
}
