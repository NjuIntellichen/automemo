package com.intellichens.service.impl;

import com.intellichens.api.EntityLinkAPI;
import com.intellichens.api.SpeechTranslateAPI;
import com.intellichens.api.impl.EntityLinkImpl;
import com.intellichens.api.impl.SpeechTranslateImpl;
import com.intellichens.dao.RecordDAO;
import com.intellichens.dao.SpeechItemDAO;
import com.intellichens.model.RecordModel;
import com.intellichens.model.SpeechItem;
import com.intellichens.service.ApiSpeechService;
import com.intellichens.util.RecordState;
import org.json.simple.JSONObject;
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
    private final EntityLinkAPI entityLinkAPI;
    private String content1 = new StringBuffer("Key Words : VR, introduction, uses, implementation\n")
            .append("Content:\n")
            .append("There are 3 key points\n")
            .append("\tThe introduction of VR\n")
            .append("\tThe uses for VR\n")
            .append("\tThe implementation of VR\n")
            .append("\n")
            .append("The introduction of VR\n")
            .append("\tVR is defined as a new type of human-computer interaction.\n")
            .append("\tVR is a technology that has just emerged in the past two years, changing the traditional way of information processing.\n")
            .append("\n")
            .append("The uses for VR\n")
            .append("\tVR can be used to improve the gaming experience.\n")
            .append("\tVR can also be used to improve audience participation.\n")
            .append("\tVR can be used in medical, educational and other fields. For example, to help doctors solving medical problem and help teachers with their lecture.\n")
            .append("\tThere are many uses for VR and when we present the content, we can display some typical use of this scenarios\n")
            .append("\n")
            .toString();
    private String content2 =  new StringBuffer("(Append)\n")
            .append("The implementation of VR\n")
            .append("\tThe key is to give people a real feeling by the difference of distance and angle\n")
            .append("\tThe implementation of VR should be under the support of some hardware\n")
            .append("\tAlso There must be a special mechanism to track the activities of the human eye, such as mobile\n")
            .append("\n")
            .toString();


    @Autowired
    public ApiSpeechServiceBean(RecordDAO recordDAO, SpeechItemDAO speechItemDAO) {
        this.speechTranslateAPI = new SpeechTranslateImpl();
        this.entityLinkAPI = new EntityLinkImpl();
        this.recordDAO = recordDAO;
        this.speechItemDAO = speechItemDAO;
    }

    @Override
    public String translateSpeech(Integer recordId, byte[] speech) {
        String content = speechTranslateAPI.translateSpeech(speech);
//        if(content==null){
//            return "0";
//        }
        SpeechItem item = new SpeechItem();
        item.setContent(content);
        item.setRecordId(recordId);
        item.setDate(new Date(Calendar.getInstance().getTimeInMillis()));

        speechItemDAO.saveAndFlush(item);
        return "1";

    }

    @Override
    public String translateSpeechTest(Integer recordId) {
        RecordModel recordModel = recordDAO.findOne(recordId);
        if(recordModel==null) return null;

        if(recordModel.getState()==RecordState.recording){
            return content1 + entityLinkAPI.getEntities(content1,null,0);
        }

        return content1 + content2 + entityLinkAPI.getEntities(content1+content2,null,0);
    }

    @Override
    public String continueSpeech(Integer recordId) {
        RecordModel recordModel = recordDAO.findOne(recordId);
        if(recordModel==null) return "-1";
        recordModel.setState(RecordState.analysing);
        recordDAO.saveAndFlush(recordModel);
        return "1";
    }


    @Override
    public String stopSpeech(Integer recordId) {
        RecordModel recordModel = recordDAO.findOne(recordId);
        if(recordModel==null) return null;

        recordModel.setState(RecordState.analysing);
        recordDAO.saveAndFlush(recordModel);

        return speechItemDAO
                .findByRecordId(recordId)
                .stream()
                .map(SpeechItem::getContent)
                .reduce("", String::concat);

    }

    @Override
    public int cancel(Integer recordId) {
        RecordModel recordModel = recordDAO.findOne(recordId);
        if(recordModel==null) return -1;
        recordModel.setState(RecordState.cancel);
        recordDAO.saveAndFlush(recordModel);
        speechItemDAO.delete(speechItemDAO.findByRecordId(recordId));
        return 1;
    }


}
