package io.github.eyulingo.ServiceImpl;


import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.eyulingo.Service.AdminService;
import io.github.eyulingo.Dao.AdminRepository;
import io.github.eyulingo.Entity.Admins;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    AdminRepository adminRepository;

    public String adminLogin(JSONObject data) {
        try {
            List<Admins> adminsList = adminRepository.findByAdminName(data.getString("adminName"));
            if (adminsList.isEmpty()) {
                return "adminname not exists";
            }
            Admins admin = adminsList.get(0);

            if (admin.getAdminPassword().equals(data.getString("password"))) {
                return "ok";
            } else {
                return "Incorrect password";
            }
        } catch (Exception ex) {
            return "internal_error";
        }
    }
}
