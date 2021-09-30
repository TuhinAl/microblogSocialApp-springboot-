package com.square.health.controller;

import com.square.health.dto.AdminDTO;
import com.square.health.service.AdminService;
import com.square.health.util.error_handle.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 1:21 AM
 **/
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin")
    ResponseEntity<ErrorDetails> registerAdmin(@RequestBody AdminDTO admin) {
        return adminService.registerAdmin(admin);
    }

    @PostMapping("/approve/{bloggerId}")
    ResponseEntity<ErrorDetails> approveBlogger(@RequestParam Long adminId,
                                                @PathVariable("bloggerId") Long bloggerId) {
        return adminService.approveBlogger(adminId, bloggerId);
    }

    @PostMapping("/activate/deactivate/{bloggerId}")
    ResponseEntity<ErrorDetails> deactivateBlogger(@PathVariable("bloggerId") Long bloggerId,
                                                   @RequestParam Integer status) {
        return adminService.deactivateBlogger(bloggerId, status);
    }

    @PostMapping("/approved/post/{postId}")
    ResponseEntity<ErrorDetails> approveBlogPost(@RequestParam Long adminId,
                                                 @PathVariable("postId") Long postId) {
        return adminService.approveBlogPost(adminId, postId);
    }

    @DeleteMapping("/delete/{postId}")
    ResponseEntity<ErrorDetails> deleteBlogPost(@PathVariable("postId") Long postId) {
        return adminService.deleteBlogPost(postId);
    }

}
