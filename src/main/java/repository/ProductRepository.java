package repository;

import entity.Product;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static db.DataSource.*;

public class ProductRepository {

    @SneakyThrows
    public void addProduct(Product product) {
        String sql = "INSERT INTO product(name, description, price, photo_id) VALUES ('%s','%s','%s','%s')"
                .formatted(product.getName(), product.getDescription(), product.getPrice(), product.getPhotoId());
        statement().execute(sql);
    }

    @SneakyThrows
    public Optional<Product> getProduct(UUID id) {
        String sql = "SELECT * FROM product WHERE id = '%s'".formatted(id);

        ResultSet resultSet = statement().executeQuery(sql);
        if (resultSet.next()) {
            Product build = Product.builder()
                    .id(resultSet.getObject("id", UUID.class))
                    .name(resultSet.getString("name"))
                    .description(resultSet.getString("description"))
                    .price(resultSet.getDouble("price"))
                    .photoId(resultSet.getString("photo_id"))
                    .build();
            return Optional.of(build);
        }
        return Optional.empty();
    }

    @SneakyThrows
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        ResultSet resultSet = statement().executeQuery("SELECT * FROM product");
        while (resultSet.next()) {
            products.add(Product.builder()
                    .id(resultSet.getObject("id", UUID.class))
                    .name(resultSet.getString("name"))
                    .description(resultSet.getString("description"))
                    .price(resultSet.getDouble("price"))
                    .photoId(resultSet.getString("photo_id"))
                    .build());
        }
        return products;
    }
}
