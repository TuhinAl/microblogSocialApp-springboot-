package com.square.health.mapper;

import com.square.health.dto.AdminDTO;
import com.square.health.model.Admin;
import com.square.health.model.Role;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Alauddin Tuhin
 * @project health
 * @created 9/28/21 at 7:47 PM
 **/
@Service
public class AdminMapper {

   public Admin registerMap(AdminDTO dto) {
        Admin admin = new Admin();
        admin.setName(admin.getName());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        admin.setCreateDate(new Date());
        admin.setRole(Role.ADMIN);

        return admin;
    }
}
