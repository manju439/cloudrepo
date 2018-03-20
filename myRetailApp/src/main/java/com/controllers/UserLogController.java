package com.controllers;


import com.model.UserLog;
import com.services.UserLogService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserLogController {

    @Autowired
    private UserLogService userLogService;

    @RequestMapping(value = "/userlog", method = RequestMethod.GET)
    public List<UserLog> getUserLogsByDate(@ApiParam(value = "yyyy-MM-dd") @RequestParam("searchByDate") String searchByDate) {
        return userLogService.fetchUserLogsByDate(searchByDate);
    }

    @RequestMapping(value = "/userlogs", method = RequestMethod.GET)
    public List<UserLog> getUserLogsByPage(@RequestParam("start") int start,
                                           @RequestParam("limit") int limit) {
        return userLogService.fetchUserLogsByPage(start, limit);
    }
}

/*

@RestController ==>  @Controller + @ResponseBody

@ResponseBody directly returns response to browser without rendering to view
 */