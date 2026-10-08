package com.huolala.qaautotest.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.huolala.core.model.response.CommonResponse;

import java.util.Map;

public class
Handle {
    private static final int TIMEOUT = 6000;

    public static void handlePostRequestRequest(String url, Object req, CommonResponse response) {
        String json = JSON.toJSONString(req);
        HttpRequest httpRequest = HttpUtil.createRequest(Method.POST, url);
        decorate(httpRequest);
        HttpResponse httpResponse = httpRequest.body(json).timeout(TIMEOUT).execute();
        parseHttpResponse(response, httpResponse);
    }

    public static void handlePostRequestRequestNew(String url, String req, CommonResponse response) {
        HttpRequest httpRequest = HttpUtil.createRequest(Method.POST, url);
        decorate(httpRequest);
        HttpResponse httpResponse = httpRequest.body(req).timeout(TIMEOUT).execute();
        parseHttpResponse(response, httpResponse);
    }

    public static String handlePostFormRequest(String url, Map<String, Object> params) {
        System.out.println("url=" + url);
        System.out.println("params=" + params);
        HttpRequest httpRequest = HttpUtil.createRequest(Method.POST, url);
        decorate(httpRequest);
        HttpResponse httpResponse = httpRequest.form(params).timeout(TIMEOUT).execute();
        return httpResponse.body();
    }

    public static void handleGetParamRequest(String url, Object req, CommonResponse response) {
        Map<String, Object> paramMap = ParseUtil.object2Map(req);
        HttpRequest httpRequest = HttpUtil.createRequest(Method.GET, url).setFollowRedirects(true);
        decorate(httpRequest);
        HttpResponse httpResponse = httpRequest.form(paramMap).timeout(TIMEOUT).execute();
        parseHttpResponse(response, httpResponse);
        System.out.println("httpRequest: " + httpRequest);
    }

    private static void decorate(HttpRequest request) {
        request.header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
    }

    @SuppressWarnings("unchecked")
    private static void parseHttpResponse(CommonResponse response, HttpResponse httpResponse) {
        response.setStatus(httpResponse.getStatus());
        String responseBody = httpResponse.body();
        response.setResult(responseBody);
        try {
            response.setData(JSON.parse(responseBody));
        } catch (Exception e) {
            response.setData(responseBody);
        }
    }
}