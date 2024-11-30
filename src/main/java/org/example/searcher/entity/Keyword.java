package org.example.searcher.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @ManyToOne
    @JoinColumn(name = "regulation_id")
    private Regulation regulation;
}
