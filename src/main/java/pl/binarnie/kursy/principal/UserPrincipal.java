package pl.binarnie.kursy.principal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.binarnie.kursy.persistance.model.Privilege;
import pl.binarnie.kursy.persistance.model.Role;
import pl.binarnie.kursy.persistance.model.User;

import java.util.*;

@AllArgsConstructor
@Getter
@Setter
public class UserPrincipal implements UserDetails {

    private Long id;

    private String name;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(User user) {
        List<Role> authorities = new ArrayList<>(user.getRoles());

        return new UserPrincipal(user.getId(), user.getName(), user.getEmail(), user.getPassword(), getAllPrivileges(authorities));
    }

    private static List<? extends GrantedAuthority> getAllPrivileges(List<Role> roles) {
        HashSet<GrantedAuthority> privileges = new HashSet<>();

        for(Role role : roles) {
            privileges.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));

            for(Privilege privilege : role.getPrivileges()) {
                privileges.add(new SimpleGrantedAuthority(privilege.getName()));
            }
        }

        return new ArrayList<>(privileges);
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
        return email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
