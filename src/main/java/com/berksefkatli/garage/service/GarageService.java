package com.berksefkatli.garage.service;

import com.berksefkatli.garage.model.Vehicle;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class GarageService {

    public static final int GARAGE_CAPACITY = 10;

    private final HashMap<Integer, Vehicle> vehicleMap = new HashMap<>();
    private final boolean[] parkingLotUsed = new boolean[GARAGE_CAPACITY + 2];
    private int ticketIndex = 1;

    public String park(Vehicle vehicle) {
        if (vehicle.getSize() > GARAGE_CAPACITY) {
            return "Vehicle does not fit in this garage.";
        }

        for (int i = 1; i < GARAGE_CAPACITY + 1; i++) {
            if (isParkingAvailableForVehicleStartingFromIndex(vehicle, i)) {
                return parkToSlotStartingFrom(vehicle, i);
            }
        }

        return "Garage is full.";
    }

    private boolean isParkingAvailableForVehicleStartingFromIndex(Vehicle vehicle, int i) {
        if (!parkingLotUsed[i - 1] && !parkingLotUsed[i]) {
            for (int j = 1; j < vehicle.getSize() + 1; j++) {
                if (i + j < GARAGE_CAPACITY + 2) {
                    if (parkingLotUsed[i + j]) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private String parkToSlotStartingFrom(Vehicle vehicle, int index) {
        vehicle.setParkingIndexStart(index);
        vehicleMap.put(ticketIndex++, vehicle);
        for (int i = index; i < index + vehicle.getSize(); i++) {
            parkingLotUsed[i] = true;
        }
        return "Allocated " + vehicle.getSize() + (vehicle.getSize() == 1 ? " slot." : " slots.");
    }

    public void leave(int ticketId) {
        Vehicle parkedVehicle = vehicleMap.get(ticketId);
        if (parkedVehicle != null) {
            for (int i = parkedVehicle.getParkingIndexStart(); i < parkedVehicle.getParkingIndexStart() + parkedVehicle.getSize(); i++) {
                parkingLotUsed[i] = false;
            }
            vehicleMap.remove(ticketId);
        } else {
            throw new RuntimeException("No matching ticket exists with id: " + ticketId);
        }
    }

    public String status() {
        return "Status:" + System.lineSeparator() +
            vehicleMap.values().stream()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
