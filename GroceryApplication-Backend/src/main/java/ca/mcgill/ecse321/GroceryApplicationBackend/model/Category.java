package ca.mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Category {
    // attributes
    private String description;
    private String name;
    private String image;
    private int id;

    // associations
    private Set<Product> product;
    private GroceryStoreApplication groceryStoreApplication;

    @ManyToOne(optional = false)
    public GroceryStoreApplication getGroceryStoreApplication() {
        return this.groceryStoreApplication;
    }

    public void setGroceryStoreApplication(GroceryStoreApplication groceryStoreApplication) {
        this.groceryStoreApplication = groceryStoreApplication;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String value) {
        this.image = value;
    }

    @Id
    public int getId() {
        return this.id;
    }

    public void setId(int value) {
        this.id = value;
    }

    @OneToMany(mappedBy = "category")
    public Set<Product> getProduct() {
        return this.product;
    }

    public void setProduct(Set<Product> products) {
        this.product = products;
    }

}
