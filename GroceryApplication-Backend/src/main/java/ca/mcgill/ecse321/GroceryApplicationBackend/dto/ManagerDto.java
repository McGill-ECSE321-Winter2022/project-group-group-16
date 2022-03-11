package ca.mcgill.ecse321.GroceryApplicationBackend.dto;

public class ManagerDto {

    private Integer id;

    public ManagerDto(){

    }

    public ManagerDto(Integer id){

        //super() //for GroceryUserDto
        this.id = id;
    }

    public Integer getManagerId(){
        return id;
    }

    public void setManagerId(Integer id){
        this.id = id;
    }

    //get user from GroceryUserDto
    
}
