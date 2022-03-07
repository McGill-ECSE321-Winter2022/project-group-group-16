package ca.mcgill.ecse321.GroceryApplicationBackend.dto;

public class ManagerDto {

    // attributes
    private int id;
    // private String dateOfBirth;
    // private String username;
    // private String password;
    // private String firstName;
    // private String lastName;
    // private String email;

    //String dateOfBirth, String username, String password, String firstName, String lastName, String email

    public ManagerDto(int id){

        //super() //for GroceryUserDto
        this.id = id;
    }

    public int getManagerId(){
        return id;
    }

    //get user from GroceryUserDto
    
}
