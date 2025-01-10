package org.example.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class SeaCreature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column
    private String Name;

    public SeaCreature() {}

    @ManyToMany(mappedBy="chatroom")
    private Set<Chatroom> chatrooms = new HashSet<>();

}
