package com.example.demo.service;


import com.example.demo.entity.Inventory;
import com.example.demo.repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    InventoryRepo itemRepo;

    public List<Inventory> getInventory(){
        List<Inventory> listProd =itemRepo.findAll();

        return listProd;
    }


    public boolean addInventory(Inventory p) {

        try {
            itemRepo.save(p);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    public boolean addItemQuantity(int id, int count) {

        Optional<Inventory> optional = itemRepo.findById(id);
        if (optional.isEmpty()) {
            return false;
        }

        Inventory newItem = optional.get();
        newItem.setQuantity(newItem.getQuantity()+count);

        try {
            itemRepo.save(newItem);
        }catch (Exception e){
            return false;
        }

        return true;
    }


    public boolean deleteInventory(int id){
        Optional<Inventory> optional = itemRepo.findById(id);

        if (optional.isEmpty()) {
            return false;
        }
        else {
            try {
                itemRepo.deleteById(id);
            }catch(Exception e){
                throw(e);
            }
            return true;
        }

    }
    public boolean updateInventory(int id, Inventory inv){
        Optional<Inventory> optional = itemRepo.findById(id);

        if (optional.isEmpty()) {
            return false;
        }
        else {
            try {
                itemRepo.save(inv);
            }catch (Exception e){
                return false;
            }

            return true;
        }

    }

}
