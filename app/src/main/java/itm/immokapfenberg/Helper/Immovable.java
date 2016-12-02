package itm.immokapfenberg.helper;

public class Immovable {

    private String name;
    private String imgUrl;
    private String[] otherImgUrls;
    private float rating;
    private float price;

    public Immovable(String name, String imgUrl, float rating, float price, String[] otherImgUrls) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.rating = rating;
        this.price = price;
        this.otherImgUrls = otherImgUrls;
    }

    public Immovable() {}

    public String getName() {
        return this.name;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public float getRating() {
        return this.rating;
    }

    public float getPrice() {
        return this.price;
    }

    public String[] getOtherImgUrls() {
        return this.otherImgUrls;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setOtherImgUrls(String[] otherImgUrls) {
        this.otherImgUrls = otherImgUrls;
    }
}
