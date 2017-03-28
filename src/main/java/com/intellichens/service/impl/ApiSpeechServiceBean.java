package com.intellichens.service.impl;

import com.intellichens.api.SpeechTranslateAPI;
import com.intellichens.dao.RecordDAO;
import com.intellichens.service.ApiSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuminchen
 * @version V1.0
 * @date 2017/3/28
 */
@Service
public class ApiSpeechServiceBean implements ApiSpeechService{

    private final SpeechTranslateAPI speechTranslateAPI;
    private final RecordDAO recordDAO;
    @Autowired
    public ApiSpeechServiceBean(SpeechTranslateAPI speechTranslateAPI, RecordDAO recordDAO) {
        this.speechTranslateAPI = speechTranslateAPI;
        this.recordDAO = recordDAO;
    }

    @Override
    public String translateSpeech(Integer recordId, byte[] speech) {
        String content = speechTranslateAPI.translateSpeech(speech);

        if(content!=null){
            return "1";
        }
        else {
            return "0";
        }
    }


    @Override
    public String stopSpeech(Integer recordId) {
        return null;
    }
}
