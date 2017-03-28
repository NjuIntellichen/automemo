package com.intellichens.controller;

import com.intellichens.service.ApiSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    private final ApiSpeechService apiSpeechService;

    @Autowired
    public SpeechController(ApiSpeechService apiSpeechService) {
        this.apiSpeechService = apiSpeechService;
    }

    /**
     *
     * @param recordId
     * @param request
     * @return if the operation is success
     */
    @RequestMapping("/receive")
    @ResponseBody
    public String receiveSpeech(Integer recordId, HttpServletRequest request){
        try {
            BufferedReader reader = request.getReader();
            char[] buf = new char[512];
            int len;
            StringBuilder speechBuilder = new StringBuilder();
            while ((len = reader.read(buf)) != -1) {
                speechBuilder.append(buf, 0, len);
            }
            String content = speechBuilder.toString();

            return apiSpeechService.translateSpeech(recordId, content.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param recordId
     * @return the result of analysis
     */
    @RequestMapping("/stop")
    @ResponseBody
    public String stopSpeech(Integer recordId){
        return apiSpeechService.stopSpeech(recordId);
    }



}
