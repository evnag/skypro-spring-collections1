package ru.skypro.skyprospringcollections1.service;

import org.springframework.stereotype.Service;
import ru.skypro.skyprospringcollections1.component.Cart;
import ru.skypro.skyprospringcollections1.model.Item;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private final Map<Integer, Item> items = new HashMap<>();

    private final Cart cart;

    public StoreService(Cart cart) {
        this.cart = cart;
    }

    @PostConstruct
    public void init() {
        items.put(1, new Item(1, "Keybord", 120));
        items.put(2, new Item(2, "Mouse", 100));
        items.put(3, new Item(3, "Headset", 150));
        items.put(4, new Item(4, "Webcam", 90));
    }

    public void add(List<Integer> ids) {
        cart.add(
                ids.stream()
                        .map(items::get)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
        );
    }

    public List<Item> get() {
        return cart.get();
    }
}
