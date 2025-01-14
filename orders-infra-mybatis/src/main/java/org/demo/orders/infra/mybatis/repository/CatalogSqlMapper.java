/*
 * Generated by Telosys ( https://www.telosys.org/ )
 * 2022-04-12 (15:12:15)
 */
package org.demo.orders.infra.mybatis.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import org.demo.orders.infra.mybatis.common.SqlMapper;

import org.demo.orders.domain.model.Catalog;
import org.demo.orders.domain.model.CatalogId;


@Mapper
public interface CatalogSqlMapper extends SqlMapper<Catalog, CatalogId> {


	static final String TABLE = "catalog" ; 

	//----------------------------------------------------------------
	// Standard methods implementation 
	//----------------------------------------------------------------
	@Override
	@Select("SELECT COUNT(*) FROM " + TABLE)
	long count();


	@Override
	@Select("SELECT * FROM " + TABLE  +
			" WHERE year = #{year} AND quarter = #{quarter} " )
	Catalog findById(CatalogId id);


	@Override
	@Select("SELECT * FROM " + TABLE + " ORDER BY ${sort} ${order} OFFSET ${offset} LIMIT ${limit}")
	List<Catalog> findAll(@Param("offset") Integer offset, @Param("limit") Integer limit,
								  @Param("sort")   String sort,    @Param("order") String order);

	@Override
	@Insert("INSERT INTO " + TABLE + "( " +
			"year, quarter, title" +
			" ) VALUES ( " +
			"#{year}, #{quarter}, #{title}" +
			")")
	int insert(Catalog catalog);

	@Override
	@Update("UPDATE " + TABLE + 
			" SET " +
			" title = #{title} " +
			" WHERE year = #{year} AND quarter = #{quarter}" )
	int update(Catalog catalog);

	@Override
	@Delete("DELETE FROM " + TABLE + 
			" WHERE year = #{year} AND quarter = #{quarter}" )
	int delete(Catalog catalog);


	//----------------------------------------------------------------
	// Ajouter ici les méthodes spécifiques 'findByXxx', 'countByXxxx', etc
	//----------------------------------------------------------------
/***
	@Select("SELECT * FROM " + TABLE +
			" WHERE xxx = #{xxx} ORDER BY ${sort} ${order} OFFSET ${offset} LIMIT ${limit}")
	List<Catalog> findByXxxx(
			@Param("xxx") int xxx, 
			@Param("offset") Integer offset,
			@Param("limit") Integer limit, 
			@Param("sort") String sort, 
			@Param("order") String order);

	@Select("SELECT COUNT(*) FROM " + TABLE + " WHERE xxx = #{param1}")
	long countByXxxx(int xxx);
***/
}
