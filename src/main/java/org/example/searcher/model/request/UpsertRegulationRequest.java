package org.example.searcher.model.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpsertRegulationRequest {
    private String title;
    private String description;
    private List<Integer> keywordIds;
    private Integer categoryId;
}
