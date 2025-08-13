package org.godigit.NewsAnalyzer.Dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NewsResponse {

    private List<Article> articles;

}
