package com.app.quantitymeasurement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_google_id", columnList = "google_id", unique = true),
        @Index(name = "idx_email", columnList = "email", unique = true)
})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false)
        private String email;

        private String password;

        private String name;
        
        @Column(name = "google_id", unique = true)
        private String googleId;
        
        @Enumerated(EnumType.STRING)
        private Role role = Role.USER;

        @Enumerated(EnumType.STRING)
        private AuthProvider authProvider = AuthProvider.LOCAL;

        private boolean emailVerified;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

        private LocalDateTime lastLoginAt;  
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<QuantityMeasurementEntity> measurements = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum Role {
        USER,
        ADMIN
    }

    public enum AuthProvider {
        LOCAL,
        GOOGLE,
        HYBRID
    }
}