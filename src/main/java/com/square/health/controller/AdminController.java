package com.square.health.controller;

import com.square.health.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:21 AM
 **/
@RestController
@RequestMapping("/api/v1/amin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
}
