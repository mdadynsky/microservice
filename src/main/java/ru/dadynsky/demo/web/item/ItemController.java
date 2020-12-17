package ru.dadynsky.demo.web.item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dadynsky.demo.entity.Item;
import ru.dadynsky.demo.service.IItemService;


@Controller
public class ItemController {
    private final IItemService itemService;

    public ItemController(IItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/item")
    @ResponseBody
    public String getItems(
            @RequestParam(required = false) String serialNumber,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer offset
    ) {
        return itemService.getItemsJson(serialNumber, limit, offset);
    }

    @PostMapping(
            value = "/item", consumes = "application/json", produces = "application/json")
    public ResponseEntity saveItem(
            @RequestBody Item item
    ) {
        itemService.save(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/item/{id}")
    @ResponseBody
    public String getParty(
            @PathVariable("id") Integer id
    ) {
        return itemService.getItemJson(id);
    }

    @PostMapping(value = "/item/{id}/sale")
    public ResponseEntity saleItem(
            @PathVariable("id") Integer id
    ) {
        return itemService.sale(id);
    }

}
