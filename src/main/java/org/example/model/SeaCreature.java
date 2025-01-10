package org.example.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "seacreature")
@Getter
@Setter
public class SeaCreature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column
    private String Name;

    public SeaCreature() {}

    @ManyToMany(mappedBy = "seaCreatures", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Chatroom> chatrooms = new HashSet<>();
}
