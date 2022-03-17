package ca.mcgill.ecse321.GroceryApplication.service;


import ca.mcgill.ecse321.GroceryApplicationBackend.dao.GroceryStoreApplicationRepository;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.GroceryStoreApplication;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.GroceryStoreApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;


@ExtendWith(MockitoExtension.class)
public class GroceryStoreApplicationServiceTest {

    private static final Integer GROCERYAPPID = 0;
    @Mock
    private GroceryStoreApplicationRepository groceryStoreApplicationRepository;
    @InjectMocks
    private GroceryStoreApplicationService gsAppService;

    @BeforeEach
    public void setMockOutput() {
        lenient().when(groceryStoreApplicationRepository.findGroceryStoreApplicationById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(GROCERYAPPID)) {

                GroceryStoreApplication app = new GroceryStoreApplication();
                app.setId(GROCERYAPPID);

                return app;
            } else {

                return null;
            }
        });

    }

    //Test for creating GroceryStoreApplication
    @Test
    public void testCreateGroceryStoreAppExists() {

        GroceryStoreApplication app = null;
        String error = null;

        try {
            app = gsAppService.createGroceryStoreApplication();

        } catch (ApiRequestException e) {

            error = e.getMessage();
        }
        assertNull(app);
        assertEquals("Grocery Store Application already exists in the system", error);
    }


    //Test for deleting a GroceryStoreApplication
    @Test
    public void testDeleteGroceryStoreApplication() {

        try {
            gsAppService.deleteGroceryStoreApplication();

        } catch (ApiRequestException e) {
            fail();
        }
    }

    //Test for getting a grocery store Application
    @Test
    public void testGetGroceryStoreApp() {
        GroceryStoreApplication app = null;
        try {

            app = gsAppService.getGroceryStoreApplication();

        } catch (ApiRequestException e) {

            fail();
        }
        assertNotNull(app);
    }
}