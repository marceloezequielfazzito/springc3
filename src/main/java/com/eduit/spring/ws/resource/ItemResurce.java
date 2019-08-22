package com.eduit.spring.ws.resource;


import com.eduit.spring.ws.resource.request.ItemRequest;
import com.eduit.spring.ws.resource.request.PriceRequest;
import com.eduit.spring.ws.resource.response.ItemResponse;
import com.eduit.spring.ws.resource.response.PriceResponse;
import com.eduit.spring.ws.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("item")
public class ItemResurce {


    private ItemService itemService;

    @Autowired
    public ItemResurce(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/{id}" , produces = "application/json")
    public @ResponseBody ResponseEntity<?> getById(@PathVariable("id") Long id){
        Optional<ItemResponse> itemResponse = itemService.getById(id);
        return itemResponse.
                map(ir -> new ResponseEntity(ir,HttpStatus.OK)).
                orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping(value = "/{id}/price" , produces = "application/json")
    public @ResponseBody ResponseEntity<?> getPriceById(@PathVariable("id") Long id){
        Optional<ItemResponse> itemResponse = itemService.getById(id);
        return itemResponse.
                map(ir -> new ResponseEntity(new PriceResponse(ir.getPrice()),HttpStatus.OK)).
                orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PatchMapping(produces = "application/json")
    public @ResponseBody ResponseEntity<?> updatePrice (@RequestBody PriceRequest priceRequest){
        ItemResponse itemResponse = itemService.updatePrice(priceRequest);
        return ResponseEntity.ok(itemResponse);
    }

    @GetMapping( produces = "application/json")
    public @ResponseBody ResponseEntity<?> getAll(){
        List<ItemResponse> itemResponses = itemService.getAll();
        return ResponseEntity.ok(itemResponses);
    }

    @DeleteMapping(value = "/{id}" , produces = "application/json")
    public @ResponseBody ResponseEntity<?> delete (@PathVariable("id") Long id){
        itemService.removeById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody ResponseEntity<?> saveItem( @RequestBody @Valid ItemRequest itemRequest){
        ItemResponse itemResponse = itemService.saveItem(itemRequest);
        return ResponseEntity.ok(itemResponse);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody ResponseEntity<?> updateItem( @RequestBody @Valid ItemRequest itemRequest){
        ItemResponse itemResponse = itemService.update(itemRequest);
        return ResponseEntity.ok(itemResponse);
    }

}
