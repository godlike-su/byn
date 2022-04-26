package com.byn.article.mapper;

import com.byn.article.entity.ArticleReply;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleReplyMapper {
    int deleteByPrimaryKey(String articlereplyid);

    int insert(ArticleReply record);

    int insertSelective(ArticleReply record);

    ArticleReply selectByPrimaryKey(String articlereplyid);

    int updateByPrimaryKeySelective(ArticleReply record);

    int updateByPrimaryKeyWithBLOBs(ArticleReply record);

    int updateByPrimaryKey(ArticleReply record);

    /**
     * 根据回复id和用户id修改
     * @param record
     * @return
     */
    int updateByArticleReplySelective(ArticleReply record);
}