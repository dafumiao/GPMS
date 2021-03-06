package com.rzq.gpms.api.roleTree.dao;

import com.rzq.gpms.api.roleTree.domain.RoleTree;
import com.rzq.gpms.api.roleTree.domain.RoleTreeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RoleTreeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_tree
     *
     * @mbggenerated
     */
    int countByExample(RoleTreeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_tree
     *
     * @mbggenerated
     */
    int deleteByExample(RoleTreeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_tree
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(@Param("roleid") Integer roleid, @Param("pageid") Integer pageid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_tree
     *
     * @mbggenerated
     */
    int insert(RoleTree record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_tree
     *
     * @mbggenerated
     */
    int insertSelective(RoleTree record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_tree
     *
     * @mbggenerated
     */
    List<RoleTree> selectByExampleWithRowbounds(RoleTreeCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_tree
     *
     * @mbggenerated
     */
    List<RoleTree> selectByExample(RoleTreeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_tree
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") RoleTree record, @Param("example") RoleTreeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role_tree
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") RoleTree record, @Param("example") RoleTreeCriteria example);
}