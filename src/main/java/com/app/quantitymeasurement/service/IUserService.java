package com.app.quantitymeasurement.service;


import java.util.Optional;

import com.app.quantitymeasurement.model.User;

public interface IUserService {

    User saveOrUpdateOAuthUser(String googleId, String email, String name, String givenName,
                               String familyName, String pictureUrl, String locale, boolean emailVerified);

    User registerLocalUser(String email, String password, String name);

    Optional<User> findByGoogleId(String googleId);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    User updateLastLogin(User user);

    void deleteUser(Long id);
}