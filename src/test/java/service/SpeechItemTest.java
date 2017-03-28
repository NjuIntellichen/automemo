package service;

import com.intellichens.api.impl.TextAnalyzeImpl;
import com.intellichens.dao.SpeechItemDAO;
import com.intellichens.model.SpeechItem;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * @author yuminchen
 * @version V1.0
 * @date 2017/3/28
 */
public class SpeechItemTest {

    private SpeechItemDAO speechItemDAO;

    @Autowired
    @Before
    public void setup(SpeechItemDAO speechItemDAO){
        this.speechItemDAO = speechItemDAO;
    }

    @Test
    public void testSave(){
        for(int i = 0;i < 10; i++){
            SpeechItem item = new SpeechItem();
            item.setContent("test"+i);
            item.setRecordId(1);
            item.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
            speechItemDAO.saveAndFlush(item);
        }

        List<SpeechItem> speechItems = speechItemDAO.findByRecordId(1);
        for(SpeechItem speechItem : speechItems){
            System.out.println(speechItem.getDate()+ speechItem.getContent());
        }

    }
}
