package com.webApp.school.service;

import com.webApp.school.model.House;
import com.webApp.school.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService implements MyService<House, Long> {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Override
    public List<House> getAll() {
        return houseRepository.findAll();
    }

    @Override
    public void save(House house) {
        if (house.getId() != null) {
            update(house);
        } else {
            if (house.getHead().getId() == 0) {
                house.setHead(null);
            }
            houseRepository.save(house);
        }
    }

    @Override
    public House getById(Long aLong) {
        return houseRepository.getById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        houseRepository.deleteById(aLong);
    }

    @Override
    public void update(House houseNewInfo) {
        House toUpdate = getById(houseNewInfo.getId());
        toUpdate.setName(houseNewInfo.getName());
        if (houseNewInfo.getHead().getId() == 0) {
            toUpdate.setHead(null);
        } else {
            toUpdate.setHead(houseNewInfo.getHead());
        }
        houseRepository.save(toUpdate);
    }

    @Override
    public void defaultMeth() {
        MyService.super.defaultMeth();
    }
}
