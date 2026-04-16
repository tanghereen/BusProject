package Project.Bus;

// finally made it

public class BusClass {
    String make = "make";
    String model = "model";
    String type = "type";
    String fuelType = "fuelType";
    double fuelCapacity = 0.0;
    double cruiseSpeed = 0.0;
    double fuelBurnRate = 0.0;

    BusClass(String make, String model, String type, String fuelType, double fuelCapacity, double fuelBurnRate, double cruiseSpeed) {
        this.make = make;
        this.model = model;
        this.type = type;
        this.fuelType = fuelType;
        this.fuelCapacity = fuelCapacity;
        this.fuelBurnRate = fuelBurnRate;
        this.cruiseSpeed = cruiseSpeed;
    }

    public BusClass() {
    }

    public String getMake() { // getter and setter for make
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() { // getter and setter for model
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() { // getter and setter for type
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
     public String getFuelType() { // added getter and setter for fuelType
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getFuelCapacity() { // getter and setter for fuelCapacity
        return fuelCapacity;
    }

    public void setFuelCapacity(double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public void setCruiseSpeed(double CruiseSpeed) { // getter and setter for cruiseSpeed
        this.cruiseSpeed = CruiseSpeed;
    }

    public double getCruiseSpeed() {
        return cruiseSpeed;
    }

    public double getFuelBurnRate() { // getter and setter for fuelBurnRate
        return fuelBurnRate;
    }

    public void setFuelBurnRate(double fuelBurnRate) {
        this.fuelBurnRate = fuelBurnRate;
    }

    public String displayBusInfo() { // displays bus info in the format for the csv file
        String info = make + ", "
                + model + ", "
                + type + ", "
                + fuelType + ", "   
                + fuelCapacity + ", "
                + fuelBurnRate + ", "
                + cruiseSpeed;
        return info;
    }
}
