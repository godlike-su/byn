package com.byn.article.service.impl;

import com.byn.article.entity.Article;
import com.byn.article.fo.ArticleAddFO;
import com.byn.article.fo.ArticleFO;
import com.byn.article.mapper.ArticleMapper;
import com.byn.article.service.ArticleService;
import com.byn.article.vo.ArticleVO;
import com.byn.common.exception.GenerallyeException;
import com.byn.common.session.entity.SessionUserDetail;
import com.byn.common.util.ObjectTransform;
import com.byn.common.util.SnowFlakeUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        Page<ArticleVO> articleVOS = articleMapper.queryArticleList(null);
        return articleVOS;
    }

    @Override
    public Page<ArticleVO> queryArticleList(ArticleFO articleFO) {
        PageHelper.startPage(articleFO.getPage(), articleFO.getRows());
        Article article = ObjectTransform.transform(articleFO, Article.class);
        article.setAudit("1");
        article.setDelflag("0");
        Page<ArticleVO> articleVOS = articleMapper.queryArticleList(article);
        return articleVOS;
    }

    @Override
    public int updateArticle(ArticleFO articleFO, SessionUserDetail sessionUser) {
        Article article = ObjectTransform.transform(articleFO, Article.class);
        article.setUserid(sessionUser.getUserId());
        article.setArticleid(sessionUser.getUserId());
        article.setTimeupdate(new Date());
        int num = articleMapper.updateByArticleSelective(article);
        if (num != 1) {
            throw new GenerallyeException("修改文章失败!");
        }
        return num;
    }

    @Override
    public int addArticle(ArticleAddFO articleAddFO, SessionUserDetail sessionUser) {
        Article article = ObjectTransform.transform(articleAddFO, Article.class);
        article.setUserid(sessionUser.getUserId());
        article.setUserName(sessionUser.getUserName());
        article.setArticleid(String.valueOf(SnowFlakeUtil.getId()));
        article.setTimeupdate(new Date());
        int num = articleMapper.insertSelective(article);
        if (num != 1) {
            throw new GenerallyeException("新增文章失败!");
        }
        return 0;
    }

    @Override
    public int deleteArticle(ArticleFO articleFO, SessionUserDetail sessionUser) {
        Article article = ObjectTransform.transform(articleFO, Article.class);
        article.setUserid(sessionUser.getUserId());
        article.setTimeupdate(new Date());
        article.setDelflag("1");
        int num = articleMapper.updateByArticleSelective(article);
        if (num != 1) {
            throw new GenerallyeException("删除文章失败!");
        }
        return num;
    }
}
