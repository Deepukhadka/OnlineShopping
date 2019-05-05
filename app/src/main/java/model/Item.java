package model;

public class Item {
    private String name;
    private int price;
    private String description;
    private String imageName;

    public Item(String name, int price, String imageName, String description) {
        this.name = name;
        this.price = price;
        this.imageName = imageName;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

}
