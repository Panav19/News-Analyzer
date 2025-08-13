package org.godigit.NewsAnalyzer.Services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class SummarizerService {

    @Async("taskExecutor")
    public CompletableFuture<String> summarizeAsync(String content) {
        String summary = "Summary of: " + content;
        return CompletableFuture.completedFuture(summary);
    }
}
