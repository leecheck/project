package com.gytech.dto.work;

import com.gytech.entity.work.RcBasicCruiseInfo;
import com.gytech.entity.work.RcBasicCruiseProjectInfo;
import com.gytech.entity.work.RcBasicCruiseSegmentInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 航次有关属性信息
 * Created by LQ on 2020/11/15 0015.
 * com.gytech.dto.work
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CruiseRelateDto {

    RcBasicCruiseInfo basicCruiseInfo;

    RcBasicCruiseProjectInfo cruiseProjectInfo;

    List<RcBasicCruiseSegmentInfo> cruiseSegmentInfos;
}
