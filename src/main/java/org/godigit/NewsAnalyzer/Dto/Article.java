package org.godigit.NewsAnalyzer.Dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Article {

    private String title;
    private String content;

}
