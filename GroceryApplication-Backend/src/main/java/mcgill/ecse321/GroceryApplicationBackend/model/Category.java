package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Category {
    private GroceryStoreApplication groceryStoreApplication;

    @ManyToOne(optional = false)
    public GroceryStoreApplication getGroceryStoreApplication() {
        return this.groceryStoreApplication;
    }

    public void setGroceryStoreApplication(GroceryStoreApplication groceryStoreApplication) {
        this.groceryStoreApplication = groceryStoreApplication;
    }

    private String name;

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    private String description;

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }

    private String image;

    public void setImage(String value) {
        this.image = value;
    }

    public String getImage() {
        return this.image;
    }

    private int id;

    public void setId(int value) {
        this.id = value;
    }

    @Id
    public int getId() {
        return this.id;
    }

    private Set<Product> product;

    @OneToMany(mappedBy = "category")
    public Set<Product> getProduct() {
        return this.product;
    }

    public void setProduct(Set<Product> products) {
        this.product = products;
    }

}
