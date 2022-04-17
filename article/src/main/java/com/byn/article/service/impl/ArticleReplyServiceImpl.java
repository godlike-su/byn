package com.byn.article.service.impl;

import com.byn.article.entity.ArticleReply;
import com.byn.article.fo.ArticleReplyAddFO;
import com.byn.article.fo.ArticleReplyFO;
import com.byn.article.fo.ArticleReplySaveFO;
import com.byn.article.mapper.ArticleReplyMapper;
import com.byn.article.service.ArticleReplyService;
import com.byn.article.vo.ArticleReplyVO;
import com.byn.common.exception.GenerallyeException;
import com.byn.common.session.entity.SessionUserDetail;
import com.byn.common.util.ObjectTransform;
import com.byn.common.util.SnowFlakeUtil;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ArticleReplyServiceImpl implements ArticleReplyService {

    @Autowired
    private ArticleReplyMapper articleReplyMapper;


    @Override
    public List<ArticleReplyVO> all() {
        return null;
    }

    @Override
    public Page<ArticleReplyVO> queryArticleReplyList(ArticleReplyFO articleReplyFO) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateArticleReply(ArticleReplyFO articleReplyFO) {
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addArticleReply(ArticleReplyAddFO articleReplyAddFO, SessionUserDetail sessionUser) {
        ArticleReply articleReply = ObjectTransform.transform(articleReplyAddFO, ArticleReply.class);
        articleReply.setArticlereplyid(String.valueOf(SnowFlakeUtil.getId()));
        articleReply.setUserid(sessionUser.getUserId());
        articleReply.setUsername(sessionUser.getUserName());
        articleReply.setReplytime(new Date());
        int num = articleReplyMapper.insertSelective(articleReply);
        if (num != 1) {
            throw new GenerallyeException("文章评论失败!");
        }
        return num;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteArticleReply(ArticleReplySaveFO articleReplySaveFO, SessionUserDetail sessionUser) {
        ArticleReply articleReply = new ArticleReply();
        articleReply.setArticlereplyid(articleReplySaveFO.getArticlereplyid());
        articleReply.setUserid(sessionUser.getUserId());
        articleReply.setUsername(sessionUser.getUserName());
        articleReply.setDelflag("1");
        articleReply.setUpdatetime(new Date());
        int num = articleReplyMapper.updateByPrimaryKeySelective(articleReply);
        if (num != 1) {
            throw new GenerallyeException("删除文章评论失败!");
        }
        return num;
    }
}
