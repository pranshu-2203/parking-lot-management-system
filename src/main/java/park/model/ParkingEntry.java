package park.model;

import java.time.LocalDateTime;

public class ParkingEntry {
    private int id;
    private int vehicle_id;
    private int slot_id;
    private LocalDateTime EntryTime;
    private LocalDateTime ExitTime;
    private double TotalAmount;

    public int getId() {
        return id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public LocalDateTime getEntry_Time() {
        return EntryTime;
    }

    public LocalDateTime getExit_Time() {
        return ExitTime;
    }

    public double getTotal_amount() {
        return TotalAmount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public void setEntry_Time(LocalDateTime entry_Time) {
        EntryTime = entry_Time;
    }

    public void setExit_Time(LocalDateTime exit_Time) {
        ExitTime = exit_Time;
    }

    public void setTotal_amount(double total_amount) {
        TotalAmount = total_amount;
    }
}
