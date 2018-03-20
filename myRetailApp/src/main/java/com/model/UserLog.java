package com.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserLog {
    private String date;
    private String address;
    private Date time_utc;
    private String name;
}
