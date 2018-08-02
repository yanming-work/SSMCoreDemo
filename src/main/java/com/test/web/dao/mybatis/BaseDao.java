package com.test.web.dao.mybatis;

import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 通用BaseMapper<M,MB,  E, ID>
 * M:实体类
 * M:实体类(含Blob类型参数)
 * E:Example
 * ID:主键的变量类型
 *
 * @author Gavin·Yan
 *         
 *         date: 2018-08-02 13:33:51
 */
public interface BaseDao<M,MB,E, ID extends Serializable> {
    /** 
     * 查询数量
     * @param example 条件对象
     */
    long countByExample(E example);

    /** 
     * 根据条件删除
     * @param example 条件对象
     */
    int deleteByExample(E example);

    /** 
     * 添加对象所有字段
     * @param record 插入字段对象(必须含ID）
     */
    int insert(M record);

    /** 
     * 添加对象对应字段
     * @param record 插入字段对象(必须含ID）
     */
    int insertSelective(M record);

    /** 
     * 添加List集合对象所有字段
     * @param record 批量插入字段对象(必须含ID）
     */
    int insertBatch(List<M> record);

    /** 
     * 添加List集合对象对应字段
     * @param record 批量插入字段对象(必须含ID）
     */
    int insertBatchSelective(List<M> record);

    /** 
     *
     * @param record 修改字段对象(必须含ID）
     */
    int insertWithBLOBs(MB record);

    /** 
     *
     * @param record 修改字段对象(必须含ID）
     */
    int insertSelectiveWithBLOBs(MB record);

    /** 
     *
     * @param record 修改字段对象(必须含ID）
     */
    int insertBatchWithBLOBs(List<MB> record);

    /** 
     *
     * @param record 修改字段对象(必须含ID）
     */
    int insertBatchSelectiveWithBLOBs(List<MB> record);

    /** 
     * 根据条件查询（包含二进制大对象）
     * @param example 条件对象
     */
    List<MB> selectByExampleWithBLOBs(E example);

    /** 
     * 根据条件查询（二进制大对象）
     * @param example 条件对象
     */
    List<M> selectByExample(E example);

    /** 
     * 根据条件修改所有字段
     * @param record 修改字段对象 (JOPO)
     * @param example 条件对象
     */
    int updateByExample(@Param("record") M record, @Param("example") E example);

    /** 
     * 根据条件修改对应字段
     * @param record 修改字段对象 (JOPO)
     * @param example 条件对象
     */
    int updateByExampleSelective(@Param("record") M record, @Param("example") E example);

    /** 
     * 根据条件修改字段 （包含二进制大对象）
     * @param record 修改字段对象(必须含ID）
     * @param example 条件对象
     */
    int updateByExampleWithBLOBs(@Param("record") MB record, @Param("example") E example);

    /** 
     * 根据ID删除
     * @param id 主键ID
     */
    int deleteByPrimaryKey(ID id);

    /** 
     * 根据ID查询
     * @param id 主键ID
     */
    M selectByPrimaryKey(ID id);

    /** 
     *
     * @param id 主键ID
     */
    MB selectByPrimaryKeyWithBLOBs(ID id);

    /** 
     * 根据ID修改对应字段
     * @param record 修改字段对象(必须含ID）
     */
    int updateByPrimaryKeySelective(M record);

    /** 
     * 根据ID修改字段（包含二进制大对象）
     * @param record 修改字段对象(必须含ID）
     */
    int updateByPrimaryKeyWithBLOBs(MB record);

    /** 
     * 根据ID修改所有字段(必须含ID）
     * @param record 修改字段对象(必须含ID）
     */
    int updateByPrimaryKey(M record);

    /** 
     *
     * @param startRow
     * @param endRow
     * @param example 条件对象
     * @param distinct 是否过滤重复数据
     * @param orderByClause 排序字段
     */
    List<M> selectByPageExample(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("example") E example, @Param("distinct") boolean distinct, @Param("orderByClause") String orderByClause);

    /** 
     * 分页查询（包含二进制大对象）
     * @param startRow
     * @param endRow
     * @param distinct 是否过滤重复数据
     * @param orderByClause 排序字段
     * @param example 条件对象
     */
    List<MB> selectByPageExampleWithBLOBs(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("distinct") boolean distinct, @Param("orderByClause") String orderByClause, @Param("example") E example);

    /** 
     *
     * @param record 修改字段对象(必须含ID）
     * @param example 条件对象
     */
    int saveOrUpdateByExample(@Param("record") M record, @Param("example") E example);

    /** 
     * 保存或更新（包含二进制大对象）
     * @param record 修改字段对象(必须含ID）
     * @param example 条件对象
     */
    int saveOrUpdateByExampleWithBLOBs(@Param("record") MB record, @Param("example") E example);

    /** 
     *
     * @param record 修改字段对象(必须含ID）
     */
    int updateBatchByPrimaryKey(List<M> record);

    /** 
     * 批量更新（包含二进制大对象）
     * @param record 修改字段对象(必须含ID）
     */
    int updateBatchByPrimaryKeyWithBLOBs(List<MB> record);

    /** 
     *
     * @param record 修改字段对象(必须含ID）
     */
    int saveOrUpdateByPrimaryKey(M record);

    /** 
     * 批量保存或更新（包含二进制大对象）
     * @param record 修改字段对象(必须含ID）
     */
    int saveOrUpdateByPrimaryKeyWithBLOBs(MB record);
}