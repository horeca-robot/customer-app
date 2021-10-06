package com.customerapp.CustomerAppApi.core.services;

import com.customerapp.CustomerAppApi.core.interfaces.IOrderService;
import edu.fontys.horecarobot.databaselibrary.models.Order;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class OrderService implements IOrderService {

    public ArrayList<Order> getAllOrders(){
        ArrayList<Order> orders = new ArrayList<Order>();
        orders.add(new Order());
        orders.add(new Order());
        orders.add(new Order());
        return orders;
    }

    public void postOrder(Order order) throws Exception {
        if(order == null){
            throw new Exception();
        }
    }

}
