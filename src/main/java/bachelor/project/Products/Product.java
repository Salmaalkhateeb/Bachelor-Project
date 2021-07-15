package bachelor.project.Products;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;


@Document
public class Product {
    @Id
    private String barcode;
    @Field
    private String productname;


    public Product(
            String productname){

        this.barcode = UUID.randomUUID().toString();
        this.productname = productname;

    }


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getProductname() {
        return productname;
    }

    @Override
    public String toString() {
        return "Products{" +
                "barcode='" + barcode + '\'' +
                ", productname='" + productname + '\'' +
                '}';
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
