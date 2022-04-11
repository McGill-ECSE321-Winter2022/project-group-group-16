package ca.mcgill.ecse321.GroceryApplicationBackend.dto;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.*;

import java.util.Set;

public class GroceryStoreApplicationDto {
    private Integer id;
    private Set<GroceryOrder> order;
    private Set<Product> product;
    private Store store;
    private Set<Category> category;
    private Set<UserRole> userRole;

    public GroceryStoreApplicationDto(int id, Set<GroceryOrder> order, Set<Product> product, Store store, Set<Category> category, Set<UserRole> userRole) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.store = store;
        this.category = category;
        this.userRole = userRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<GroceryOrder> getOrder() {
        return order;
    }

    public void setOrder(Set<GroceryOrder> order) {
        this.order = order;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Set<Category> getCategory() {
        return category;
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }
}
