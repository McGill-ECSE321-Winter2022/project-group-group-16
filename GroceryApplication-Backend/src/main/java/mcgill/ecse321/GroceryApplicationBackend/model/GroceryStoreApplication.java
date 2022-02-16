package mcgill.ecse321.GroceryApplicationBackend.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class GroceryStoreApplication {
    private Set<Order> order;
    private int id;

    @OneToMany(mappedBy = "groceryStoreApplication", cascade = {CascadeType.ALL})
    public Set<Order> getOrder() {
        return this.order;
    }

    public void setOrder(Set<Order> orders) {
        this.order = orders;
    }

    private Set<Product> product;

    @OneToMany(mappedBy = "groceryStoreApplication", cascade = {CascadeType.ALL})
    public Set<Product> getProduct() {
        return this.product;
    }

    public void setProduct(Set<Product> products) {
        this.product = products;
    }

    private Set<UserRole> userRole;

    @OneToMany(mappedBy = "groceryStoreApplication", cascade = {CascadeType.ALL})
    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<UserRole> userRoles) {
        this.userRole = userRoles;
    }

    private Store store;

    @OneToOne(mappedBy = "groceryStoreApplication", cascade = {CascadeType.ALL})
    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    private Set<Category> category;

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
