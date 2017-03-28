package com.intellichens.dao;

import com.intellichens.model.UserGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by raychen on 2017/3/22.
 */
public interface UserGroupDAO extends JpaRepository<UserGroupModel, Integer> {
    @Query("select ug from UserGroupModel ug where ug.groupId=:gid and ug.userId=:uid")
    UserGroupModel findByGroupAndUser(@Param("uid") Integer uid, @Param("gid") Integer gid);

    @Query("select ug from UserGroupModel ug where ug.groupId = :gid")
    List<UserGroupModel> findUserGroupsByGroupId(@Param("gid") Integer gid);
}
