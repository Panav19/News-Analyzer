package org.godigit.NewsAnalyzer.Dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AnalyzedNews {

    private String title;
    private String summary;
    private String bias;

}
