package bachelor.project.Retailers;


import bachelor.project.Products.Product;
import bachelor.project.Transactions.Transaction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "/Management/Retailer")
public class RetailerManagementController {

    private static final List<Retailer> retailers = Arrays.asList(
            new Retailer("soudi", "123",3, LocalDate.of(2021,1,1),"waterway","Cairo"),
            new Retailer("carrefour", "123",3, LocalDate.of(2021,2,2),"downtown","Cairo"),
            new Retailer("hypermarket","123",3,LocalDate.of(2021,3,3), "madinaty","Cairo")
    );
    private static final List<Transaction> transactions = Arrays.asList(
        new Transaction("soudi","3", LocalDate.of(2021,2,20), new Product("Milk"))
    );
    private PasswordEncoder passwordEncoder;



    @PostMapping("/retailers")
    @PreAuthorize("hasAuthority('retailer:write')")
    public String addRetailer(@RequestBody Retailer retailer){
//        retailers.add(new Retailer(retailer.getUsername(),retailer.getPassword(),retailer.getPasswordTrials(),retailer.getPassExpired(),retailer.getEmail()));
//        TODO: check add function
        return "Retailer is added";

    }
    @GetMapping("/retailers")
    @PreAuthorize("hasAuthority('retailer:read')")
    public List<Retailer> addRetailer(){
//        retailers.add(new Retailer(retailer.getUsername(),retailer.getPassword(),retailer.getPasswordTrials(),retailer.getPassExpired(),retailer.getEmail()));
//        return "Retailer is added";

            return retailers;
        }



    @PostMapping("/login")
    public String login(@RequestBody Retailer retailer){

        return "Retailer is added";

    }

    public static String alphaNumericString(int len) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
    @PostMapping("/RetailerPassword")
    @PreAuthorize("hasAuthority('password:write')")
    public String setPassword(Retailer retailer){

//        String randomPass = passwordEncoder.encode(alphaNumericString(10));
//        retailer.setPassword(randomPass);
        return "Password edited";
    }
    @GetMapping("/RetailerPassword")
    @PreAuthorize("hasAuthority('password:read')")
    public String getPassword(){

      return retailers.get(1).getPassword();
//        return "Retailer Password";
    }


}
