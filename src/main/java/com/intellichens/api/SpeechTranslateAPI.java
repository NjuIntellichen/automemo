package com.intellichens.api;

/**
 * @author yuminchen
 * @version V1.0
 * @date 2017/3/26
 */
public interface SpeechTranslateAPI {
    String translateSpeech(String fileName);

    String translateSpeech(byte[] speechStream);
}
