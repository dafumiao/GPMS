package com.rzq.gpms.api.theme.dao;

import com.rzq.gpms.api.theme.domain.Theme;
import com.rzq.gpms.api.theme.domain.ThemeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ThemeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_theme
     *
     * @mbggenerated
     */
    int countByExample(ThemeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_theme
     *
     * @mbggenerated
     */
    int deleteByExample(ThemeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_theme
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_theme
     *
     * @mbggenerated
     */
    int insert(Theme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_theme
     *
     * @mbggenerated
     */
    int insertSelective(Theme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_theme
     *
     * @mbggenerated
     */
    List<Theme> selectByExampleWithRowbounds(ThemeCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_theme
     *
     * @mbggenerated
     */
    List<Theme> selectByExample(ThemeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_theme
     *
     * @mbggenerated
     */
    Theme selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_theme
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Theme record, @Param("example") ThemeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_theme
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Theme record, @Param("example") ThemeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_theme
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Theme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_theme
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Theme record);
}