package com.intellichens.controller;

import com.intellichens.service.ApiSpeechService;
import com.intellichens.service.ApiTextAnalyzeService;
import com.intellichens.util.ResultUtil;
import org.json.simple.JSONObject;
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
    private final ApiTextAnalyzeService apiTextAnalyzeService;

    @Autowired
    public SpeechController(ApiSpeechService apiSpeechService, ApiTextAnalyzeService apiTextAnalyzeService) {
        this.apiSpeechService = apiSpeechService;
        this.apiTextAnalyzeService = apiTextAnalyzeService;
    }

    /**
     *
     * @param recordId
     * @param request
     * @return if the operation is success
     */
    @RequestMapping("/receive")
    @ResponseBody
    public JSONObject receiveSpeech(Integer recordId, HttpServletRequest request){
        return speechTranslator(recordId,request,apiSpeechService::translateSpeech);
    }

    @RequestMapping("/continue")
    @ResponseBody
    public JSONObject continueSpeech(Integer recordId){
        return ResultUtil.wrapResult(apiSpeechService.continueSpeech(recordId));
    }

    /**
     *
     * @param recordId
     * @return if the operation is success
     */
    @RequestMapping("/stop")
    @ResponseBody
    public JSONObject stopSpeech(Integer recordId){
        return ResultUtil.wrapResult(apiSpeechService.translateSpeechTest(recordId));
//        String content = apiSpeechService.stopSpeech(recordId);
//        if(content==null){
//            return ResultUtil.wrapResult(-1);
//        }
//        return ResultUtil.wrapResult(apiTextAnalyzeService.analyzeText(content,recordId));
    }

    /**
     *
     * @param recordId
     * @return if the operation is success
     */
    @RequestMapping("/cancel")
    @ResponseBody
    public JSONObject cancel(Integer recordId){
        return ResultUtil.wrapResult(apiSpeechService.cancel(recordId));
    }

    private JSONObject speechTranslator(Integer recordId, HttpServletRequest request, Translator translator){
        try {
            BufferedReader reader = request.getReader();
            char[] buf = new char[512];
            int len;
            StringBuilder speechBuilder = new StringBuilder();
            while ((len = reader.read(buf)) != -1) {
                speechBuilder.append(buf, 0, len);
            }
            String content = speechBuilder.toString();

            return ResultUtil.wrapResult(translator.translate(recordId, content.getBytes()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    interface Translator{
        String translate(Integer recordId, byte[] speech);
    }
}
