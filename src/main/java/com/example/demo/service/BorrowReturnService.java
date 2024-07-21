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

import java.util.Optional;

@Service
public class BorrowReturnService {

    @Autowired
    BorrowRepo borRepo;
    @Autowired
    ReturnRepo retRepo;
    @Autowired
    InventoryRepo itemRepo;

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



}
