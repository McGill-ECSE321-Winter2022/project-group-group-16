package ca.mcgill.ecse321.GroceryApplication.service;

//Project imports
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.*;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.ManagerService;
import ca.mcgill.ecse321.GroceryApplicationBackend.dao.*;

//Mockito imports
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;


@ExtendWith(MockitoExtension.class)
public class ManagerServiceTest {

    @Mock
    private ManagerRepository managerRepository;

    @Mock
    private GroceryStoreApplicationRepository gsRepository;

    @Mock
    private GroceryUserRepository userRepository;

    @InjectMocks
    private ManagerService managerService;

    //Initial parameters
    private static final Integer APP_ID = 11;
    private static final String USER_EMAIL = "name@email.ca";
    private static final Integer MANAGER_ID = 3;

    //Updated parameters
    private static final Integer APP_ID_UPDATED = 12;
    private static final String USER_EMAIL_UPDATED = "new_name@email.ca";

    //Does not exist parameters
    private static final Integer APP_ID_DOES_NOT_EXIST = 99;
    private static final String USER_EMAIL_DOES_NOT_EXIST = "wrongemail@email.ca";
    private static final Integer MANAGER_ID_DOSE_NOT_EXIST = 9;
    private static final String USER_EMAIL_NOT_MANAGER = "customer@mail.ca";


    @BeforeEach
    public void setMockOutput() {

        lenient().when(managerRepository.findManagerById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {

            if (invocation.getArgument(0).equals(MANAGER_ID)) {
                Manager manager = new Manager();
                manager.setId(MANAGER_ID);
                return manager;
            } else {
                return null;
            }

        });

        lenient().when(managerRepository.findManagerByUser(any())).thenAnswer((InvocationOnMock invocation) -> {

            GroceryUser argUser = invocation.getArgument(0);

            if (argUser.getEmail().equals(USER_EMAIL)) {
                Manager manager = new Manager();
                GroceryUser user = new GroceryUser();
                user.setEmail(USER_EMAIL);
                manager.setUser(user);
                return manager;
            } else {
                return null;
            }

        });

        lenient().when(gsRepository.findGroceryStoreApplicationById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {

            if (invocation.getArgument(0).equals(APP_ID)) {
                GroceryStoreApplication gsApplication = new GroceryStoreApplication();
                gsApplication.setId(APP_ID);
                return gsApplication;
            } else if (invocation.getArgument(0).equals(APP_ID_UPDATED)) {
                GroceryStoreApplication gsApplication = new GroceryStoreApplication();
                gsApplication.setId(APP_ID_UPDATED);
                return gsApplication;
            } else {
                return null;
            }

        });

        lenient().when(userRepository.findGroceryUserByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {

            if (invocation.getArgument(0).equals(USER_EMAIL)) {
                GroceryUser groceryUser = new GroceryUser();
                groceryUser.setEmail(USER_EMAIL);
                return groceryUser;
            } else if (invocation.getArgument(0).equals(USER_EMAIL_UPDATED)) {
                GroceryUser groceryUser = new GroceryUser();
                groceryUser.setEmail(USER_EMAIL_UPDATED);
                return groceryUser;
            } else if (invocation.getArgument(0).equals(USER_EMAIL_NOT_MANAGER)) {
                GroceryUser groceryUser = new GroceryUser();
                groceryUser.setEmail(USER_EMAIL_NOT_MANAGER);
                return groceryUser;
            } else {
                return null;
            }

        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
        lenient().when(managerRepository.save(any(Manager.class))).thenAnswer(returnParameterAsAnswer);

    }


    //===============
    //createManager()
    //===============

    @Test
    public void testCreateManager() {
        Manager manager = null;

        try {
            manager = managerService.createManager(APP_ID, USER_EMAIL);
        } catch (ApiRequestException e) {
            fail();
        }

        assertNotNull(manager);
        assertEquals(APP_ID, manager.getGroceryStoreApplication().getId());
        assertEquals(USER_EMAIL, manager.getUser().getEmail());
    }

    @Test
    public void testCreateManagerNullEmail() {
        Manager manager = null;
        String error = null;

        try {
            manager = managerService.createManager(APP_ID, null);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(manager);
        assertEquals("The email cannot be null!", error);
    }

    @Test
    public void testCreateManagerApplicationDoesNotExist() {
        Manager manager = null;
        String error = null;

        try {
            manager = managerService.createManager(APP_ID_DOES_NOT_EXIST, USER_EMAIL);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(manager);
        assertEquals("The application doesn't exist!", error);
    }

    @Test
    public void testCreateManagerUserDoesNotExist() {
        Manager manager = null;
        String error = null;

        try {
            manager = managerService.createManager(APP_ID, USER_EMAIL_DOES_NOT_EXIST);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(manager);
        assertEquals("The user doesn't exist!", error);
    }


    //===============
    //updateManager()
    //===============

    @Test
    public void testUpdateManager() {
        Manager manager = null;

        try {
            manager = managerService.updateManager(MANAGER_ID, USER_EMAIL_UPDATED, APP_ID_UPDATED);
        } catch (ApiRequestException e) {
            fail();
        }

        assertNotNull(manager);
        assertEquals(MANAGER_ID, manager.getId());
        assertEquals(APP_ID_UPDATED, manager.getGroceryStoreApplication().getId());
        assertEquals(USER_EMAIL_UPDATED, manager.getUser().getEmail());
    }

    @Test
    public void testUpdateManagerNotFound() {
        Manager manager = null;
        String error = null;

        try {
            manager = managerService.updateManager(MANAGER_ID_DOSE_NOT_EXIST, USER_EMAIL_UPDATED, APP_ID_UPDATED);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(manager);
        assertEquals("Manager not found!", error);
    }

    @Test
    public void testUpdateManagerUserNotFound() {
        Manager manager = null;
        String error = null;

        try {
            manager = managerService.updateManager(MANAGER_ID, USER_EMAIL_DOES_NOT_EXIST, APP_ID_UPDATED);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(manager);
        assertEquals("This user does not exist!", error);
    }

    @Test
    public void testUpdateManagerAppNotFound() {
        Manager manager = null;
        String error = null;

        try {
            manager = managerService.updateManager(MANAGER_ID, USER_EMAIL_UPDATED, APP_ID_DOES_NOT_EXIST);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(manager);
        assertEquals("Application does not exist!", error);
    }


    //================
    //getManagerById()
    //================

    @Test
    public void testFindManagerById() {
        Manager manager = null;

        try {
            manager = managerService.getManagerById(MANAGER_ID);
        } catch (ApiRequestException e) {
            fail();
        }

        assertNotNull(manager);
        assertEquals(MANAGER_ID, manager.getId());
    }

    @Test
    public void testFindMangerByIdNotFound() {
        Manager manager = null;
        String error = null;

        try {
            manager = managerService.getManagerById(MANAGER_ID_DOSE_NOT_EXIST);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(manager);
        assertEquals("Manager does not exist!", error);
    }


    //===================
    //getManagerByEmail()
    //===================

    @Test
    public void testFindManagerByEmail() {
        Manager manager = null;

        try {
            manager = managerService.getManagerByEmail(USER_EMAIL);
        } catch (ApiRequestException e) {
            fail();
        }

        assertNotNull(manager);
        assertEquals(USER_EMAIL, manager.getUser().getEmail());
    }

    @Test
    public void testFindMangerByEmailUserNotFound() {
        Manager manager = null;
        String error = null;

        try {
            manager = managerService.getManagerByEmail(USER_EMAIL_DOES_NOT_EXIST);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(manager);
        assertEquals("User does not exist!", error);
    }

    @Test
    public void testFindMangerByEmailUserNotManger() {
        Manager manager = null;
        String error = null;

        try {
            manager = managerService.getManagerByEmail(USER_EMAIL_NOT_MANAGER);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertNull(manager);
        assertEquals("User is not a manager!", error);
    }

    //===================
    //DeleteManagerById()
    //===================

    @Test
    public void testDeleteManagerById() {
        try {
            managerService.deleteManagerById(MANAGER_ID);
        } catch (ApiRequestException e) {
            fail();
        }
    }

    @Test
    public void testDeleteMangerByIdNotFound() {
        String error = null;

        try {
            managerService.deleteManagerById(MANAGER_ID_DOSE_NOT_EXIST);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertEquals("Manager does not exist!", error);
    }

    //====================
    //deleteManagerByEmail()
    //====================

    @Test
    public void testDeleteManagerByEmail() {
        try {
            managerService.deleteManagerByEmail(USER_EMAIL);
        } catch (ApiRequestException e) {
            fail();
        }
    }

    @Test
    public void testDeleteMangerByEmailUserNotFound() {
        String error = null;

        try {
            managerService.deleteManagerByEmail(USER_EMAIL_DOES_NOT_EXIST);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertEquals("User does not exist!", error);
    }

    @Test
    public void testDeleteMangerByEmailUserNotManger() {
        String error = null;

        try {
            managerService.deleteManagerByEmail(USER_EMAIL_NOT_MANAGER);
        } catch (ApiRequestException e) {
            error = e.getMessage();
        }

        assertEquals("User is not a manager!", error);
    }


}
