package istad.co.votingsystem.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "polls")
@Getter
@Setter
@NoArgsConstructor
public class Poll {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "multiple_choice", nullable = false)
    private Boolean multipleChoice;

    @Column(name = "is_private", nullable = false)
    private Boolean isPrivate;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Choice> choices;

}