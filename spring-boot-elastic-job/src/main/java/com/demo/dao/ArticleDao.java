package com.demo.dao;

import com.demo.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleDao extends CrudRepository<Article, Long> {

}