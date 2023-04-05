package com.example.demo.utils;

import cn.hutool.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zch
 **/
public class ResponseUtils extends HashMap<String, Object> {

    public ResponseUtils() {
        put("code", HttpStatus.HTTP_OK);
        put("message", "success");
    }

    @Override
    public ResponseUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static ResponseUtils ok() {
        return new ResponseUtils();
    }

    public static ResponseUtils ok(String msg) {
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.put("message", msg);
        return responseUtils;
    }

    public static ResponseUtils ok(Map<String, Object> map) {
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.putAll(map);
        return responseUtils;
    }

    public static ResponseUtils error(int code, String msg) {
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.put("code", code);
        responseUtils.put("message", msg);
        return responseUtils;
    }

    public static ResponseUtils error(String msg) {
        return error(HttpStatus.HTTP_INTERNAL_ERROR, msg);
    }

    public static ResponseUtils error() {
        return error(HttpStatus.HTTP_INTERNAL_ERROR, "未知错误，请联系管理员！");
    }
}
