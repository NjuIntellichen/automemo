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

//    @Query("select g from GroupModel g join UserGroupModel ug on g.id=ug.group.id where ug.user.id=:id")
//    List<GroupModel> getGroupsByUserId(@Param("id") Integer id);
}
