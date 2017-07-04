package com.dk.netty.xml;

/**
 * Created with IntelliJ IDEA
 * OrderFactory
 *
 * @author dk
 * @date 2017/7/4 16:11
 */
public class OrderFactory {

    public static Order create(long orderID) {
        Order order = new Order();
        order.setOrderNumber(orderID);
        order.setTotal(1999.999f);
        Address address = new Address();
        address.setCountry("中国");
        address.setState("湖北省");
        address.setCity("武汉市");
        address.setStreet1("解放大道");
        address.setPostCode("027");

        order.setBillTo(address);
        Customer customer = new Customer();
        customer.setCustomerNumber(orderID);
        customer.setFirstName("张");
        customer.setLastName("三丰");
        order.setCustomer(customer);
        order.setShipping(Shipping.INTERNATIONAL_MAIL);
        order.setShipTo(address);
        return order;
    }
}
