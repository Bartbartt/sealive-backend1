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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "chatroom_seacreature",
            joinColumns = @JoinColumn(name = "chatroom_id"),
            inverseJoinColumns = @JoinColumn(name = "seacreature_id")

    )
    private Set<SeaCreature> seaCreatures = new HashSet<>();

    public Chatroom(){
    }

    public void addSeaCreature(SeaCreature seaCreature) {
        seaCreatures.add(seaCreature);
        seaCreature.getChatrooms().add(this);
    }
} 
