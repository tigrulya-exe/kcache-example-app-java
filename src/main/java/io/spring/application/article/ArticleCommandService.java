package io.spring.application.article;

import com.sun.tools.javac.util.List;
import io.spring.core.article.Article;
import io.spring.core.article.ArticleRepository;
import io.spring.core.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.UUID;

@Service
@Validated
public class ArticleCommandService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleCommandService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article createArticle(@Valid NewArticleParam newArticleParam, User creator) {
        Article article =
                new Article(
                        newArticleParam.getTitle(),
                        newArticleParam.getDescription(),
                        newArticleParam.getBody(),
                        newArticleParam.getTagList(),
                        creator.getId());
        articleRepository.save(article);
        return article;
    }

    public Article updateArticle(Article article, @Valid UpdateArticleParam updateArticleParam) {
        article.update(
                updateArticleParam.getTitle(),
                updateArticleParam.getDescription(),
                updateArticleParam.getBody());
        articleRepository.save(article);
        return article;
    }

    public void evict() {
        articleRepository.save(new Article(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                "BODY",
                List.of("TAG"),
                "75317"
        ));
    }
}
