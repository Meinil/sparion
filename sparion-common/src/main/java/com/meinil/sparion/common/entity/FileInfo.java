package com.meinil.sparion.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Meinil
 * @date 2022/10/5
 * @description 文件实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_file_info")
@EqualsAndHashCode(callSuper = true)
public class FileInfo extends BaseEntity{
    @ApiModelProperty("文件存储的路径")
    private String path;

    @ApiModelProperty("文件uuid")
    private String uuid;

    @ApiModelProperty("文件原始名字")
    private String originName;
}
