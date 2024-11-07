package org.example.model;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column
    private String Username;
    @Column
    private String Biography;
    @Column
    private LocalDate Birthday;

    public Account() {
    }
    public Account(String username, String biography, LocalDate birthday) {
        Username = username;
        Biography = biography;
        Birthday = birthday;
    }
    public Account(int id, String username, String biography, LocalDate birthday) {
        Id = id;
        Username = username;
        Biography = biography;
        Birthday = birthday;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
