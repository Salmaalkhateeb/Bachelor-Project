package bachelor.project.PandG;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/PandG")
public class PandGController {

    private final PandGService pandgService;
//    private final PandGrepository pandgRepository;

    @Autowired
    public PandGController(PandGService pandgService) {
        this.pandgService = pandgService;
//        this.pandgRepository = pandgRepository;
    }

    @GetMapping
    public String Hello(){
        return pandgService.Hello();
    }


//    @GetMapping("/p&gusers")
//    public List<PGusers> getusers(){
//        return pandgRepository.findAll();
//    }
}
