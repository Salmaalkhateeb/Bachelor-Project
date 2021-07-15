package bachelor.project.Transactions;


import bachelor.project.Products.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.UUID;


@Document
public class Transaction {
    @Id
    private String id;
    @Field
    private String retailerId;
    @Field
    private int quantity;
    @Field
    private LocalDate transactionDate;
    @Field
    private Product productname;

    @Override
    public String toString() {
        return "Transactions{" +
                "retailerId='" + retailerId + '\'' +
                ", quantity=" + quantity +
                ", transactionDate=" + transactionDate +
                ", productname='" + productname + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Product getProductname() {
        return productname;
    }

    public void setProductname(Product productname) {
        this.productname = productname;
    }

    public Transaction(
            String retailerId,
            int quantity,
            LocalDate transactionDate,
            Product productname) {

        this.id = UUID.randomUUID().toString();
        this.retailerId=retailerId;
        this.quantity=quantity;
        this.transactionDate=transactionDate;
        this.productname=productname;
    }

}

