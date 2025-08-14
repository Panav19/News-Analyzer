package org.godigit.NewsAnalyzer.Services;

import org.godigit.NewsAnalyzer.Dto.Article;
import org.godigit.NewsAnalyzer.Dto.NewsResponse;
import org.godigit.NewsAnalyzer.Models.NewsEntity;
import org.godigit.NewsAnalyzer.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class NewsService {

    private String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=5be2831aec68474f9e6147d1c26bbc2c";

    @Autowired
    
    private NewsRepository newsRepository;

    @Async("taskExecutor")
    public CompletableFuture<List<Article>> fetchNewsAsync() {
        NewsResponse newsResponse = new RestTemplate().getForObject(url, NewsResponse.class);
        return CompletableFuture.completedFuture(newsResponse.getArticles());
    }

    public void saveAnalyzedNews(String title, String summary, String bias) {
        NewsEntity newsEntity = new NewsEntity(title, summary, bias);
        newsRepository.save(newsEntity);
    }
}
