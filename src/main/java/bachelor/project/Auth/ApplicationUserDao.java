package bachelor.project.Auth;

import java.util.Optional;

public interface ApplicationUserDao {

     Optional<ApplicationUser> selectApplicationUserByUsername (String username);
}
