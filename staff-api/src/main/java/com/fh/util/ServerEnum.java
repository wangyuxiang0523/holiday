package com.fh.util;

public enum ServerEnum {
     SUCCESS(200,"成功")
    ,ERROR(500,"失败")
    ,PHONE_NULL(6000,"手机号为空")
    ,PHONE_OR_CODE_NULL(6001,"手机号或验证码不能为空")
    ,CODE_ERROR(6002,"验证码错误")
    ,TOKEN_TIMEOUT(7000,"token失效了")
    ,SAFETY_ERROR(7001,"token解析失败")
    ,STOCK_INSUFFICIENT(499,"库存不足")
    ,CREATE_PAY_QRCODE_ERROR(5000,"二维码生成失败")
    ,ORDER_PAY_TIMEOUT(5001,"支付超时")
    ,ORDER_STATUS_NOPAY(5003,"订单未支付")
    ,ORDER_STATUS_SUCCESS(200,"订单支付成功")
    ,CARS_ORDER_NULL(5009,"订单为空，重新加入")
    ,NO_ORDER_PAY(5007,"没有要支付的订单")
    ,VIP_LOGIN_ING(5600,"用户已在其他地方登录")
    ,EXPORT_NULL(7001,"导出数据是空的")
    ;
    private  Integer code;
    private  String  message;
    private ServerEnum(Integer code, String message){
        this.code=code;
        this.message=message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
