package ru.skypro.skyprospringcollections1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.skyprospringcollections1.model.Item;
import ru.skypro.skyprospringcollections1.service.StoreService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/add")
    public void add(@RequestParam("id") List<Integer> ids) {
        storeService.add(ids);
    }

    @GetMapping("/get")
    public List<Item> get() {
        return storeService.get();
    }
}
