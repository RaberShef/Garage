package com.berksefkatli.garage.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Truck.class),
    @JsonSubTypes.Type(value = Jeep.class),
    @JsonSubTypes.Type(value = Car.class)
})
public abstract class Vehicle implements Comparable<Vehicle> {
    private String plate;
    private String color;
    private String type;
    private int parkingIndexStart;

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getParkingIndexStart() {
        return parkingIndexStart;
    }

    public void setParkingIndexStart(int parkingIndexStart) {
        this.parkingIndexStart = parkingIndexStart;
    }

    public abstract int getSize();

    @Override
    public int compareTo(Vehicle o) {
        return Integer.compare(this.getParkingIndexStart(), o.getParkingIndexStart());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
            .append(getPlate()).append(" ")
            .append(getColor()).append("\t[");

        for (int i = parkingIndexStart; i < parkingIndexStart + getSize(); i++) {
            stringBuilder.append(i);
            if (i != parkingIndexStart + getSize() - 1) {
                stringBuilder.append(",");
            } else {
                stringBuilder.append("]");
            }
        }

        return stringBuilder.toString();
    }
}
