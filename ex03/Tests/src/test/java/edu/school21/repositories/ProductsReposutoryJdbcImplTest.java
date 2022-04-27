package edu.school21.repositories;

import edu.school21.models.Product;
import org.hsqldb.HsqlException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductsReposutoryJdbcImplTest {
    private static final List<Product> EXPECTED_FIND_BY_DIFFERENT_ID_PRODUCT = List.of(
            new Product(2,"MacBook", 100000),
            new Product(0,"cookies", 250),
            new Product(4,"coffee", 200));
    private static final List<Product> EXPECTED_FIND_ALL_PRODUCTS = List.of(
            new Product(0,"cookies", 250),
            new Product(1,"pepsi", 100),
            new Product(2,"MacBook", 100000),
            new Product(3,"iPhone", 75000),
            new Product(4,"coffee", 200));
    private static final Product EXPECTED_FIND_BY_ID_1_PRODUCT = new Product(0,"cookies", 250);
    private static final Product EXPECTED_FIND_BY_ID_2_PRODUCT = new Product(2,"MacBook", 100000);
    private static final Product EXPECTED_UPDATED_PRODUCT = new Product(4,"iPhone12", 90000);
    private static final Product EXPECTED_SAVED_PRODUCT = new Product(5,"sixthProduct", 455);
    private DataSource dataSource;
    private ProductsRepository productsRepository;

    @BeforeEach
    public void init() throws SQLException {
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("/schema.sql")
                .addScript("/data.sql")
                .build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource.getConnection());

    }

    @Test
    public void isFoundProductWithId0() {
        Product foundProduct = productsRepository.findById(0L).orElse(null);
        assertEquals(EXPECTED_FIND_BY_ID_1_PRODUCT, foundProduct);
    }

    @Test
    public void isFoundProductWithId2() {
        Product foundProduct = productsRepository.findById(2L).orElse(null);
        assertEquals(EXPECTED_FIND_BY_ID_2_PRODUCT, foundProduct);
    }

    @Test
    public void isFoundProductWithDifferentId() {
        List<Product> productList = new LinkedList<>();
        productList.add(productsRepository.findById(2L).orElse(null));
        productList.add(productsRepository.findById(0L).orElse(null));
        productList.add(productsRepository.findById(4L).orElse(null));
        assertEquals(EXPECTED_FIND_BY_DIFFERENT_ID_PRODUCT, productList);
    }

    @Test
    public void isUpdatedProductWithId4() {
        productsRepository.update(new Product(4, "iPhone12",90000));
        Product updatedProduct = productsRepository.findById(4L).orElse(null);
        assertEquals(EXPECTED_UPDATED_PRODUCT, updatedProduct);
    }

    @Test
    public void isSaveProduct() {
        productsRepository.save( new Product(5,"sixthProduct", 455));
        Product foundSixthProduct = productsRepository.findById(5L).orElse(null);
        assertEquals(EXPECTED_SAVED_PRODUCT, foundSixthProduct);

    }

    @Test
    public void areAllProductFind(){
        List<Product> productList = productsRepository.findAll();
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productList);
    }

    @Test
    public void isDeleted() {
        List<Product> productList = productsRepository.findAll();
        productsRepository.delete(3L);
        List<Product> productAfterDeleteList = productsRepository.findAll();
        assertEquals(productList.size() - 1, productAfterDeleteList.size());
    }
}
