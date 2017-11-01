package com.dao;


import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductsDao {

    @Autowired
    private Session session;

    private String keySpace;

    @Autowired
    public ProductsDao(@Value("${cassandra.keyspace}") String keyspace) {
        keySpace = keyspace;
    }

    /** Table Name */
    private static final String PRODUCTS_TABLE = "products";

    /** Column Names */
    private static final String PRODUCT_ID_COLUMN = "id";
    private static final String PRODUCT_NAME_COLUMN = "name";
    private static final String PRODUCT_CURRENCY_PRICE_COLUMN = "currency_price";
    private static final String PRODUCT_CURRENCY_CODE_COLUMN = "currency_code";

    public List<Product> getProductDetailsById(String id) {
        ResultSet result = session.execute("SELECT * FROM "+keySpace+"."+PRODUCTS_TABLE +" where id='"+id+"'");
        return result.all()
                .stream()
                .map(this::productRowMapper)
                .collect(Collectors.toList());
    }

    public Product productRowMapper(Row row){
        Product product = new Product();
        product.setId(row.getString(PRODUCT_ID_COLUMN));
        product.setName(row.getString(PRODUCT_NAME_COLUMN));
        product.setCurrencyPrice(row.getString(PRODUCT_CURRENCY_PRICE_COLUMN));
        product.setCurrencyCode(row.getString(PRODUCT_CURRENCY_CODE_COLUMN));

        return product;
    }

    public void upsertProductDetails(Map hm,String productId){
        StringBuilder sb = new StringBuilder("insert into ");
        sb.append(keySpace).append(".").append(PRODUCTS_TABLE);
        sb.append(" (").append(PRODUCT_ID_COLUMN).append(",").append(PRODUCT_NAME_COLUMN).append(",");
        sb.append(PRODUCT_CURRENCY_PRICE_COLUMN).append(",").append(PRODUCT_CURRENCY_CODE_COLUMN).append(") values ( '");
        sb.append(productId).append("' , '").append(hm.get(PRODUCT_NAME_COLUMN)).append("', '");
        sb.append(hm.get(PRODUCT_CURRENCY_PRICE_COLUMN)).append("', '").append(hm.get(PRODUCT_CURRENCY_CODE_COLUMN));
        sb.append("')");
        session.execute(sb.toString());
    }
}
