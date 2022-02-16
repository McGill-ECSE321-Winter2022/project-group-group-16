package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Product {
    private GroceryStoreApplication groceryStoreApplication;

    public enum Availability {
    }

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

    private float price;

    public void setPrice(float value) {
        this.price = value;
    }

    public float getPrice() {
        return this.price;
    }

    private String image;

    public void setImage(String value) {
        this.image = value;
    }

    public String getImage() {
        return this.image;
    }

    private float weight;

    public void setWeight(float value) {
        this.weight = value;
    }

    public float getWeight() {
        return this.weight;
    }

    private float volume;

    public void setVolume(float value) {
        this.volume = value;
    }

    public float getVolume() {
        return this.volume;
    }

    private Availability availability;

    private void setAvailability(Availability value) {
        this.availability = value;
    }

    private Availability getAvailability() {
        return this.availability;
    }

    private int barcode;

    public void setBarcode(int value) {
        this.barcode = value;
    }

    @Id
    public int getBarcode() {
        return this.barcode;
    }

    private boolean isRefundable;

    public void setIsRefundable(boolean value) {
        this.isRefundable = value;
    }

    public boolean isIsRefundable() {
        return this.isRefundable;
    }

    private int availableQuantity;

    public void setAvailableQuantity(int value) {
        this.availableQuantity = value;
    }

    public int getAvailableQuantity() {
        return this.availableQuantity;
    }

    private Category category;

    @ManyToOne(optional = false)
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private Set<Order> order;

    @ManyToMany(mappedBy = "product")
    public Set<Order> getOrder() {
        return this.order;
    }

    public void setOrder(Set<Order> orders) {
        this.order = orders;
    }

}
