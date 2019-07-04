package io.github.eyulingo.Dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import io.github.eyulingo.Entity.Admins;

import java.util.List;

@Repository("AdminRepository")
public interface AdminRepository extends CrudRepository<Admins,String> {
    List<Admins> findByAdminName(String adminName);


}
