package bachelor.project;

import bachelor.project.PandG.PGusers;
import bachelor.project.PandG.PandGrepository;
import bachelor.project.Products.Product;
import bachelor.project.Products.ProductsRepository;
import bachelor.project.Retailers.Retailer;
import bachelor.project.Retailers.RetailersRepository;
import bachelor.project.Transactions.Transaction;
import bachelor.project.Transactions.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {


	private final PandGrepository pandGrepository;
	private final ProductsRepository productsRepository;
	private final RetailersRepository retailersRepository;
	private final TransactionsRepository transactionsRepository;

    @Autowired
	public ProjectApplication(PandGrepository pandGrepository, ProductsRepository productsRepository, RetailersRepository retailersRepository, TransactionsRepository transactionsRepository) {
		this.pandGrepository = pandGrepository;
		this.productsRepository = productsRepository;
		this.retailersRepository = retailersRepository;
		this.transactionsRepository = transactionsRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if(pandGrepository.findAll().isEmpty()){
			pandGrepository.save(new PGusers("Admin1","password"));
			pandGrepository.save(new PGusers("Admin2","password"));
		}
		if(productsRepository.findAll().isEmpty()){
			productsRepository.save(new Product("Hair Shampoo"));
			productsRepository.save(new Product("Tooth Paste"));
			productsRepository.save(new Product("Shaving Cream"));
		}
		if(retailersRepository.findAll().isEmpty()){
			retailersRepository.save(new Retailer("Soudi","password",3, LocalDate.of(2021,5,5),"Tagmou3","Cairo"));
			retailersRepository.save(new Retailer("Carrefour","password",3, LocalDate.of(2021,4,4),"Madinaty","Cairo"));
			retailersRepository.save(new Retailer("Hypermarket","password",3, LocalDate.of(2021,3,3),"Shrouk City","Cairo"));
		}
		if(transactionsRepository.findAll().isEmpty()){
			transactionsRepository.save(new Transaction("1",3,LocalDate.of(2021,3,3),new Product("Hair Shampoo")));
			transactionsRepository.save(new Transaction("2",4,LocalDate.of(2021,4,4),new Product("Shaving Cream")));
			transactionsRepository.save(new Transaction("3",10,LocalDate.of(2021,3,3),new Product("Tooth Paste")));
		}

		}
}
