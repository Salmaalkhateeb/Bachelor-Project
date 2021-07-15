package bachelor.project.Transactions;


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
@RequestMapping(path = "/Management/Transactions")
public class TransactionManagementController {

    private static final List<Transaction> transactions = Arrays.asList(
            new Transaction("soudi",3, LocalDate.of(2021,2,20), new Product("Milk"))
    );
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @PreAuthorize("hasAuthority('transaction:read')")
    public List<Transaction> getTransactions(){
        return transactions;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('transaction:write')")
    public String addTransaction(@RequestBody Transaction transaction){
//        retailers.add(new Retailer(retailer.getUsername(),retailer.getPassword(),retailer.getPasswordTrials(),retailer.getPassExpired(),retailer.getEmail()));
//        TODO: check add function
        return "Transaction is added";

    }

}
