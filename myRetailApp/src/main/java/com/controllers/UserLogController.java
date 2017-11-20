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
    public @ResponseBody
    List<UserLog> getUserLogsByDate(@ApiParam(value = "yyyy-MM-dd") @RequestParam("searchByDate") String searchByDate) {
        System.out.println(searchByDate);
        List<UserLog> list = userLogService.fetchUserLogsByDate(searchByDate);
        return null;
    }
}
