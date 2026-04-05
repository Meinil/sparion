package com.meinil.resource.convert;

import com.meinil.resource.domain.entity.ResFile;
import com.meinil.resource.domain.vo.ResFileVO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Meinil
 * @date 2026/4/5
 * @description 文件转换类
 */
@Mapper
public interface ResFileConvert {

    ResFileVO resFileToResFileVO(ResFile resFile);

    List<ResFileVO> resFileToResFileVO(List<ResFile> resFiles);
}
