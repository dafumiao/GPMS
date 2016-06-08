package com.rzq.gpms.api.userFile.dao;

import com.rzq.gpms.api.userFile.domain.UserFile;
import com.rzq.gpms.api.userFile.domain.UserFileCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserFileMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_file
     *
     * @mbggenerated
     */
    int countByExample(UserFileCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_file
     *
     * @mbggenerated
     */
    int deleteByExample(UserFileCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_file
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_file
     *
     * @mbggenerated
     */
    int insert(UserFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_file
     *
     * @mbggenerated
     */
    int insertSelective(UserFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_file
     *
     * @mbggenerated
     */
    List<UserFile> selectByExampleWithRowbounds(UserFileCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_file
     *
     * @mbggenerated
     */
    List<UserFile> selectByExample(UserFileCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_file
     *
     * @mbggenerated
     */
    UserFile selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_file
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") UserFile record, @Param("example") UserFileCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_file
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") UserFile record, @Param("example") UserFileCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_file
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table man_file
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserFile record);
}