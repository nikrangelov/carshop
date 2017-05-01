package carshop.entities;

/**
 * Created by nik on 5/1/17.
 */
public class Filter {


    private String manufacturer;

    private String model;

    private int minPrice;
    private int maxPrice;

    private int minMilage;
    private int maxMilage;

    private int minManufactureYear;
    private int maxManufactureYear;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getMinMilage() {
        return minMilage;
    }

    public void setMinMilage(int minMilage) {
        this.minMilage = minMilage;
    }

    public int getMaxMilage() {
        return maxMilage;
    }

    public void setMaxMilage(int maxMilage) {
        this.maxMilage = maxMilage;
    }

    public int getMinManufactureYear() {
        return minManufactureYear;
    }

    public void setMinManufactureYear(int minManufactureYear) {
        this.minManufactureYear = minManufactureYear;
    }

    public int getMaxManufactureYear() {
        return maxManufactureYear;
    }

    public void setMaxManufactureYear(int maxManufactureYear) {
        this.maxManufactureYear = maxManufactureYear;
    }
}
