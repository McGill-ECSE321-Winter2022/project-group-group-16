package ca.mcgill.ecse321.GroceryApplicationBackend.dto;

public class ManagerDto {

    private int id;

    public ManagerDto(){

    }

    public ManagerDto(int id){

        //super() //for GroceryUserDto
        this.id = id;
    }

    public int getManagerId(){
        return id;
    }

    public void setManagerId(int id){
        this.id = id;
    }

    //get user from GroceryUserDto
    
}
