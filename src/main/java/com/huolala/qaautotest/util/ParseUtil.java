package com.huolala.qaautotest.util;

import com.alibaba.fastjson.JSON;
import java.util.Map;

public class ParseUtil {
    @SuppressWarnings("unchecked")
    public static Map<String, Object> object2Map(Object obj) {
        String jsonStr = JSON.toJSONString(obj);
        return JSON.parseObject(jsonStr, Map.class);
    }
}