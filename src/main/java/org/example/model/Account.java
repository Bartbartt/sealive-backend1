package org.example.model;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
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

}
