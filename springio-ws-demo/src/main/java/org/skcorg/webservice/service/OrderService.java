package org.skcorg.webservice.service;

import org.skcorg.webservices.schemas.jaxb.Order;

/**
 * <pre>
 * Service interface for Order Service operations, handles two operations. <ul>
 *     <li>placeOrderRequest</li>
 *     <li>cancelOrderRequest</li>
 * </ul>
 * </pre>
 *
 *
 * @see OrderServiceImpl
 *
 */
public interface OrderService {

	String placeOrder(Order order);

	boolean cancelOrder(String orderRef);
}