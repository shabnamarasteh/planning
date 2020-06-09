package ir.eshragh.planning.modules.admins.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "admins")
public class Admin implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "number")
    private long id;

    @Column(columnDefinition = "varchar2(100)", name = "email")
    private String username;

    @Column(columnDefinition = "varchar2(100)")
    private String firstname;

    @Column(columnDefinition = "varchar2(100)")
    private String lastname;

    @Column(columnDefinition = "varchar2(200)")
    private String password;

    private Boolean enabled = true;

    @Column(columnDefinition = "number")
    private long nationalcode;

    @CreationTimestamp
    @Column(name = "creation_at" , updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @JsonIgnore

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public Admin() {
    }

    public Admin(String username, String firstname, String lastname, String password, Boolean enabled, long nationalcode) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.enabled = enabled;
        this.nationalcode = nationalcode;
    }

    public Admin(String username, String firstname, String lastname, String password, Boolean enabled, long nationalcode, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.enabled = enabled;
        this.nationalcode = nationalcode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Admin(String username, String firstname, String lastname, String password, Boolean enabled, long nationalcode, LocalDateTime createdAt, LocalDateTime updatedAt, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.enabled = enabled;
        this.nationalcode = nationalcode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.authorities = authorities;
    }

    public <E> Admin(String javainuse, String $2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6, ArrayList<E> es) {
    }

    public Admin(long id, String firstname, String lastname, String username, String password, List<GrantedAuthority> authorities) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public long getNationalcode() {
        return nationalcode;
    }

    public void setNationalcode(long nationalcode) {
        this.nationalcode = nationalcode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static Admin create(Admin admin) {
        List<GrantedAuthority> authorities = new ArrayList<String>(Arrays.asList("admin")).stream().map(role ->
                new SimpleGrantedAuthority("admin")
        ).collect(Collectors.toList());
        return new Admin(
                admin.getId(),
                admin.getFirstname(),
                admin.getLastname(),
                admin.getUsername(),
                admin.getPassword(),
                authorities
        );
    }
}
