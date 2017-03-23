package com.intellichens.dao;

import com.intellichens.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by raychen on 2017/3/21.
 */
@Repository
public interface UserDAO extends JpaRepository<UserModel,Integer>{
    List<UserModel> findByPhone(String phone);
}
