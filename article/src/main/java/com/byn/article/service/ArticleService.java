package com.byn.article.service;

import com.byn.article.fo.ArticleFO;
import com.byn.article.vo.ArticleVO;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 文章服务层代码
 * @author 字母哥
 */
public interface ArticleService {

  /**
   * 查询所有
   */
  List<ArticleVO> all();

  /**
   * 根据参数查询
   */
  Page<ArticleVO> queryArticleList(ArticleFO articleFO);

  /**
   * 根据id更新数据字典项
   * @param article 更新实体(包含id)
   */
  int updateArticle(ArticleFO articleFO);

  /**
   * 新增
   */
  int addArticle(ArticleFO articleFO);

  /**
   * 根据id删除数据字典项
   * @param id  删除项的id
   */
  int deleteArticle(ArticleFO articleFO);

}
