package com.huolala.qaautotest.util;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class ParamEntity {

    @JSONField(name = "case_name")
    private String caseName;

    @JSONField(name = "data")
    private String data;

    @JSONField(name = "env")
    private String env;
}