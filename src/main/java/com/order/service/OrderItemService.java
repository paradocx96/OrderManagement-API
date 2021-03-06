package com.order.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.model.OrderItem;
import com.order.repo.OrderItemRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    //private final ProductRepository productRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public void updateProductQtyAndSaveOrderItems(OrderItem[] orderItems) {
        for (OrderItem orderItem : orderItems) {

            Product product = getProductByOrderId(orderItem);

            if (product != null) {
                int newQuantity = product.getStockQty() - orderItem.getQuantity();

                JSONObject json = new JSONObject();
                json.put("id", product.getId());
                json.put("title", product.getTitle());
                json.put("sellPrice", product.getSellPrice());
                json.put("price", product.getPrice());
                json.put("image", product.getImage());
                json.put("categoryType", product.getCategoryType());
                json.put("stockQty", newQuantity);

                int response = saveProductUpdate(json);

                if (response == 200) {
                    orderItemRepository.save(orderItem);
                    System.out.println("OM - Product Updated!");
                    System.out.println("OM - Order Saved!");
                }
            }
        }
    }

    private Product getProductByOrderId(OrderItem orderItem) {

        String baseUrl = "http://13.77.46.50:8080/api/product/seller/get-product/" + orderItem.getProductId();

        try {
            URL url = new URL(baseUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            Product product = mapper.readValue(responseStream, Product.class);

            return product;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int saveProductUpdate(JSONObject json) {

        String baseUrl = "http://13.77.46.50:8080/api/product/seller/update-product";

        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder(URI.create(baseUrl))
                    .header("content-type", "application/json")
                    .header("Accept", "*/*")
                    .PUT(HttpRequest.BodyPublishers.ofString(String.valueOf(json)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response);
            return response.statusCode();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
