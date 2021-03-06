package com.byn.article.service;

import com.byn.article.fo.ArticleAddFO;
import com.byn.article.fo.ArticleFO;
import com.byn.article.fo.ArticleSaveFO;
import com.byn.article.vo.ArticleVO;
import com.byn.common.session.entity.SessionUserDetail;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 文章服务层代码
 *
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
     * 根据id修改文章
     */
    int updateArticle(ArticleSaveFO articleSaveFO, SessionUserDetail sessionUser);

    /**
     * 新增
     */
    int addArticleByCat0(ArticleAddFO articleAddFO, SessionUserDetail sessionUser);

    /**
     * 新增
     */
    int addArticleByCat1(ArticleAddFO articleAddFO, SessionUserDetail sessionUser);

    /**
     * 根据id删除数据字典项
     */
    int deleteArticle(ArticleSaveFO articleSaveFO, SessionUserDetail sessionUser);

}
