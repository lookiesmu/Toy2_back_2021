package team2.study_project.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor //(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder

public class User extends BasicClass implements UserDetails  {

    @Id @GeneratedValue
    @Column(name="user_id")
    private Long id;

    @Email
    private String email;

    @Column
    private String password;

    @Column
    private String username;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Column
    private Boolean studyStatus;

    @OneToMany(mappedBy = "user")
    private List<Timer> timer = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Study> studyList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Follow> followList = new ArrayList<>();

    @Builder
    public User(String email, String password, String username, boolean studyStatus){
        this.email= email;
        this.password=password;
        this.username=username;
        this.studyStatus=studyStatus;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.email = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                username.equals(user.username);
    }

    public void update(String username){
        this.username= username;
    }

    public void updateTimeStatue(boolean status){
        this.studyStatus = status;
    }

}
