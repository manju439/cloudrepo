package com.model;

import lombok.Data;

@Data
public class UserLog {
    private String date;
    private String userId;
    private String time_utc;
    private String name;
}
