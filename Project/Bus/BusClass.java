package Project.Bus;

// this class is the basic bus class and is used as the shared attributes and methods of the city and long distance buses
public class BusClass {

    // This block is used to declare the Basic attributes of a bus
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

    // This is a blank constructor
    public BusClass() {
    }


    public String getMake() { // getter and setter for make
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
     // This funtion is to display the bus info in a string.
    public String displayBusInfo() {
        String info = make + ", "
                + type + ", "
                + fuelType + ", "
                + fuelCapacity + ", "
                + fuelBurnRate + ", "
                + cruiseSpeed;
        return info;
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
}
