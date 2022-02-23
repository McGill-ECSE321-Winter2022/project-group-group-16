package ca.mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {
    // attributes
    private String name;
    private String description;
    private float price;
    private String image;
    private float weight;
    private int barcode;
    private boolean isRefundable;
    private float volume;
    private int availableQuantity;
    @Enumerated
    private Availability availability;

    // associations
    private Category category;
    private Set<GroceryOrder> order;
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

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float value) {
        this.price = value;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String value) {
        this.image = value;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float value) {
        this.weight = value;
    }

    public float getVolume() {
        return this.volume;
    }

    public void setVolume(float value) {
        this.volume = value;
    }

    public Availability getAvailability() {
        return this.availability;
    }

    public void setAvailability(Availability value) {
        this.availability = value;
    }

    @Id
    public int getBarcode() {
        return this.barcode;
    }

    public void setBarcode(int value) {
        this.barcode = value;
    }

    public boolean isIsRefundable() {
        return this.isRefundable;
    }

    public void setIsRefundable(boolean value) {
        this.isRefundable = value;
    }

    public int getAvailableQuantity() {
        return this.availableQuantity;
    }

    public void setAvailableQuantity(int value) {
        this.availableQuantity = value;
    }

    @ManyToOne(optional = false)
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToMany(mappedBy = "product")
    public Set<GroceryOrder> getOrder() {
        return this.order;
    }

    public void setOrder(Set<GroceryOrder> orders) {
        this.order = orders;
    }

    // enums
    public enum Availability {
        PICKUP, IN_STORE, DELIVERY, UNRESTRICTED
    }

}
