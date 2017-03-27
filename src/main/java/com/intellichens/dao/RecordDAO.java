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
public interface RecordDAO extends JpaRepository<RecordModel, Integer>{

//    @Query("select r from RecordModel r where r.userId=:uid")
    List<RecordModel> findRecordsByUserId(@Param("uid") Integer uid);

//    @Query("select r from RecordModel r where r.groupId=:gid")
    List<RecordModel> findRecordsByGroupId(@Param("gid") Integer gid);

}
