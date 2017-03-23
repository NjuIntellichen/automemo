package com.intellichens.dao;

import com.intellichens.model.TagModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by raychen on 2017/3/22.
 */
@Repository
public interface TagDAO extends JpaRepository<TagModel, Integer> {
}
