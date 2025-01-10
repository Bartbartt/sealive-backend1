package org.example.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class Chatroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String Title;
    @Column
    private String Description;
    @Column
    private LocalDateTime CreatedDateTime;
    @ManyToOne()
    @JoinColumn(name = "accountId", referencedColumnName = "id")
    private Account CreatorAccount;

    public Chatroom(){
    }

    @ManyToMany
    @JoinTable(
            name = "chatroom_seaCreature",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<SeaCreature> seaCreatures = new HashSet<>();
}
