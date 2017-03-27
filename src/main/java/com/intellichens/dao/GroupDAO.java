package com.intellichens.dao;

import com.intellichens.model.GroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by raychen on 2017/3/22.
 */
public interface GroupDAO extends JpaRepository<GroupModel, Integer>{

    @Query("select g from GroupModel g join UserGroupModel ug on g.id=ug.groupId where ug.userId=:id")
    List<GroupModel> findGroupsByUserId(@Param("id") Integer id);

    @Query("select g from GroupModel g where g.leaderId = :id")
    List<GroupModel> findGroupByLeaderId(@Param("id") Integer id);

    @Query("select g from GroupModel g where g.groupId = :id")
    GroupModel findGroupByGroupId(@Param("id") Integer id);
}
