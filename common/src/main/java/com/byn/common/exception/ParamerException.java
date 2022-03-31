package com.byn.common.exception;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/3 20:39`
 * @Version: 1.0
 * @Description:
 */
public class ParamerException extends BaseException{

    /** 错误码 */
    private String resultCode = "400";

    /** 错误描述 */
    private String resultMsg = "ParamerException";

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public ParamerException(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public ParamerException(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public ParamerException() {}
}
