package com.intellichens.controller;

import com.intellichens.api.SpeechTranslateAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author yuminchen
 * @version V1.0
 * @date 2017/3/28
 */
@RestController
@RequestMapping("/speech")
public class SpeechController {

    private final SpeechTranslateAPI speechTranslator;

    @Autowired
    public SpeechController(SpeechTranslateAPI speechTranslator) {
        this.speechTranslator = speechTranslator;
    }

    @RequestMapping("/receive")
    @ResponseBody
    public String receiveSpeech(Integer recordId, Integer status, HttpServletRequest request){
        try {
            BufferedReader reader = request.getReader();
            char[] buf = new char[512];
            int len;
            StringBuilder speechBuilder = new StringBuilder();
            while ((len = reader.read(buf)) != -1) {
                speechBuilder.append(buf, 0, len);
            }

            String content = speechBuilder.toString();

            if(content == null){
                content = "";
            }

            speechTranslator.translateSpeech(content.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
