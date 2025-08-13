package org.godigit.NewsAnalyzer.Services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class SentimentService {

    @Async("taskExecutor")
    public CompletableFuture<String> detectBiasAsync(String content) {
        String[] bias = {"Positive", "Neutral", "Negative"};
        int index = (int) (Math.random() * 3);
        return CompletableFuture.completedFuture(bias[index]);
    }
}
