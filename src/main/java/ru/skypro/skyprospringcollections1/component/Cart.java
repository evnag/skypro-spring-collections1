package ru.skypro.skyprospringcollections1.component;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.skypro.skyprospringcollections1.model.Item;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class Cart {
    private final List<Item> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(List<Item> items) {
        this.items.addAll(items);
    }

    public List<Item> get() {
        return new ArrayList<>(items);
    }
}
