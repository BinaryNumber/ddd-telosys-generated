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

import org.demo.orders.domain.model.Product;

import org.demo.orders.domain.model.Category;

@Mapper
public interface ProductSqlMapper extends SqlMapper<Product, String> {


	static final String TABLE = "product" ; 

	//----------------------------------------------------------------
	// Standard methods implementation 
	//----------------------------------------------------------------
	@Override
	@Select("SELECT COUNT(*) FROM " + TABLE)
	long count();


	@Override
	@Select("SELECT * FROM " + TABLE  +
			" WHERE code = #{code} " )
	Product findById(String id);


	@Override
	@Select("SELECT * FROM " + TABLE + " ORDER BY ${sort} ${order} OFFSET ${offset} LIMIT ${limit}")
	List<Product> findAll(@Param("offset") Integer offset, @Param("limit") Integer limit,
								  @Param("sort")   String sort,    @Param("order") String order);

	@Override
	@Insert("INSERT INTO " + TABLE + "( " +
			"code, name, description, unit_price, in_stock, active_for_sale, category_id, catalog_year, catalog_quarter" +
			" ) VALUES ( " +
			"#{code}, #{name}, #{description}, #{unitPrice}, #{inStock}, #{activeForSale}, #{categoryId}, #{catalogYear}, #{catalogQuarter}" +
			")")
	int insert(Product product);

	@Override
	@Update("UPDATE " + TABLE + 
			" SET " +
			" name = #{name}, " +
			" description = #{description}, " +
			" unit_price = #{unitPrice}, " +
			" in_stock = #{inStock}, " +
			" active_for_sale = #{activeForSale}, " +
			" category_id = #{categoryId}, " +
			" catalog_year = #{catalogYear}, " +
			" catalog_quarter = #{catalogQuarter} " +
			" WHERE code = #{code}" )
	int update(Product product);

	@Override
	@Delete("DELETE FROM " + TABLE + 
			" WHERE code = #{code}" )
	int delete(Product product);


	//----------------------------------------------------------------
	// Ajouter ici les méthodes spécifiques 'findByXxx', 'countByXxxx', etc
	//----------------------------------------------------------------
/***
	@Select("SELECT * FROM " + TABLE +
			" WHERE xxx = #{xxx} ORDER BY ${sort} ${order} OFFSET ${offset} LIMIT ${limit}")
	List<Product> findByXxxx(
			@Param("xxx") int xxx, 
			@Param("offset") Integer offset,
			@Param("limit") Integer limit, 
			@Param("sort") String sort, 
			@Param("order") String order);

	@Select("SELECT COUNT(*) FROM " + TABLE + " WHERE xxx = #{param1}")
	long countByXxxx(int xxx);
***/
}
