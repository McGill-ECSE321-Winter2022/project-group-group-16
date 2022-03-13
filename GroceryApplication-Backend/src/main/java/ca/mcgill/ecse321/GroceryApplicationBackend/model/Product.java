package ca.mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
public class Product {
    // attributes
    private String name;
    private String description;
    private Float price;
    private String image;
    private Float weight;
    private Integer barcode;
    private boolean isRefundable;
    private Float volume;
    private Integer availableQuantity;
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

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float value) {
        this.price = value;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String value) {
        this.image = value;
    }

    public Float getWeight() {
        return this.weight;
    }

    public void setWeight(Float value) {
        this.weight = value;
    }

    public Float getVolume() {
        return this.volume;
    }

    public void setVolume(Float value) {
        this.volume = value;
    }

    public Availability getAvailability() {
        return this.availability;
    }

    public void setAvailability(Availability value) {
        this.availability = value;
    }

    @GenericGenerator(name = "UseExistingIdOtherwiseGenerateUsingIdentity", strategy = "ca.mcgill.ecse321.GroceryApplicationBackend.model.UseExistingIdOtherwiseGenerateUsingIdentity")
    @GeneratedValue(generator = "UseExistingIdOtherwiseGenerateUsingIdentity")
    @Id
    public Integer getBarcode() {
        return this.barcode;
    }

    public void setBarcode(Integer value) {
        this.barcode = value;
    }

    public boolean getIsRefundable() {
        return this.isRefundable;
    }

    public void setIsRefundable(boolean value) {
        this.isRefundable = value;
    }

    public Integer getAvailableQuantity() {
        return this.availableQuantity;
    }

    public void setAvailableQuantity(Integer value) {
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
