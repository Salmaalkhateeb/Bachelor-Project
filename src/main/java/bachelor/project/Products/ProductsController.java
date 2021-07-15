package bachelor.project.Products;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductsController {

    private final ProductsService productsService;
//    private final ProductsRepository productsRepository;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
//        this.productsRepository = productsRepository;
    }


    @GetMapping
    public String Hello(){
        return productsService.Hello();
    }


//    @GetMapping("/products")
//    public List<Product> getpandg(){
//        return productsRepository.findAll();
//    }
}
