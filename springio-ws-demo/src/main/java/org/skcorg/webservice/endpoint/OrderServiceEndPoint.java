package org.skcorg.webservice.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.skcorg.webservice.service.OrderService;
import org.skcorg.webservices.schemas.jaxb.CancelOrderRequest;
import org.skcorg.webservices.schemas.jaxb.CancelOrderResponse;
import org.skcorg.webservices.schemas.jaxb.ObjectFactory;
import org.skcorg.webservices.schemas.jaxb.PlaceOrderRequest;
import org.skcorg.webservices.schemas.jaxb.PlaceOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * <pre>
 * This is the endpoint for the {@link OrderService}.
 * Requests are simply delegated to the {@link OrderService} for processing.
 * Two operations are mapped, using annotation, as specified in the link,
 * <a href="http://static.springsource.org/spring-ws/sites/1.5/reference/html/server.html#server-at-endpoint"
 * >http://static.springsource.org/spring-ws/sites/1.5/reference/html/server.html#server-at-endpoint</a
 * ><ul>
 *     <li>placeOrderRequest</li>
 *     <li>cancelOrderRequest</li>
 * </ul>
 * </pre>
 *
 */
@Endpoint
public class OrderServiceEndPoint {

	private static final String NAMESPACE_URI = "http://skcorg.org/OrderService/schema";
	private final OrderService orderService;
	private final ObjectFactory JAXB_OBJECT_FACTORY = new ObjectFactory();

	@Autowired
	public OrderServiceEndPoint(OrderService orderService) {
		this.orderService = orderService;
	}

	@PayloadRoot(localPart = "placeOrderRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public PlaceOrderResponse getOrder(
			@RequestPayload PlaceOrderRequest placeOrderRequest) {
		PlaceOrderResponse response = JAXB_OBJECT_FACTORY
				.createPlaceOrderResponse();
		response.setRefNumber(orderService.placeOrder(placeOrderRequest
				.getOrder()));
		return response;
	}

	@PayloadRoot(localPart = "cancelOrderRequest", namespace = NAMESPACE_URI)
	@ResponsePayload
	public CancelOrderResponse cancelOrder(
			@RequestPayload CancelOrderRequest cancelOrderRequest) {
		CancelOrderResponse response = JAXB_OBJECT_FACTORY
				.createCancelOrderResponse();
		response.setCancelled(orderService.cancelOrder(cancelOrderRequest
				.getRefNumber()));
		return response;
	}

}