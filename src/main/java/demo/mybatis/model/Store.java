package demo.mybatis.model;

public class Store {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.STORE.ID
     *
     * @mbg.generated Sun May 07 20:21:13 EEST 2023
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.STORE.ADDRESS
     *
     * @mbg.generated Sun May 07 20:21:13 EEST 2023
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.STORE.NAME
     *
     * @mbg.generated Sun May 07 20:21:13 EEST 2023
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.STORE.ID
     *
     * @return the value of PUBLIC.STORE.ID
     *
     * @mbg.generated Sun May 07 20:21:13 EEST 2023
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.STORE.ID
     *
     * @param id the value for PUBLIC.STORE.ID
     *
     * @mbg.generated Sun May 07 20:21:13 EEST 2023
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.STORE.ADDRESS
     *
     * @return the value of PUBLIC.STORE.ADDRESS
     *
     * @mbg.generated Sun May 07 20:21:13 EEST 2023
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.STORE.ADDRESS
     *
     * @param address the value for PUBLIC.STORE.ADDRESS
     *
     * @mbg.generated Sun May 07 20:21:13 EEST 2023
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.STORE.NAME
     *
     * @return the value of PUBLIC.STORE.NAME
     *
     * @mbg.generated Sun May 07 20:21:13 EEST 2023
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.STORE.NAME
     *
     * @param name the value for PUBLIC.STORE.NAME
     *
     * @mbg.generated Sun May 07 20:21:13 EEST 2023
     */
    public void setName(String name) {
        this.name = name;
    }
}