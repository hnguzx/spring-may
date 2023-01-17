package pers.guzx.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 25446
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "分页信息")
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("页码")
    private Long current;
    @ApiModelProperty("页大小")
    private Long size;
    @ApiModelProperty("数据总数")
    private Long total;
    @ApiModelProperty("具体数据")
    private transient List<T> results;

    public static <P, V> PageResult<V> build(Long current, Long size, Long total, List<V> results) {
        PageResult<V> pageResult = new PageResult<>();
        pageResult.setCurrent(current);
        pageResult.setSize(size);
        pageResult.setTotal(total);
        pageResult.setResults(results);
        return pageResult;
    }
}
