package com.rytec.rec.db.mapper;

import com.rytec.rec.db.model.PanoramaDevice;
import com.rytec.rec.db.model.PanoramaDeviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface PanoramaDeviceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table panoramadevice
     *
     * @mbggenerated Wed May 10 09:18:04 CST 2017
     */
    int countByExample(PanoramaDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table panoramadevice
     *
     * @mbggenerated Wed May 10 09:18:04 CST 2017
     */
    int deleteByExample(PanoramaDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table panoramadevice
     *
     * @mbggenerated Wed May 10 09:18:04 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table panoramadevice
     *
     * @mbggenerated Wed May 10 09:18:04 CST 2017
     */
    int insert(PanoramaDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table panoramadevice
     *
     * @mbggenerated Wed May 10 09:18:04 CST 2017
     */
    int insertSelective(PanoramaDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table panoramadevice
     *
     * @mbggenerated Wed May 10 09:18:04 CST 2017
     */
    List<PanoramaDevice> selectByExampleWithRowbounds(PanoramaDeviceExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table panoramadevice
     *
     * @mbggenerated Wed May 10 09:18:04 CST 2017
     */
    List<PanoramaDevice> selectByExample(PanoramaDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table panoramadevice
     *
     * @mbggenerated Wed May 10 09:18:04 CST 2017
     */
    PanoramaDevice selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table panoramadevice
     *
     * @mbggenerated Wed May 10 09:18:04 CST 2017
     */
    int updateByExampleSelective(@Param("record") PanoramaDevice record, @Param("example") PanoramaDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table panoramadevice
     *
     * @mbggenerated Wed May 10 09:18:04 CST 2017
     */
    int updateByExample(@Param("record") PanoramaDevice record, @Param("example") PanoramaDeviceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table panoramadevice
     *
     * @mbggenerated Wed May 10 09:18:04 CST 2017
     */
    int updateByPrimaryKeySelective(PanoramaDevice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table panoramadevice
     *
     * @mbggenerated Wed May 10 09:18:04 CST 2017
     */
    int updateByPrimaryKey(PanoramaDevice record);
}