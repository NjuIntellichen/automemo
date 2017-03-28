package com.intellichens.dao;

import com.intellichens.model.SpeechItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yuminchen
 * @version V1.0
 * @date 2017/3/28
 */
public interface SpeechItemDAO extends JpaRepository<SpeechItem, Integer>{
    List<SpeechItem> findByRecordId(Integer recordId);
}
