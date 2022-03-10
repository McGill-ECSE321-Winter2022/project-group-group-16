package ca.mcgill.ecse321.GroceryApplicationBackend.controller;

import ca.mcgill.ecse321.GroceryApplicationBackend.dto.ShiftDto;
import ca.mcgill.ecse321.GroceryApplicationBackend.exception.ApiRequestException;
import ca.mcgill.ecse321.GroceryApplicationBackend.model.Shift;
import ca.mcgill.ecse321.GroceryApplicationBackend.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ShiftRestController {
    @Autowired
    private ShiftService shiftService;

    @PostMapping(value = {"/shift", "/shift/"})
    public ShiftDto createShift(
            @RequestParam Shift.Day day,
            @RequestParam Shift.ShiftType shiftType,
            @RequestParam Integer employeeId
    ) throws ApiRequestException {
        Shift shift = shiftService.createShift(day, shiftType, employeeId);
        return convertToDto(shift);
    }

    @PutMapping(value = {"/shift/{id}", "/shift/{id}/"})
    public ShiftDto updateShift(
            @PathVariable("id") Integer id,
            @RequestParam Shift.Day day,
            @RequestParam Shift.ShiftType shiftType,
            @RequestParam Integer employeeId
    ) throws ApiRequestException {
        Shift shift = shiftService.updateShift(id, day, shiftType, employeeId);
        return convertToDto(shift);
    }

    @GetMapping(value = {"/shift/{id}", "/shift/{id}/"})
    public ShiftDto getShift(@PathVariable("id") Integer id) throws ApiRequestException {
        Shift shift = shiftService.getShift(id);
        return convertToDto(shift);
    }

    @GetMapping(value = {"/shift", "/shift/"})
    public List<ShiftDto> getAllShifts() throws ApiRequestException {
        List<Shift> shifts = shiftService.getAllShifts();
        return convertToDto(shifts);
    }

    @DeleteMapping(value = {"/shift/{id}", "/shift/{id}/"})
    public void deleteShift(@PathVariable("id") Integer id) throws ApiRequestException {
        shiftService.deleteShift(id);
    }


    /**
     * Converts a shift to a data transfer object.
     *
     * @param shift employee to convert.
     * @return corresponding data transfer object.
     */
    private static ShiftDto convertToDto(Shift shift) {
        if (shift == null) return null;
        return new ShiftDto(shift.getDay(), shift.getId(), shift.getShift(), shift.getEmployee());
    }

    /**
     * Convert a list of shifts to a list of data transfer objects.
     *
     * @param shifts
     * @return corresponding list of data transfer objects.
     */
    private static List<ShiftDto> convertToDto(List<Shift> shifts) {
        List<ShiftDto> Dtos = new ArrayList<>();
        for (Shift shift : shifts) {
            Dtos.add(convertToDto(shift));
        }
        return Dtos;
    }
}
