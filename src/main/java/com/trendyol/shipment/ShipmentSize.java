package com.trendyol.shipment;

public enum ShipmentSize {

    SMALL,
    MEDIUM,
    LARGE,
    X_LARGE;

    public ShipmentSize getBiggerSize() {
        if (this == X_LARGE) {
            return this;
        }
        return ShipmentSize.values()[this.ordinal() + 1];
    }


}
