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
    InventoryRepo prodRepo;

    public List<Inventory> listProduct(){
        List<Inventory> listProd =prodRepo.findAll();

        return listProd;
    }


    public boolean insertProduct(Inventory p) {

        try {
            prodRepo.save(p);
        }catch (Exception e){
            return false;
        }

        return true;
    }



    public boolean deleteProduct(int id){
        Optional<Inventory> optional = prodRepo.findById(id);

        if (optional.isEmpty()) {
            return false;
        }
        else {
            try {
                prodRepo.deleteById(id);
            }catch(Exception e){
                throw(e);
            }
            return true;
        }

    }

}
