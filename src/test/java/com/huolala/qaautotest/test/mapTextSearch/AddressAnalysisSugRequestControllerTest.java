package com.huolala.qaautotest.test.mapTextSearch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huolala.core.api.mapTextSearch.TextSearchController;
import com.huolala.core.model.request.mapTextSearch.TextsearchV1AddrAlyRequest;
import com.huolala.core.model.response.CommonResponse;
import com.huolala.qaautotest.util.DataProviderCommon;
import com.huolala.qaautotest.util.ParamEntity;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class AddressAnalysisSugRequestControllerTest {

    private final TextSearchController textSearchController = new TextSearchController();
    private final CommonResponse<Object> res = new CommonResponse<>();

    @Test(testName = "智能地址解析high", dataProvider = "dataProvider", dataProviderClass = DataProviderCommon.class)
    public void textSearchController_addressAnalysisHigh(ParamEntity paramEntity) {
        Reporter.log("用例名称：" + paramEntity.getCaseName());
        Reporter.log("请求参数：" + paramEntity.getData());
        System.out.println("用例名称：" + paramEntity.getCaseName());
        System.out.println("请求参数：" + paramEntity.getData());

        TextsearchV1AddrAlyRequest request = JSON.parseObject(
                paramEntity.getData(),
                TextsearchV1AddrAlyRequest.class
        );
        textSearchController.handle(paramEntity.getEnv(), request, res);

        Reporter.log("响应参数：" + res);
        System.out.println("响应参数：" + res);

        Assert.assertEquals(res.getStatus(), 200, "HTTP状态码校验失败");
        Assert.assertEquals(res.getRet(), 0, "业务返回码校验失败");
        Assert.assertTrue(res.getMsg().contains("请求成功"), "响应消息校验失败");

        JSONObject result = JSONObject.parseObject(res.getData().toString());
        String poi = result.getString("poi");
        System.out.println("poi字段值：" + poi);

        if ("[]".equals(poi)) {
            Assert.assertTrue(
                    result.getString("candidate_location").contains("\"addrAlyLevel\":\"high\""),
                    "地址解析级别校验失败"
            );
        } else {
            Assert.assertNull(result.getString("top_location"), "top_location应为空");
        }
    }
}