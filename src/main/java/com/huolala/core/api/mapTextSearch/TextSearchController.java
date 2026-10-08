package com.huolala.core.api.mapTextSearch;

import com.huolala.core.api.SuperController;
import com.huolala.core.model.response.CommonResponse;
import com.huolala.qaautotest.util.Domain;
import com.huolala.qaautotest.util.Handle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("v")
public class gTextSearchController implements SuperController {

    @Override
    public CommonResponse handle(String env, Object req, CommonResponse response) {
        String url = StringUtils.join(
                Domain.MAP_TEXTSEARCH_URL,
                Domain.CROSSBAR,
                env,
                Domain.DOT_MYHLL_CN,
                Domain.MAP_TEXTSEARCH_ADDR_ALY_PATH
        );
        Handle.handleGetParamRequest(url, req, response);
        return response;
    }

    @Override
    public CommonResponse handle(Object req, CommonResponse response) {
        return handle("pre", req, response);
    }

    @Override
    public CommonResponse handle(String env, Map<String, Object> params, CommonResponse response) {
        return null;
    }
}