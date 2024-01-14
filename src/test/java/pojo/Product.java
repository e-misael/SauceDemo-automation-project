package pojo;

public record Product (String name, String description, String price){
    public static final class ProductBuilder{
        private String name;
        private String description;
        private String price;

        public ProductBuilder setName (String name){
            this.name = name;
            return this;
        }
        public ProductBuilder setDescription (String description){
            this.description = description;
            return this;
        }
        public ProductBuilder setPrice (String price){
            this.price = price;
            return this;
        }

        public Product build(){
            return new Product(name, description, price);
        }
    }
}
