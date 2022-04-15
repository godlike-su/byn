package com.byn.article.service.impl;

import com.byn.article.fo.ArticleFO;
import com.byn.article.mapper.ArticleMapper;
import com.byn.article.service.ArticleService;
import com.byn.article.vo.ArticleVO;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author 
 * @since 2022-04-08
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<ArticleVO> all() {
        return null;
    }

    @Override
    public Page<ArticleVO> queryArticleList(ArticleFO articleFO) {
        return null;
    }

    @Override
    public int updateArticle(ArticleFO articleFO) {
        return 0;
    }

    @Override
    public int addArticle(ArticleFO articleFO) {
        return 0;
    }

    @Override
    public int deleteArticle(ArticleFO articleFO) {
        return 0;
    }
}
