package com.intellichens.api;

import java.util.List;

/**
 * Created by raychen on 2017/3/26.
 */
public interface TextAnalyzeAPI {
    String getLanguage(String text, int languages);
    String getKeys(String text);
    String getTopic(List<String> docs);
    String getSentiment(String text);
}
