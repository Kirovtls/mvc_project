package peaksoft.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter @Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String studyFormat;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH, PERSIST, REMOVE})
    private Course course;

    @ManyToOne
    private Company company;

    public Student(String firstName, String lastName, String email, String phoneNumber, String studyFormat) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.studyFormat = studyFormat;
    }
}
