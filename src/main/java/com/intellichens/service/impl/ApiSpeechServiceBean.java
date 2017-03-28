package com.intellichens.service.impl;

import com.intellichens.api.SpeechTranslateAPI;
import com.intellichens.api.impl.SpeechTranslateImpl;
import com.intellichens.dao.RecordDAO;
import com.intellichens.dao.SpeechItemDAO;
import com.intellichens.model.RecordModel;
import com.intellichens.model.SpeechItem;
import com.intellichens.service.ApiSpeechService;
import com.intellichens.util.RecordState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * @author yuminchen
 * @version V1.0
 * @date 2017/3/28
 */
@Service
public class ApiSpeechServiceBean implements ApiSpeechService{

    private final SpeechTranslateAPI speechTranslateAPI;
    private final RecordDAO recordDAO;
    private final SpeechItemDAO speechItemDAO;

    @Autowired
    public ApiSpeechServiceBean(RecordDAO recordDAO, SpeechItemDAO speechItemDAO) {
        this.speechTranslateAPI = new SpeechTranslateImpl();
        this.recordDAO = recordDAO;
        this.speechItemDAO = speechItemDAO;
    }

    @Override
    public String translateSpeech(Integer recordId, byte[] speech) {
        String content = speechTranslateAPI.translateSpeech(speech);
        if(content==null){
            return "0";
        }
        SpeechItem item = new SpeechItem();
        item.setContent(content);
        item.setRecordId(recordId);
        item.setDate(new Date(Calendar.getInstance().getTimeInMillis()));

        speechItemDAO.saveAndFlush(item);
        return "1";

    }


    @Override
    public String stopSpeech(Integer recordId) {
        RecordModel recordModel = recordDAO.findOne(recordId);
        recordModel.setState(RecordState.finished);
        recordDAO.saveAndFlush(recordModel);

        return "1";
//        List<SpeechItem> items = speechItemDAO.findByRecordId(recordId);
//        StringBuilder stringBuilder = new StringBuilder();
//        items.forEach(item -> stringBuilder.append(item.getContent()));
//
//        String result = stringBuilder.toString();
    }

}
