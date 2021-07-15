package bachelor.project.Retailers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/Retailer")
public class RetailerController {

    private final RetailerService retailerService;
//    private final RetailersRepository retailersRepository;

    @Autowired
    public RetailerController(RetailerService retailerService) {
        this.retailerService = retailerService;
//        this.retailersRepository = retailersRepository;
    }

    @GetMapping
    public String Hello(){
        return retailerService.Hello();
    }


//    @GetMapping("/retailers")
//    public List<Retailer> getretailers(){
//        return retailersRepository.findAll();
//    }
}
