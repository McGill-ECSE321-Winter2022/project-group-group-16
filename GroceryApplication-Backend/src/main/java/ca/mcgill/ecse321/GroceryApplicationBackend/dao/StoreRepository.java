package ca.mcgill.ecse321.GroceryApplicationBackend.dao;

import ca.mcgill.ecse321.GroceryApplicationBackend.model.Store;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface StoreRepository extends CrudRepository<Store, String> {


    /**
     * Find store via their name
     *
     * @param name
     * @return store(Store)
     */
    Store findStoreByName(String name);

    @Override
    void delete(Store entity);

    @Transactional
    @Modifying
    @Query("DELETE FROM Store store WHERE store.name = :name")
    void deleteStoreByName(@Param("name") String name);

    // boolean existsByStoreName(String name);

}
