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

        Optional<Inventory> optional = itemRepo.findById(rt.getItemId());

        if (optional.isEmpty()) return false;

        Optional<Borrow> borrow = borRepo.findById(rt.getBorrowId());

        if(borrow.isEmpty()) return false;

        int prevReturnTotal = retRepo.getReturnQuantitySumByBorrowId(rt.getBorrowId());

        System.out.println(prevReturnTotal);

        Borrow bor = borrow.get();

        int remainingBorrow = bor.getQuantity()-prevReturnTotal;

        System.out.println(remainingBorrow);

        if (rt.getQuantity()>remainingBorrow) return false;
        else if (rt.getQuantity()==remainingBorrow) {
            bor.setReturned(true);
            borRepo.save(bor);
        }


        Inventory item = optional.get();


        item.setQuantity(item.getQuantity()+rt.getQuantity());

        itemRepo.save(item);
        retRepo.save(rt);

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
