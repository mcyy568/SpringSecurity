package com.spring.security.controller;

import java.nio.charset.Charset;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.spring.security.exception.AuthException;
import com.spring.security.exception.InvalidCaptchaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * 创建用户：杨辽
 * 创建时间：2016-10-08 15:47:00
 * 描    述：继承一些Controller的通用方法, 新建Controller的时候可以继承此类
 */
@Controller
public class DefaultAppController {

    protected final static Logger logger = LoggerFactory.getLogger(DefaultAppController.class);

    protected HttpHeaders httpHeaders = new HttpHeaders();

    public enum ResultKey {
        success,
        message,
        status,
        token,
        data
    }

    @Autowired
    protected HttpServletRequest request;

    public DefaultAppController() {
        MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
        httpHeaders.setContentType(mediaType);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> exceptionHandler(Exception ex) {
        JSONObject result = new JSONObject();

        if (ex instanceof AuthException) {
            logger.debug("error message = {}, data = {}", ex.getMessage(), ((AuthException) ex).getData());
        } else if (ex instanceof InvalidCaptchaException) {
            logger.debug("error message = {}", ex.getMessage());
        } else {
            ex.printStackTrace();
        }

        result.put(ResultKey.status.name(), Boolean.FALSE);
        result.put(ResultKey.message.name(), ex.getMessage());

        return new ResponseEntity<String>(result.toJSONString(), httpHeaders, HttpStatus.OK);
    }

    /**
     * 简化操作, 生成字符串为:
     *
     * @return { "success":true }
     */
    protected JSONObject successDataToJSONString() {
        JSONObject result = new JSONObject();
        result.put(ResultKey.status.name(), true);
        return result;
    }

    /**
     * 简化操作, 生成字符串为:
     *
     * @param obj Object
     * @return { "success":true, "data": obj }
     */
    protected JSONObject successDataToJSONString(Object obj) {
        JSONObject result = new JSONObject();
        result.put(ResultKey.status.name(), Boolean.TRUE);
        result.put(ResultKey.data.name(), obj);
        return result;
    }

}

