package com.intellichens.dao;

import com.intellichens.model.TagModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by raychen on 2017/3/22.
 */
public interface TagDAO extends JpaRepository<TagModel, Integer> {

    @Query("select tags from TagModel tags where tags.recordId=:rid")
    List<TagModel> findTagsByRecordId(@Param("rid")Integer rid);

    @Query("delete from TagModel tag where tag.recordId=:rid")
    @Modifying
    void deleteByRid(@Param("rid") Integer rid);
}
