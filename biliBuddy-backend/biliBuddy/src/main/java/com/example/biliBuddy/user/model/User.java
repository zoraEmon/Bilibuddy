package com.example.biliBuddy.user.model;

import com.example.biliBuddy.user.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collation = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    @Id
    private String id;

    private String username;
    private String password; //Nullable if OAuth.
    private String email;
    private String profilePicture;

    //Set the role of all users as "USER" by default.
    private Set<Role> roles = Set.of(Role.USER);

}
