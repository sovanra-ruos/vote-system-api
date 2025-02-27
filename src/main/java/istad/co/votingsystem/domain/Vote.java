package istad.co.votingsystem.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private String ipAddress;

    @ManyToOne
    @JoinColumn(name = "poll_id", nullable = false)
    private Poll poll; // The poll being voted on

    @ManyToOne
    @JoinColumn(name = "choice_id", nullable = false)
    private Choice selectedChoice;
}