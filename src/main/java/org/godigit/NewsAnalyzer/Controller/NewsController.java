package org.godigit.NewsAnalyzer.Controller;

import org.godigit.NewsAnalyzer.Dto.AnalyzedNews;
import org.godigit.NewsAnalyzer.Models.NewsEntity;
import org.godigit.NewsAnalyzer.Repository.NewsRepository;
import org.godigit.NewsAnalyzer.Services.NewsService;
import org.godigit.NewsAnalyzer.Services.SentimentService;
import org.godigit.NewsAnalyzer.Services.SummarizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private SummarizerService summarizerService;

    @Autowired
    private SentimentService sentimentService;

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/analyze")
    public CompletableFuture<List<AnalyzedNews>> getAnalyzedNews() throws Exception {
        return newsService.fetchNewsAsync().thenCompose(articles -> {
            List<CompletableFuture<AnalyzedNews>> futures = articles.stream()
                    .map(article -> {

                        CompletableFuture<String> summaryFuture = summarizerService.summarizeAsync(article.getContent());
                        CompletableFuture<String> biasFuture = sentimentService.detectBiasAsync(article.getContent());
                        try {
                            System.out.println("BiasFuture is " + biasFuture.get());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return summaryFuture.thenCombine(biasFuture, (summary, bias) -> {
                            AnalyzedNews analyzedNews = new AnalyzedNews(article.getTitle(), summary, bias);

                            NewsEntity newsEntity = new NewsEntity(analyzedNews.getTitle(), analyzedNews.getSummary(), analyzedNews.getBias());
                            newsRepository.save(newsEntity);

                            return analyzedNews;
                        });
                    })
                    .collect(Collectors.toList());
            return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .thenApply(v -> futures.stream()
                            .map(CompletableFuture :: join)
                            .collect(Collectors.toList()));
        });
    }
}
