package com.example.demo.controller;


import com.example.demo.entity.Inventory;
import com.example.demo.entity.User;
import com.example.demo.model.ResponseMessage;
import com.example.demo.service.InventoryService;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Operation(summary = "Add new user info")
    @PostMapping("/registeruser")
    @ResponseBody
    ResponseEntity<ResponseMessage> addUser(@RequestBody User user){
        HttpStatus stt;
        ResponseMessage rm;
        if(userService.addUser(user)) {
            stt = HttpStatus.OK;
            rm = new ResponseMessage(stt, "Success");
        }else {
            stt=HttpStatus.BAD_REQUEST;
            rm = new ResponseMessage(stt,"Bad request");
        }

        return ResponseEntity.status(stt).body(rm);

    }

    @Operation(summary = "Delete user from table")
    @DeleteMapping("/deleteuser/{id}")
    @ResponseBody
    ResponseEntity<ResponseMessage> deleteUser(@PathVariable("id") int id){
        HttpStatus stt;
        ResponseMessage rm;
        if(userService.deleteUser(id)) {
            stt = HttpStatus.OK;
            rm = new ResponseMessage(stt, "Success");
        }else {
            stt=HttpStatus.BAD_REQUEST;
            rm = new ResponseMessage(stt,"Bad request");
        }

        return ResponseEntity.status(stt).body(rm);

    }

    @Operation(summary = "Edit user data in table")
    @PutMapping("/edituser/{id}")
    @ResponseBody
    ResponseEntity<ResponseMessage> editUser(@PathVariable("id") int id, @RequestBody User usr){
        HttpStatus stt;
        ResponseMessage rm;
        if(userService.editUser(id,usr)) {
            stt = HttpStatus.OK;
            rm = new ResponseMessage(stt, "Success");
        }else {
            stt=HttpStatus.BAD_REQUEST;
            rm = new ResponseMessage(stt,"Bad request");
        }

        return ResponseEntity.status(stt).body(rm);

    }
}
