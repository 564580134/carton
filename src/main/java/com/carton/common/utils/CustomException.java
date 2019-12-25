package com.carton.common.utils;

import com.carton.common.utils.enums.ExceptionEnum;
import com.carton.common.utils.enums.ResponseEnum;
import lombok.Getter;

/**
 * 自定义异常处理
 *
 * @Author: TaoLiPing
 * @Date: 2019/5/5
 */

@Getter
public class CustomException extends RuntimeException {

    public Integer code;

    public CustomException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }

    public CustomException(String message) {
        super(message);
        this.code = ResponseEnum.EXCEPTION.getCode();
    }
}
