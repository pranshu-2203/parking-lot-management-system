package park.model;

public class Slot {
    private int id;
    private int parking_id;
    private int slot_no;
    private String vehicletype;
    private String status;

    public int getId() {
        return id;
    }

    public int getParking_id() {
        return parking_id;
    }

    public int getSlot_no() {
        return slot_no;
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setParking_id(int parking_id) {
        this.parking_id = parking_id;
    }

    public void setSlot_no(int slot_no) {
        this.slot_no = slot_no;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
