package com.gytech.mapper.work;

import com.gytech.LocalEntity.ResultInfo;
import com.gytech.Utils.TypeFunction;
import com.gytech.dto.work.MeteorologyDto;
import com.gytech.entity.admin.SysOrganization;
import com.gytech.entity.work.StationMeteorology;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 海洋气象站位信息 Mapper 接口
 * </p>
 *
 * @author LQ
 * @since 2020-11-12
 */
public interface StationMeteorologyMapper extends BaseMapper<StationMeteorology> {


    @Select("SELECT\n" +
            "s.id,\n" +
            "\ts.cruise_segment_id,\n" +
            "\ts.metadata_id,\n" +
            "\ts.section_id,\n" +
            "\ts.time_zone,\n" +
            "\ts.obs_date,\n" +
            "\ts.obs_time,\n" +
            "\ts.station_lat,\n" +
            "\ts.station_lon,\n" +
            "\td.obs_height,\n" +
            "\td.air_pressure,\n" +
            "\td.air_temperature,\n" +
            "\td.wind_velocity,\n" +
            "\td.wind_direction,\n" +
            "\td.humidity,\n" +
            "\td.rainfall,\n" +
            "\td.remark \n" +
            "FROM\n" +
            "\tstation_meteorology s,\n" +
            "\tdata_meteorology d \n" +
            "WHERE\n" +
            "\ts.cruise_segment_id = #{segId}" +
            "\tand\n" +
            "\ts.relate_id = d.meteorology_station_id \n" +
            "\tand\n" +
            "\ts.id mod #{thin} = 0")
    List<MeteorologyDto> spaceQuery(@Param("extent")String extent, @Param("thin")Integer thin,@Param("segId")Integer segId, @Param("where")String where);

}
