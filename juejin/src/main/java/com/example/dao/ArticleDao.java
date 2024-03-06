package com.example.dao;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleDao {
    // 获取最热榜单
    @Select("SELECT * from articles order by visit_count desc limit #{count}")
    List<Article> getArticleByVisitCount(int count);

    // 获取最新榜单
    @Select("SELECT * from articles order by created_at desc limit #{count}")
    List<Article> getArticleByCreatedAt(int count);

    // 按token查询用户id
    @Select("select user_id from users where token = #{token}")
    public int getUserIdByToken(String token);

    // 按article_id查询article信息
    @Select("select * from articles where article_id = #{article_id}")
    public Article getArticleById(int article_id);

    // 按article_id查询评论列表
    @Select("select * from comments where article_id = #{article_id}")
    public List<Comment> getCommentListByArticleId(int article_id);

    // 按user_id查询文章列表
    @Select("select * from articles where user_id = #{user_id}")
    public List<Article> getArticleListByUserId(int user_id);

    // 按user_id查询赞过的文章id列表
    @Select("select * from user_like_article where user_id = #{user_id}")
    public List<Integer> getUserLikeArticleIdByUserId(int user_id);

    // 向articles表中插入数据
    @Insert("insert into articles (user_id,title,content,cover_url,created_at) values " +
            "(#{user_id},#{title},#{content},#{cover_url},#{created_at})")
    public int insertArticle(Article article);

}
