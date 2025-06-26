package com.github.inncontrol.profile.domain.model.aggregates;


import com.github.inncontrol.profile.domain.model.commands.CreateProfileCommand;
import com.github.inncontrol.profile.domain.model.valueobjects.EmailAddress;
import com.github.inncontrol.profile.domain.model.valueobjects.PersonName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "profiles")
public class Profile  {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonName name;

    private String phoneNumber;

    @Embedded
    private EmailAddress email;

    private Long userId;

    public Profile(String firstName, String lastName, String email,Long userId) {
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.userId=userId;
    }

    public Profile(CreateProfileCommand command) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.phoneNumber = command.phoneNumber();
        this.email = new EmailAddress(command.email());
        this.userId=command.userId();
    }

    public Profile() {

    }

    public void updateName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }

    public void updateEmail(String email) {
        this.email = new EmailAddress(email);
    }



    public String getFullName() { return name.getFullName(); }

    public String getEmailAddress() { return email.address(); }

    public Long getId() {
        return id;
    }

    public PersonName getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getUserId() {
        return userId;
    }




}
