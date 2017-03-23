package com.intellichens.dao;

import com.intellichens.model.RecordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by raychen on 2017/3/22.
 */
@Repository
public interface RecordDAO extends JpaRepository<RecordModel, Integer>{
//    @Query("select r from RecordModel r where r.group.id=:gid and r.user.id=:uid")
//    List<RecordModel> findRecordsByUserAndGroup(@Param("uid") Integer uid, @Param("gid") Integer gid);
}
