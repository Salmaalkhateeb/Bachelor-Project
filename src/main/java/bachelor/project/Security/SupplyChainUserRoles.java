package bachelor.project.Security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static bachelor.project.Security.SupplyChainPermissions.*;


public enum SupplyChainUserRoles {


   RETAILERS(Sets.newHashSet(TRANSACTION_WRITE,LOGIN_WRITE)),
   ADMINS(Sets.newHashSet(LOGIN_READ,LOGIN_WRITE,RETAILER_READ,RETAILER_WRITE,TRANSACTION_READ,PASSWORD_READ,PASSWORD_WRITE));



    private final Set<SupplyChainPermissions> permissions;


    SupplyChainUserRoles(Set<SupplyChainPermissions> permissions) {

        this.permissions = permissions;

    }

    public Set<SupplyChainPermissions> getPermissions() {

        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions= getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("Role"+ this.name()));
        return permissions;

    }
}
