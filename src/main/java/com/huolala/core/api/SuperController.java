package com.huolala.core.api;

import com.huolala.core.model.response.CommonResponse;
import java.util.Map;

public interface SuperController {
    CommonResponse handle(String env, Object req, CommonResponse response);
    CommonResponse handle(Object req, CommonResponse response);
    CommonResponse handle(String env, Map<String, Object> params, CommonResponse response);
}


//这三个方法就是三条统一规矩：
//
//- 规矩 1：所有接口都可以通过「环境 + 请求对象 + 响应对象」的方式调用
//- 规矩 2：所有接口都可以不指定环境（默认预发），直接传请求对象调用
//- 规矩 3：所有接口都支持直接传 Map 参数调用