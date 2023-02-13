package pers.guzx.common.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.guzx.common.enums.Code;

/**
 * @author guzx
 * @version 1.0
 * @date 2022/6/21 16:23
 * @describe
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<CodeField, DataField> {
    /**
     * http code
     */
    private Code code;
    /**
     * 业务系统code
     */
    private CodeField bizCode;
    /**
     * 业务系统提示信息
     */
    private String bizMsg;
    /**
     * 异常信息
     */
    private String errorMsg;
    /**
     * 异常来源
     */
    private String errorSource;
    /**
     * 异常类名
     */
    private String errorCauseName;
    /**
     * 业务数据
     */
    private DataField data;

    @Override
    public String toString() {
        return "CommonResponse(" + "code=" + code + ", bizCode=" + bizCode + ", bizMsg=" + bizMsg + ", errorMsg=" + errorMsg + ", errorSource=" + errorSource + ", errorCauseName=" + errorCauseName + ", data=" + data + ")";
    }
}
