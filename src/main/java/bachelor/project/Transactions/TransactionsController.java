package bachelor.project.Transactions;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionsController {

//    private final TransactionsRepository transactionsRepository;
    private final TransactionsService transactionsService;

    public TransactionsController( TransactionsService transactionsService) {
//        this.transactionsRepository = transactionsRepository;
        this.transactionsService = transactionsService;
    }


    @GetMapping
    public String Hello(){
        return transactionsService.Hello();
    }


//    @GetMapping("/retailers")
//    public List<Transaction> gettransactions(){
//        return transactionsRepository.findAll();
//    }
}
