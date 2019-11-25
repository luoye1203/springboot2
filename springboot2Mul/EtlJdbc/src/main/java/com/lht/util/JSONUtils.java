package com.lht.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONUtils {

    public static String jsonFormattedStr(Object object){

        if(null==object){
            return null;
        }else{
            return JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        }

    }

}
