package com.byn.common.exception.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/3 22:35`
 * @Version: 1.0
 * @Description: 自定义返回错误
 */
public class ErrorResult implements Result {
    private static final long serialVersionUID = 1L;
    //value = "状态，200-成功，400-出错，401-未登录，403-禁止访问，900-数据重复，901-验证失败",
    protected String status = "400";
    protected String message = "错误";

    public ErrorResult(String status, String message) {
        this.message = message;
        this.status = status;
    }

    public ErrorResult() {
    }

    public ErrorResult(String message) {
        this.message = message;
    }

    @Override
    public void render(Map<String, ?> map
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
        JSONObject json = new JSONObject(true);
        json.put("status", status);
        json.put("message", message);

        response.setStatus(400);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        response.getWriter().print( JSON.toJSONString(json) );
    }
}
