package demo.mybatis.dao;

import demo.mybatis.model.Product;
import java.util.List;

public interface ProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PRODUCT
     *
     * @mbg.generated Sun May 07 20:21:13 EEST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PRODUCT
     *
     * @mbg.generated Sun May 07 20:21:13 EEST 2023
     */
    int insert(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PRODUCT
     *
     * @mbg.generated Sun May 07 20:21:13 EEST 2023
     */
    Product selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PRODUCT
     *
     * @mbg.generated Sun May 07 20:21:13 EEST 2023
     */
    List<Product> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.PRODUCT
     *
     * @mbg.generated Sun May 07 20:21:13 EEST 2023
     */
    int updateByPrimaryKey(Product record);
}