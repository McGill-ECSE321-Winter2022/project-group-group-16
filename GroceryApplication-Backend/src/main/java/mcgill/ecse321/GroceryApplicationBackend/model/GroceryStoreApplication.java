package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class GroceryStoreApplication {
    // attributes
    private int id;

    // associations
    private Set<GroceryOrder> order;
    private Set<Product> product;
    private Store store;
    private Set<Category> category;
    private Set<UserRole> userRole;


    @OneToMany(mappedBy = "groceryStoreApplication", cascade = {CascadeType.ALL})
    public Set<GroceryOrder> getOrder() {
        return this.order;
    }

    public void setOrder(Set<GroceryOrder> orders) {
        this.order = orders;
    }

    @OneToMany(mappedBy = "groceryStoreApplication", cascade = {CascadeType.ALL})
    public Set<Product> getProduct() {
        return this.product;
    }

    public void setProduct(Set<Product> products) {
        this.product = products;
    }

    @OneToMany(mappedBy = "groceryStoreApplication", cascade = {CascadeType.ALL})
    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<UserRole> userRoles) {
        this.userRole = userRoles;
    }

    @OneToOne(mappedBy = "groceryStoreApplication", cascade = {CascadeType.ALL})
    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @OneToMany(mappedBy = "groceryStoreApplication", cascade = {CascadeType.ALL})
    public Set<Category> getCategory() {
        return this.category;
    }

    public void setCategory(Set<Category> categorys) {
        this.category = categorys;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    public int getId() {
        return id;
    }
}
