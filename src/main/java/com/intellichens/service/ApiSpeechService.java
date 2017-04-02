package com.intellichens.service;

/**
 * @author yuminchen
 * @version V1.0
 * @date 2017/3/28
 */
public interface ApiSpeechService {
    String translateSpeech(Integer recordId, byte[] speech);

    String translateSpeechTest(Integer recordId);

    String continueSpeech(Integer recordId);

    String stopSpeech(Integer recordId);

    int cancel(Integer recordId);


}
