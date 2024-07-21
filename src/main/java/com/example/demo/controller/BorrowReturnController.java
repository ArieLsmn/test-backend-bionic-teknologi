package com.example.demo.controller;


import com.example.demo.entity.Borrow;
import com.example.demo.entity.ResponseMessage;
import com.example.demo.entity.Return;
import com.example.demo.service.BorrowReturnService;
import com.example.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("api/inventory")
@RestController
public class BorrowReturnController {

    @Autowired
    private InventoryService itemService;
    @Autowired
    private BorrowReturnService transactionService;

    @PostMapping("/borrowitem")
    @ResponseBody
    ResponseEntity<ResponseMessage> borrowItem(@RequestBody Borrow br){
        HttpStatus stt;
        ResponseMessage rm;
        if(transactionService.itemBorrow(br)) {
            stt = HttpStatus.OK;
            rm = new ResponseMessage(stt, "Success");
        }else {
            stt=HttpStatus.BAD_REQUEST;
            rm = new ResponseMessage(stt,"Bad request");
        }

            return ResponseEntity.status(stt).body(rm);

    }
    @PostMapping("/returnitem")
    @ResponseBody
    ResponseEntity<ResponseMessage> returnItem(@RequestBody Return rt){
        HttpStatus stt;
        ResponseMessage rm;
        if(transactionService.itemReturn(rt)) {
            stt = HttpStatus.OK;
            rm = new ResponseMessage(stt, "Success");
        }else {
            stt=HttpStatus.BAD_REQUEST;
            rm = new ResponseMessage(stt,"Bad request");
        }

        return ResponseEntity.status(stt).body(rm);

    }
}
