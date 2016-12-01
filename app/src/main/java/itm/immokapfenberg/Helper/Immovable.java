package itm.immokapfenberg.helper;

public class Immovable {

    private String name;
    private int imageId;
    private float rating;
    private float price;

    public Immovable (String name, int imageId, float rating, float price) {
        this.name = name;
        this.imageId = imageId;
        this.rating = rating;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getImageId() {
        return this.imageId;
    }

    public float getRating() {
        return this.rating;
    }

    public float getPrice() {
        return this.price;
    }
}
