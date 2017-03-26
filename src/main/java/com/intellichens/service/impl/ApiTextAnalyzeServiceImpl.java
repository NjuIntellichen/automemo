package com.intellichens.service.impl;

import com.intellichens.service.ApiTextAnalyzeService;
import com.intellichens.api.impl.TextAnalyzeImpl;
import org.springframework.stereotype.Service;

/**
 * Created by raychen on 2017/3/24.
 */
@Service
public class ApiTextAnalyzeServiceImpl implements ApiTextAnalyzeService {

    TextAnalyzeImpl builder;

    public ApiTextAnalyzeServiceImpl(){
        builder = new TextAnalyzeImpl();
    }

    @Override
    public int analyzeText(String text) {
        return 0;
    }
}
