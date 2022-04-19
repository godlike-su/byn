package com.byn.article.service.impl;

import com.byn.article.common.ArticleEnum;
import com.byn.article.entity.Article;
import com.byn.article.fo.ArticleAddFO;
import com.byn.article.fo.ArticleFO;
import com.byn.article.fo.ArticleSaveFO;
import com.byn.article.mapper.ArticleMapper;
import com.byn.article.service.ArticleService;
import com.byn.article.vo.ArticleVO;
import com.byn.common.exception.GenerallyeException;
import com.byn.common.session.entity.SessionUserDetail;
import com.byn.common.util.ObjectTransform;
import com.byn.common.util.SnowFlakeUtil;
import com.byn.openfeign.feign.CommonWebFeign;
import com.byn.openfeign.fo.UserFO;
import com.byn.openfeign.vo.UserVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.result.SingleResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private CommonWebFeign commonWebFeign;

    @Override
    public List<ArticleVO> all() {
        return articleMapper.queryArticleList(null);
    }

    @Override
    public Page<ArticleVO> queryArticleList(ArticleFO articleFO) {
        PageMethod.startPage(articleFO.getPage(), articleFO.getRows());
        Article article = ObjectTransform.transform(articleFO, Article.class);
        article.setAudit("1");
        article.setDelflag("0");
        Page<ArticleVO> articleVOS = articleMapper.queryArticleList(article);
        for (ArticleVO articleVO : articleVOS) {
            String userid = articleVO.getUserid();
            UserFO userFO = new UserFO();
            userFO.setUserId(userid);
            SingleResult<UserVO> userInformation = commonWebFeign.getUserInformation(userFO);
            UserVO userVO = userInformation.getData();
            String userThumb = Optional.ofNullable(userVO).map(UserVO::getThumb).orElse(null);
            articleVO.setUserThumb(userThumb);
        }
        return articleVOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateArticle(ArticleSaveFO articleSaveFO, SessionUserDetail sessionUser) {
        Article article = ObjectTransform.transform(articleSaveFO, Article.class);
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
    public int addArticleByCat0(ArticleAddFO articleAddFO, SessionUserDetail sessionUser) {
        Article article = ObjectTransform.transform(articleAddFO, Article.class);
        article.setUserid(sessionUser.getUserId());
        article.setUserName(sessionUser.getUserName());
        article.setArticleid(String.valueOf(SnowFlakeUtil.getId()));
        article.setCat1(ArticleEnum.CAT1_SQUARE.getKey());
        article.setTimeupdate(new Date());
        int num = articleMapper.insertSelective(article);
        if (num != 1) {
            throw new GenerallyeException("新增文章动态广场失败!");
        }
        return num;
    }

    @Override
    public int addArticleByCat1(ArticleAddFO articleAddFO, SessionUserDetail sessionUser) {
        Article article = ObjectTransform.transform(articleAddFO, Article.class);
        article.setUserid(sessionUser.getUserId());
        article.setUserName(sessionUser.getUserName());
        article.setArticleid(String.valueOf(SnowFlakeUtil.getId()));
        article.setCat1(ArticleEnum.CAT1_HOLE.getKey());
        article.setTimeupdate(new Date());
        article.setCat1("1");
        int num = articleMapper.insertSelective(article);
        if (num != 1) {
            throw new GenerallyeException("新增文章树洞失败!");
        }
        return num;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteArticle(ArticleSaveFO articleSaveFO, SessionUserDetail sessionUser) {
        Article article = ObjectTransform.transform(articleSaveFO, Article.class);
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
