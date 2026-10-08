package com.huolala.qaautotest.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.testng.annotations.DataProvider;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Scanner;

public class DataProviderCommon {

    @DataProvider
    public static Object[][] dataProvider(Method method) {
        String basePath = "/dataProvider";
        String fullPackage = method.getDeclaringClass().getPackage().getName();
        String[] packageArr = fullPackage.split("\\.");
        String lastPackageName = packageArr[packageArr.length - 1];
        String className = method.getDeclaringClass().getSimpleName().replace("Test", "");
        String filePath = basePath + "/" + lastPackageName + "/" + className + ".json";
        return generateDataProvider(filePath);
    }

    private static Object[][] generateDataProvider(String filePath) {
        try (InputStream is = DataProviderCommon.class.getResourceAsStream(filePath)) {
            if (is == null) {
                throw new RuntimeException("测试数据文件未找到: " + filePath);
            }
            Scanner scanner = new Scanner(is, "UTF-8");
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
            scanner.close();

            JSONArray dataArray = JSON.parseArray(sb.toString());
            Object[][] result = new Object[dataArray.size()][1];
            for (int i = 0; i < dataArray.size(); i++) {
                JSONObject item = dataArray.getJSONObject(i);
                ParamEntity paramEntity = new ParamEntity();
                paramEntity.setCaseName(item.getString("case_name"));
                paramEntity.setEnv(item.getString("env"));
                paramEntity.setData(item.getJSONObject("data").toJSONString());
                result[i][0] = paramEntity;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("加载测试数据失败", e);
        }
    }
}