package com.example.biliBuddy.user.model;

import com.example.biliBuddy.user.model.enums.Role;
import com.example.biliBuddy.user.model.enums.VerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;

@Document(collation = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    @Id
    private String userId;

    private String username;
    private String password; //Nullable if OAuth.
    private String email;
    private String profilePicture;

    //Set the role of all users to "USER" by default.
    @Builder.Default
    private Set<Role> roles = Set.of(Role.USER);

    //Set the verification status to "PENDING" by default.
    @Builder.Default
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

    //For soft deletion.
    private boolean deleted = false;

    @CreatedDate
    private Instant createdAt; //time creation.

    @LastModifiedDate
    private Instant updatedAt; //last modified date.

}
