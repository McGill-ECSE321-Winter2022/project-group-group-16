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

    // enums
    public enum Availability {
        PICKUP, IN_STORE, DELIVERY, UNRESTRICTED
    }

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


    public void setPrice(float value) {
        this.price = value;
    }

    public float getPrice() {
        return this.price;
    }

    public void setImage(String value) {
        this.image = value;
    }

    public String getImage() {
        return this.image;
    }


    public void setWeight(float value) {
        this.weight = value;
    }

    public float getWeight() {
        return this.weight;
    }


    public void setVolume(float value) {
        this.volume = value;
    }

    public float getVolume() {
        return this.volume;
    }

    public void setAvailability(Availability value) {
        this.availability = value;
    }

    public Availability getAvailability() {
        return this.availability;
    }


    public void setBarcode(int value) {
        this.barcode = value;
    }

    @Id
    public int getBarcode() {
        return this.barcode;
    }


    public void setIsRefundable(boolean value) {
        this.isRefundable = value;
    }

    public boolean isIsRefundable() {
        return this.isRefundable;
    }


    public void setAvailableQuantity(int value) {
        this.availableQuantity = value;
    }

    public int getAvailableQuantity() {
        return this.availableQuantity;
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

}
