package org.godigit.NewsAnalyzer.Services;

import org.godigit.NewsAnalyzer.Utils.SentimentAnalyzer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class SentimentService {

    private final SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();

    @Async("taskExecutor")
    public CompletableFuture<String> detectBiasAsync(String content) {
        String sentiment = sentimentAnalyzer.analyzeSentiment(content);
        return CompletableFuture.completedFuture(sentiment);
    }
}
