package furnituremgr.model;

import javax.persistence.*;

@Entity
public class Furniture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String type;
    @Column
    private String price;
    @Column
    private String store;
    @Column
    private String link;

    public Furniture() {
    }

    public Furniture(FurnitureBuilder furnitureBuilder) {
        this.type = furnitureBuilder.type;
        this.price = furnitureBuilder.price;
        this.store = furnitureBuilder.store;
        this.link = furnitureBuilder.link;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Furniture{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price='" + price + '\'' +
                ", store='" + store + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public static class FurnitureBuilder {
        private String type;
        private String price;
        private String store;
        private String link;

        public FurnitureBuilder(String type, String store) {
            this.type = type;
            this.store = store;
        }

        public FurnitureBuilder withPrice(String price){
            this.price = price;
            return this;
        }

        public FurnitureBuilder withLink(String link){
            this.link = link;
            return this;
        }

        public Furniture build() {
            return new Furniture(this);
        }
    }
}

