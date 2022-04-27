package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private Connection connection;
    private String DELETE_QUERY = "DELETE FROM product WHERE identifier = ?";
    private String FIND_ALL_QUERY = "SELECT * FROM product";
    private String FIND_BY_ID_QUERY = "SELECT * FROM product WHERE identifier = ?";
    private String UPDATE_QUERY = "UPDATE product SET name = ?,price = ? WHERE identifier = ?";
    private String SAVE_QUERY = "INSERT INTO product (name, price) VALUES (?, ?)";

    public ProductsRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Product> findAll() {

        List<Product> productList = new LinkedList<>();
        ResultSet resultSet = null;

        try {
            resultSet = connection.createStatement().executeQuery(FIND_ALL_QUERY);

            while (resultSet.next()){
                productList.add(new Product(
                        resultSet.getInt("identifier"),
                        resultSet.getString("name"),
                        resultSet.getInt("price")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return productList;
    }

    @Override
    public Optional<Product> findById(Long id) {

        Product ret = null;
        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                ret = new Product(
                        resultSet.getInt("identifier"),
                        resultSet.getString("name"),
                        resultSet.getInt("price")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return Optional.ofNullable(ret);
    }

    @Override
    public void update(Product product) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getId());
            preparedStatement.executeUpdate();
//            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void save(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_QUERY);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

//            Statement statement = connection.createStatement();
//            statement.executeQuery(DELETE_QUERY + id);
//            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
