package twitterx.simplificado.entities;

import jakarta.persistence.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import twitterx.simplificado.controller.dto.LoginRequest;
import twitterx.simplificado.controller.dto.LoginResponse;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    @Column(unique = true)
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User() {

    }

    public boolean isLoginCorrect(
            LoginRequest loginRequest, PasswordEncoder passwordEncoder){
        return passwordEncoder.matches(loginRequest.password(), this.password);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(
            UUID userId) {
        this.userId =
                userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(
            String username) {
        this.username =
                username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(
            String password) {
        this.password =
                password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(
            Set<Role> roles) {
        this.roles =
                roles;
    }

    public User(
            UUID userId,
            String username,
            String password,
            Set<Role> roles) {
        this.userId =
                userId;
        this.username =
                username;
        this.password =
                password;
        this.roles =
                roles;
    }
}
