package ca.mcgill.ecse321.GroceryApplicationBackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class GroceryStoreApplication {
    // attributes
    private Integer id;

    // associations
    private Set<GroceryOrder> order;
    private Set<Product> product;
    private Store store;
    private Set<Category> category;
    private Set<UserRole> userRole;


    @JsonManagedReference
    @OneToMany(mappedBy = "groceryStoreApplication", cascade = {CascadeType.ALL})
    public Set<GroceryOrder> getOrder() {
        return this.order;
    }

    public void setOrder(Set<GroceryOrder> orders) {
        this.order = orders;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "groceryStoreApplication", cascade = {CascadeType.ALL})
    public Set<Product> getProduct() {
        return this.product;
    }

    public void setProduct(Set<Product> products) {
        this.product = products;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "groceryStoreApplication", cascade = {CascadeType.ALL})
    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<UserRole> userRoles) {
        this.userRole = userRoles;
    }

    @JsonManagedReference
    @OneToOne(mappedBy = "groceryStoreApplication", cascade = {CascadeType.ALL})
    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "groceryStoreApplication", cascade = {CascadeType.ALL})
    public Set<Category> getCategory() {
        return this.category;
    }

    public void setCategory(Set<Category> categorys) {
        this.category = categorys;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
