package by.itechart.library.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;


@Data

public class User implements Serializable {
    private static final long serialVersionUID = 4752563413543184299L;

    private long id;
    private String firstName;
    private String lastName;
    private boolean gender;
    private String email;
    private LocalDate dateOfRegistration;
    private String phoneNumber;
    private String username;
    private String password;
    private Role role;
    private boolean deletedStatus;
}
