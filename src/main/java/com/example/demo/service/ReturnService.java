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
public class ReturnService {

    @Autowired
    ReturnRepo retRepo;
    @Autowired
    InventoryRepo itemRepo;
    @Autowired
    BorrowRepo borRepo;

    public List<Return> getReturns(){

        return retRepo.findAll();

    }

    @Transactional
    public boolean itemReturn(Return rt){

        Optional<Inventory> optional = itemRepo.findById(rt.getItemId());;
        if (optional.isEmpty()) return false;
        Optional<Borrow> borrow = borRepo.findById(rt.getBorrowId());;
        if (borrow.isEmpty() || rt.getQuantity()!=borrow.get().getQuantity()) return false;//return only works when return quantity is in full for now

        Inventory item = optional.get();
        Borrow bor = borrow.get();

        item.setQuantity(item.getQuantity()+rt.getQuantity());
        bor.setReturned(true);
        itemRepo.save(item);
        retRepo.save(rt);
        borRepo.save(bor);
        return true;
    }

    @Transactional
    public boolean itemReturnEdit(int id, Return rt){

        Optional<Return> optional = retRepo.findById(id);;
        if (optional.isEmpty()) return false;

        retRepo.save(rt);
        return true;

    }


}
