package com.example.demo.service;

import com.example.demo.entity.Borrow;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Return;
import com.example.demo.repository.BorrowRepo;
import com.example.demo.repository.InventoryRepo;
import com.example.demo.repository.ReturnRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowService {

    @Autowired
    BorrowRepo borRepo;

    @Autowired
    InventoryRepo itemRepo;

    public List<Borrow> getBorrows(){

        return borRepo.findAll();
    }


    @Transactional
    public boolean itemBorrow(Borrow br){

        Optional<Inventory> optional = itemRepo.findById(br.getItemId());;
        if (optional.isEmpty()) return false;

        br.setReturned(false);

        Inventory item = optional.get();

        if(br.getQuantity()>item.getQuantity()) return false;//if borrow quantity>inventory quantity, fail to save

        item.setQuantity(item.getQuantity()-br.getQuantity());
        itemRepo.save(item);
        borRepo.save(br);
        return true;

    }

    @Transactional
    public boolean itemBorrowEdit(int id, Borrow br){

        Optional<Borrow> optional = borRepo.findById(id);;
        if (optional.isEmpty()) return false;

        borRepo.save(br);
        return true;

    }


}
