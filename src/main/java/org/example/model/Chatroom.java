package org.example.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

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
    @OneToOne(cascade = CascadeType.ALL)

    @JoinColumn(name = "accountId", referencedColumnName = "id")
    private Account CreatorAccount;

    public Chatroom(String title, String description, LocalDateTime createdDateTime, Account creatorAccount){
        Title = title;
        Description = description;
        CreatedDateTime = createdDateTime;
        CreatorAccount = creatorAccount;
    }
}
