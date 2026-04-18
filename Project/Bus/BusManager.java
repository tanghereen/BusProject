package Project.Bus;

import java.util.ArrayList;
import Project.Bus.BusClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class BusManager {
    public ArrayList<BusClass> busList = new ArrayList<BusClass>();
    static String filename = "Project/Bus/Bus.csv";
    File file = new File(filename);
    String line, make, model, type, fuelType;
    double fuelBurnRate, fuelCapacity, cruiseSpeed;

    public BusManager() throws FileNotFoundException {
    }

    public void listBuses() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                line = line.replace("\"", "");
                String[] columns = line.split(", ");
                make = columns[0];
                model = columns[1];
                type = columns[2];
                fuelType = columns[3]; // added fuelType to the list of columns
                fuelCapacity = Double.parseDouble(columns[4]); // shifted fuelCapacity to column 4 and added fuelType to column 3
                fuelBurnRate = Double.parseDouble(columns[5]); // shifted fuelBurnRate to column 5 and added fuelType to column 3
                cruiseSpeed = Double.parseDouble(columns[6]); // shifted cruiseSpeed to column 6 and added fuelType to column 3
                BusClass bus = new BusClass(make, model, type, fuelType, fuelCapacity, fuelBurnRate,
                        cruiseSpeed);
                busList.add(bus);

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (BusClass b : busList) {
                String line = b.displayBusInfo();
                pw.println(line);
            }
        }
    }

    public boolean removeBus(int row) {
        if (row >= 0 && row < busList.size()) {
            busList.remove(row);
            try {
                save();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else
            return false;
    }
}
