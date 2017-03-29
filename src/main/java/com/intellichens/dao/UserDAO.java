package com.intellichens.dao;

import com.intellichens.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by raychen on 2017/3/21.
 */
public interface UserDAO extends JpaRepository<UserModel,Integer>{
    List<UserModel> findByPhone(String phone);

    @Query("select u from UserModel u, UserGroupModel ug where u.id=ug.userId and ug.groupId=:id")
    List<UserModel> findByGroupId(@Param("id") Integer id);
}
