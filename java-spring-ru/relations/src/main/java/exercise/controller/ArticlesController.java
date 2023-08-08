package exercise.controller;

import exercise.dto.ArticleDto;
import exercise.model.Article;
import exercise.model.Category;
import exercise.repository.ArticleRepository;

import exercise.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticlesController {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @GetMapping(path = "")
    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteArticle(@PathVariable long id) {
        articleRepository.deleteById(id);
    }

    // BEGIN
    @PostMapping(path = "")
    public Article createArticle(@RequestBody ArticleDto articleDto) {
        Category category = categoryRepository.findById(articleDto.getCategory().getId());
        Article article = new Article();
        article.setBody(articleDto.getBody());
        article.setName(articleDto.getName());
        article.setCategory(category);

        return this.articleRepository.save(article);
    }
    @PatchMapping(path = "/{id}")
    public Article updateArticle(@PathVariable long id, @RequestBody Article article) {
        article.setId(id);
        return this.articleRepository.save(article);
    }
    @GetMapping(path = "/{id}")
    public Article getArticle(@PathVariable long id) {
        return this.articleRepository.findById(id);
    }
    // END
}
