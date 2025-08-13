package org.godigit.NewsAnalyzer.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String summary;
    private String bias;

    public NewsEntity(String title, String summary, String bias) {
        this.title = title;
        this.summary = summary;
        this.bias = bias;
    }

}
