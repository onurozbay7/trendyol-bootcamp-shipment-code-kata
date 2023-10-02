package com.trendyol.shipment;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Basket {

    private List<Product> products;
    private final Integer thresholdValue =  3;

    public ShipmentSize getShipmentSize() {
        if(isBasketEmpty()){
            return null;
        }

        return incrementAndGetShipmentSize();
    }

    private ShipmentSize findLargestShipmentSize() {
        return products.stream()
                .map(Product::getSize)
                .max(ShipmentSize::compareTo).orElseThrow();
    }

    private ShipmentSize incrementAndGetShipmentSize(){
       ShipmentSize shipmentSize = products.stream().collect(groupingBy(Product::getSize, counting())).entrySet()
                .stream().filter(s -> s.getValue() >= thresholdValue).map(Map.Entry::getKey).findAny().orElse(null);

       if(products.size() < thresholdValue || shipmentSize == null){
        return findLargestShipmentSize();
       }

       return shipmentSize.getBiggerSize();

    }

    private boolean isBasketEmpty() {
        return products == null || products.isEmpty();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}


