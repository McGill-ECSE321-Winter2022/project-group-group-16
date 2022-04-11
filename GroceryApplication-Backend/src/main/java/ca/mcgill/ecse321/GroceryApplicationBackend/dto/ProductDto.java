package ca.mcgill.ecse321.GroceryApplicationBackend.dto;


import ca.mcgill.ecse321.GroceryApplicationBackend.model.Product.Availability;

public class ProductDto {
    // attributes
    private String name;
    private String description;
    private Float price;
    private String image;
    private Float weight;
    private Float volume;
    private Integer barcode;
    private boolean isRefundable;
    private Integer availableQuantity;
    private Availability availability;


    public ProductDto() {

    }

    public ProductDto(Integer barcode) {
        this.name = null;
        this.description = null;
        this.price = null;
        this.image = null;
        this.weight = null;
        this.volume = null;
        this.barcode = barcode;
        this.availability = null;
        this.isRefundable = false;
        this.availableQuantity = null;
    }

    public ProductDto(Float volume, String name, String description, Float price, String image, Float weight, Integer barcode, boolean isRefundable, Integer availableQuantity, Availability availability) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.weight = weight;
        this.volume = volume;
        this.barcode = barcode;
        this.availability = availability;
        this.isRefundable = isRefundable;
        this.availableQuantity = availableQuantity;
    }

    // ------------------ Getters and Setters ------------------------

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

    public Integer getBarcode() {
        return this.barcode;
    }

    public void setBarcode(Integer value) {
        this.barcode = value;
    }

    public boolean isRefundable() {
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


}
