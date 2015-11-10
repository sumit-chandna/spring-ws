package org.skcorg.webservice.client;

import org.apache.log4j.Logger;
import org.skcorg.webservice.service.OrderService;
import org.skcorg.webservices.schemas.jaxb.CancelOrderRequest;
import org.skcorg.webservices.schemas.jaxb.CancelOrderResponse;
import org.skcorg.webservices.schemas.jaxb.ObjectFactory;
import org.skcorg.webservices.schemas.jaxb.Order;
import org.skcorg.webservices.schemas.jaxb.PlaceOrderRequest;
import org.skcorg.webservices.schemas.jaxb.PlaceOrderResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class OrderServiceClient extends WebServiceGatewaySupport implements
		OrderService {

	private static final Logger logger = Logger
			.getLogger(OrderServiceClient.class);
	private static final ObjectFactory WS_CLIENT_FACTORY = new ObjectFactory();

	@Override
	public boolean cancelOrder(String orderRef) {
		logger.debug("Preparing CancelOrderRequest.....");
		CancelOrderRequest request = WS_CLIENT_FACTORY
				.createCancelOrderRequest();
		request.setRefNumber(orderRef);

		logger.debug("Invoking Web service Operation[CancelOrder]....");
		CancelOrderResponse response = (CancelOrderResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://skcorg.org/OrderService/schema/placeOrderRequest"));

		logger.debug("Has the order cancelled: " + response.isCancelled());

		return response.isCancelled();
	}

	@Override
	public String placeOrder(Order order) {
		logger.debug("Preparing PlaceOrderRequest.....");
		PlaceOrderRequest request = WS_CLIENT_FACTORY.createPlaceOrderRequest();
		request.setOrder(order);

		logger.debug("Invoking Web service Operation[PlaceOrder]....");
		PlaceOrderResponse response = (PlaceOrderResponse) getWebServiceTemplate()
				.marshalSendAndReceive(request);
		logger.debug("Order reference:" + response.getRefNumber());
		return response.getRefNumber();
	}
}