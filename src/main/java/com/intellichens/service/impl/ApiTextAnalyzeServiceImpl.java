package com.intellichens.service.impl;

import com.intellichens.service.ApiTextAnalyzeService;
import com.intellichens.api.impl.TextAnalyzeImpl;
import com.intellichens.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by raychen on 2017/3/24.
 */
@Service
public class ApiTextAnalyzeServiceImpl implements ApiTextAnalyzeService {

    TextAnalyzeImpl analyzer;
    @Autowired
    private RecordService recordService;

    public ApiTextAnalyzeServiceImpl(){
        analyzer = new TextAnalyzeImpl();
    }

    @Override
    public int analyzeText(String text, Integer recordId) {
        String tags = analyzer.getKeys(text);
        return recordService.updateRecord(recordId, text, null, true);
    }
}
