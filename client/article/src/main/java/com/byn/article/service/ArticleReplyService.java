package com.byn.article.service;

import com.byn.article.fo.ArticleReplyAddFO;
import com.byn.article.fo.ArticleReplyFO;
import com.byn.article.fo.ArticleReplySaveFO;
import com.byn.article.vo.ArticleReplyVO;
import com.byn.common.session.entity.SessionUserDetail;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 文章服务层代码
 * @author 字母哥
 */
public interface ArticleReplyService {

  /**
   * 查询所有
   */
  List<ArticleReplyVO> all();

  /**
   * 根据参数查询
   */
  Page<ArticleReplyVO> queryArticleReplyList(ArticleReplyFO articleReplyFO);

  /**
   * 根据id更新数据字典项
   */
  int updateArticleReply(ArticleReplyFO articleReplyFO);

  /**
   * 新增
   */
  int addArticleReply(ArticleReplyAddFO articleReplyAddFO, SessionUserDetail sessionUser);

  /**
   * 根据id删除数据字典项
   */
  int deleteArticleReply(ArticleReplySaveFO articleReplySaveFO, SessionUserDetail sessionUser);

}
