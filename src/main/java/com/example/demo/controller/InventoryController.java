package com.example.demo.controller;


import com.example.demo.entity.Inventory;
import com.example.demo.model.ResponseMessage;
import com.example.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("api/inventory")
@RestController
public class InventoryController {

    @Autowired
    private InventoryService itemService;


    @GetMapping("/getitems")
    @ResponseBody
    ResponseEntity<Object> getInventory(){

        return ResponseEntity.status(HttpStatus.OK).body(itemService.getInventory());

    }

    @PostMapping("/additem")
    @ResponseBody
    ResponseEntity<ResponseMessage> addInventory(@RequestBody Inventory pr){
        HttpStatus stt;
        ResponseMessage rm;
        if(itemService.addInventory(pr)) {
            stt = HttpStatus.OK;
            rm = new ResponseMessage(stt, "Success");
        }else {
            stt=HttpStatus.BAD_REQUEST;
            rm = new ResponseMessage(stt,"Bad request");
        }

            return ResponseEntity.status(stt).body(rm);

    }
    @DeleteMapping("/deleteitem/{id}")
    @ResponseBody
    ResponseEntity<ResponseMessage> deleteInventory(@PathVariable("id") String id){
        HttpStatus stt;
        ResponseMessage rm;
        if(itemService.deleteInventory(Integer.parseInt(id))) {
            stt = HttpStatus.OK;
            rm = new ResponseMessage(stt, "Success");
        }else {
            stt=HttpStatus.BAD_REQUEST;
            rm = new ResponseMessage(stt,"Bad request");
        }

        return ResponseEntity.status(stt).body(rm);

    }

    @PutMapping("/updateitem/{id}")
    @ResponseBody
    ResponseMessage updateInventory(@PathVariable("id") String id, @RequestBody Inventory inv){
        if (!id.matches("^[0-9]+$")) return new ResponseMessage(HttpStatus.BAD_REQUEST,"Only numbers allowed");

        boolean check = itemService.updateInventory(Integer.parseInt(id), inv);
        if (check)
            return new ResponseMessage(HttpStatus.OK, "Success");

        else return new ResponseMessage(HttpStatus.NOT_FOUND, "Data not found");

    }


}
