package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

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

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }


    public void setImage(String value) {
        this.image = value;
    }

    public String getImage() {
        return this.image;
    }

    public void setId(int value) {
        this.id = value;
    }

    @Id
    public int getId() {
        return this.id;
    }

    @OneToMany(mappedBy = "category")
    public Set<Product> getProduct() {
        return this.product;
    }

    public void setProduct(Set<Product> products) {
        this.product = products;
    }

}
