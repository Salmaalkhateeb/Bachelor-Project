package bachelor.project.PandG;

import org.springframework.stereotype.Service;

@Service
public class PandGService<UserDetails, UsernamePasswordAuthenticationToken>  {
    public String Hello(){
        return "Hello P&G";
    }
}
