package com.example.demo.controller;


import com.example.demo.entity.Borrow;
import com.example.demo.model.ResponseMessage;
import com.example.demo.entity.Return;
import com.example.demo.service.BorrowService;
import com.example.demo.service.InventoryService;
import com.example.demo.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("api/transactions")
@RestController
public class BorrowReturnController {

    @Autowired
    private InventoryService itemService;
    @Autowired
    private BorrowService borService;
    @Autowired
    private ReturnService retService;

    @GetMapping("/getborrows")
    @ResponseBody
    ResponseEntity<Object> getBorrow(){

        return ResponseEntity.status(HttpStatus.OK).body(borService.getBorrows());

    }

    @GetMapping("/getreturns")
    @ResponseBody
    ResponseEntity<Object> getReturn(){

        return ResponseEntity.status(HttpStatus.OK).body(retService.getReturns());

    }
    @PostMapping("/borrowitem")
    @ResponseBody
    ResponseEntity<ResponseMessage> borrowItem(@RequestBody Borrow br){
        HttpStatus stt;
        ResponseMessage rm;
        if(borService.itemBorrow(br)) {
            stt = HttpStatus.OK;
            rm = new ResponseMessage(stt, "Success");
        }else {
            stt=HttpStatus.BAD_REQUEST;
            rm = new ResponseMessage(stt,"Bad request");
        }

            return ResponseEntity.status(stt).body(rm);

    }

    @PutMapping("/borrowedit/{id}")
    @ResponseBody
    ResponseEntity<ResponseMessage> borrowEdit(@PathVariable("id") String id, @RequestBody Borrow br){
        HttpStatus stt;
        ResponseMessage rm;
        if(borService.itemBorrowEdit(Integer.parseInt(id),br)) {
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
        if(retService.itemReturn(rt)) {
            stt = HttpStatus.OK;
            rm = new ResponseMessage(stt, "Success");
        }else {
            stt=HttpStatus.BAD_REQUEST;
            rm = new ResponseMessage(stt,"Bad request");
        }

        return ResponseEntity.status(stt).body(rm);

    }
    @PutMapping("/returnedit/{id}")
    @ResponseBody
    ResponseEntity<ResponseMessage> returnEdit(@PathVariable("id") String id, @RequestBody Return rt){
        HttpStatus stt;
        ResponseMessage rm;
        if(retService.itemReturnEdit(Integer.parseInt(id),rt)) {
            stt = HttpStatus.OK;
            rm = new ResponseMessage(stt, "Success");
        }else {
            stt=HttpStatus.BAD_REQUEST;
            rm = new ResponseMessage(stt,"Bad request");
        }

        return ResponseEntity.status(stt).body(rm);

    }
}
