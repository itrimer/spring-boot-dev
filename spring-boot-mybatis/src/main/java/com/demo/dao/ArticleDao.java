package com.demo.dao;

import com.demo.model.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleDao {
    @Select("SELECT * FROM ARTICLE WHERE id = #{id}")
    Article findById(@Param("id") Long id);

    @Select("SELECT * FROM ARTICLE WHERE title like '%' || #{title} || '%'")
    List<Article> findByTitle(@Param("title") String title);

    @Insert("INSERT INTO ARTICLE(id, title, content) VALUES(#{id}, #{title}, #{content})")
    int insertByProperty(@Param("id") String id, @Param("title") String title, @Param("content") String content);

    @Insert("INSERT INTO ARTICLE(id, title, content) VALUES(#{article.id}, #{article.title}, #{article.content})")
    int insert(@Param("article") Article Article);

}