package pers.guzx.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SystemCode implements CommonEnum {

    SUCCESS("200", "已在响应中发出"),
    CREATED("201", "新资源被创建"),
    ACCEPTED("202", "已接受处理请求但尚未完成（异步处理）"),
    NULL("204", "请求失败"),
    MOVED_PERMANENTLY("301", "资源的URI已被更新"),
    SEE_OTHER("303", "其他（如，负载均衡）"),
    NOT_MODIFIED("304", "资源未更改（缓存）"),
    BAD_REQUEST("400", "坏请求（如，参数错误）"),
    NOT_FOUND("404", "资源不存在"),
    NOT_ACCEPTABLE("406", "服务端不支持所需表示"),
    CONFLICT("409", "通用冲突"),
    PRECONDITION_FAILED("412", "前置条件失败（如执行条件更新时的冲突）"),
    UNSUPPORTED_MEDIA_TYPE("415", " 接受到的媒体类型不受支持"),
    INTERNAL_SERVER_ERROR("500", "通用错误响应"),
    SERVICE_UNAVAILABLE("503", "服务端当前无法处理请求"),;

    private String value;
    private String description;

    @Override
    public String getValueObject() {
        return this.value;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
