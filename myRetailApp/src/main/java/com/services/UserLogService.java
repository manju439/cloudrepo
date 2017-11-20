package com.services;

import com.dao.UserLogDao;
import com.model.UserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogService {
    @Autowired
    private UserLogDao userLogDao;

    public List<UserLog> fetchUserLogsByDate(String searchByDate) {
        return userLogDao.findAll(searchByDate);
    }
}
