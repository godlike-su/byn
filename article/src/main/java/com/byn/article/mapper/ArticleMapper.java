package com.byn.article.mapper;

import com.byn.article.entity.Article;
import com.byn.article.vo.ArticleVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-04-08
 */
@Mapper
public interface ArticleMapper {

    Page<ArticleVO> queryArticleList(Article article);

    int deleteByPrimaryKey(Integer articleid);

    int insert(Article article);

    int insertSelective(Article article);

    Article selectByPrimaryKey(Integer articleid);

    int updateByPrimaryKeySelective(Article article);

    int updateByPrimaryKeyWithBLOBs(Article article);

    int updateByPrimaryKey(Article article);

    /**
     * 根据文章id和用户id修改
     * @param article
     * @return
     */
    int updateByArticleSelective(Article article);


}
