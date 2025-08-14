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
        if(content == null || content.trim().isEmpty()) {
            return CompletableFuture.completedFuture("Neutral");
        }
        String newContent = "I had a wonderful experience today. Everyone was kind, and the service was excellent!";
        String sentiment = sentimentAnalyzer.analyzeSentiment(newContent);
        System.out.println("Sentiment"+sentiment);
        return CompletableFuture.completedFuture(sentiment);
    }
}
