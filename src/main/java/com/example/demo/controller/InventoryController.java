package com.example.demo.controller;


import com.example.demo.entity.Inventory;
import com.example.demo.entity.ResponseMessage;
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

    @PostMapping("/additem")
    @ResponseBody
    ResponseEntity<ResponseMessage> addInventory(@RequestBody Inventory pr){
        HttpStatus stt;
        ResponseMessage rm;
        if(itemService.insertProduct(pr)) {
            stt = HttpStatus.OK;
            rm = new ResponseMessage(stt, "Success");
        }else {
            stt=HttpStatus.BAD_REQUEST;
            rm = new ResponseMessage(stt,"Bad request");
        }

            return ResponseEntity.status(stt).body(rm);

    }


}
