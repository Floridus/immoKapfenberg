package itm.immokapfenberg.helper;

public class Immovable {

    private String name;
    private String imgUrl;
    private String[] otherImgUrls;
    private float rating;
    private float price;
    private int squaremeter;
    private int amount;
    private String adress;
    private int parkingamount;
    private String infos;
    private float distance;

    public Immovable(
            String name, String imgUrl,
            float rating, float price,
            String[] otherImgUrls, int squaremeter,
            int amount, String adress,
            int parkingamount, String infos,
            float distance) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.rating = rating;
        this.price = price;
        this.otherImgUrls = otherImgUrls;
        this.squaremeter = squaremeter;
        this.amount = amount;
        this.adress = adress;
        this.parkingamount = parkingamount;
        this.infos = infos;
        this.distance = distance;
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

    public int getSquaremeter() {
        return this.squaremeter;
    }

    public int getAmount() { return this.amount;}

    public String getAdress() {return this.adress;}

    public int getParkingamount() {return parkingamount;}

    public String getInfos() {return infos; }

    public String[] getOtherImgUrls() {
        return this.otherImgUrls;
    }

    public float getDistance() {
        return this.distance;
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

    public void setSquaremeter(int squaremeter) {
        this.squaremeter = squaremeter;
    }

    public void setAmount(int amount) {this.amount = amount; }

    public void setAdress(String adress) {this.adress = adress; }

    public void setParkingamount(int parkingamount) {this.parkingamount = parkingamount; }

    public void setInfos(String infos) {this.infos = infos; }

    public void setOtherImgUrls(String[] otherImgUrls) {
        this.otherImgUrls = otherImgUrls;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
