package com.eduit.spring.ws.service;


import com.eduit.spring.ws.exception.NotFoundException;
import com.eduit.spring.ws.model.Item;
import com.eduit.spring.ws.repository.ItemRepository;
import com.eduit.spring.ws.resource.request.ItemRequest;
import com.eduit.spring.ws.resource.request.PriceRequest;
import com.eduit.spring.ws.resource.response.ItemResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public Optional<ItemResponse> getById(Long id){
        Item item = itemRepository.findOne(id);
        if(item == null){
            return Optional.empty();
        }
        return Optional.of(ItemResponse.from(item));
    }


    public void removeById(Long id){
        if(!itemRepository.exists(id)){
          throw new NotFoundException( id + " not found on database ");
        }
        itemRepository.delete(id);
    }

    public List<ItemResponse> getAll(){
        Stream<Item> stream = StreamSupport.stream(itemRepository.findAll().spliterator(), false);
        return stream.map(item -> ItemResponse.from(item)).collect(Collectors.toList());
    }


    public ItemResponse updatePrice(PriceRequest priceRequest){
        if(!itemRepository.exists(priceRequest.getCode())){
            throw new NotFoundException( priceRequest.getCode() + " not found on database ");
        }
        Item item =  itemRepository.findOne(priceRequest.getCode());
        item.setPrice(priceRequest.getPrice());
        item = itemRepository.save(item);
        return ItemResponse.from(item);
    }


    public ItemResponse update(ItemRequest itemRequest){
        if(itemRequest.getId() == null){
            throw new NotFoundException(  " id could not be null ");
        }
        if(!itemRepository.exists(itemRequest.getId())){
            throw new NotFoundException( itemRequest.getId() + " not found on database ");
        }
        Item item = Item.from(itemRequest);
        item = itemRepository.save(item);
        return ItemResponse.from(item);
    }

    public  ItemResponse  saveItem(ItemRequest itemRequest){
        Item item = Item.from(itemRequest);
        item = itemRepository.save(item);
        return ItemResponse.from(item);
    }


}
