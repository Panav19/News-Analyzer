package org.godigit.NewsAnalyzer.Utils;

import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

public class SentimentAnalyzer {
    
    private final StanfordCoreNLP pipeline;

    public SentimentAnalyzer() {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,pos,parse,sentiment");
        this.pipeline = new StanfordCoreNLP(properties);
    }

    public String analyzeSentiment(String text) {
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);

        int totalScore = 0, sentenceCount = 0;

        for(CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
            switch (sentiment) {
                case "Very positive": totalScore += 2; break;
                case "Positive": totalScore += 1; break;
                case "Neutral": totalScore += 0; break;
                case "Negative": totalScore -= 1; break;
                case "Very negative": totalScore -= 2; break;
            }

            sentenceCount++;
        }
        System.out.println("total score"+totalScore);
        System.out.println(sentenceCount);
        double averageScore = (double) totalScore / sentenceCount;
        return (averageScore >= 1) ? "Positive" : (averageScore <= -1) ? "Negative" : "Neutral";
    }

}
