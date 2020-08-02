package com.jianjianhe.entitybase.response;



import com.jianjianhe.entitybase.constant.ResponseCodeConstant;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 通用响应父类的封装
 * 目的：封装响应的公共属性，例如响应编码等
 * @param <T>
 */
@Data
@ToString(callSuper = true)
public class CommonResponse<T>  implements Serializable {

    private static final long serialVersionUID = -3214885479484534221L;

    /**
     * 返回业务数据
     */
    private T response;

    /**
     * 响应编码
     */
    private String code;

    /**
     * 响应描述
     */
    private String content;


    private boolean success;
    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public CommonResponse<T> setResponse(T response){
        this.response = response;
        return this;
    }

    public CommonResponse<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public CommonResponse<T> setContent(String content) {
        this.content = content;
        return this;
    }

    public CommonResponse<T> setSuccessMsg(T t){
        this.setCode(ResponseCodeConstant.SUCCESS.getResponseCode());
        this.setContent(ResponseCodeConstant.SUCCESS.getResponseDesc());
        this.setSuccess(true);
        this.setResponse(t);
        return this;
    }
}

