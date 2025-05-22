package com.example.biliBuddy.auth.service;

import com.example.biliBuddy.user.model.User;
import com.example.biliBuddy.user.model.enums.Role;
import com.example.biliBuddy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) {
        OAuth2User oAuth2User = super.loadUser(request);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.get("email");
        String username = (String) attributes.get("username");
        String picture = (String) attributes.get("picture");

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setUsername(username);
                    newUser.setProfilePicture(picture);
                    newUser.setRoles(Collections.singleton(Role.USER));
                    return userRepository.save(newUser);
                });

        List<SimpleGrantedAuthority> authorities = user.getRoles().
                stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();

        return new DefaultOAuth2User(authorities, attributes, "email");
    }
}
