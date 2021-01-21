package com.me.teste.api_teste.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.me.teste.api_teste.model.payload.PedidoPayload;
import com.me.teste.api_teste.model.response.PedidoResponse;
import com.me.teste.api_teste.model.table.OrderItems;
import com.me.teste.api_teste.model.table.Orders;
import com.me.teste.api_teste.repository.OrdersRepository;
import com.me.teste.api_teste.util.ModelConverter;
import com.me.teste.api_teste.util.StatusEnum;
import com.me.teste.api_teste.validator.OrderNumberValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PedidoService extends Service<PedidoPayload, Orders> {

    @Autowired
    OrdersRepository ordersRepository;

    @Override
    protected List<String> validate(PedidoPayload payload, Orders entity) {
        OrderNumberValidator validator = new OrderNumberValidator();
        List<StatusEnum> status = validator.check(payload, null, new ArrayList<>());
        if (!status.isEmpty()) {
            List<String> statusMessage = new ArrayList<>();
            status.forEach(element -> statusMessage.add(element.getMessage()));
            return statusMessage;
        }
        return new ArrayList<>();
    }

    @Override
    protected Orders findOrder(String id) throws IllegalArgumentException {
        Optional<Orders> order = ordersRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            return null;
        }
    }

    /**
     * Update the base OrderItems with new values converted from payload.
     * 
     * @param base
     * @param newValues
     * @return
     */
    private List<OrderItems> updateOrderItems(List<OrderItems> base, List<OrderItems> newValues) {
        for (int i = 0; i < base.size(); i++) {
            base.get(i).setQuantity(newValues.get(i).getQuantity());
            base.get(i).setUnitPrice(newValues.get(i).getUnitPrice());
            base.get(i).getItem().setDescription(newValues.get(i).getItem().getDescription());
        }
        return base;
    }

    /**
     * Update the base Order with new values converted from payload.
     * 
     * @param base
     * @param newValues
     */
    private void updateOrders(Orders base, Orders newValues) {
        if (base.getItems().size() == newValues.getItems().size()) {
            base.setItems(updateOrderItems(base.getItems(), newValues.getItems()));
        } else if (base.getItems().size() < newValues.getItems().size()) {
            base.setItems(updateOrderItems(base.getItems(), newValues.getItems()));
            for (int i = base.getItems().size(); i < newValues.getItems().size(); i++) {
                base.getItems().add(newValues.getItems().get(i));
            }
        } else {
            for (int i = base.getItems().size() - 1; i > newValues.getItems().size() - 1; i--) {
                base.getItems().remove(i);
            }
            base.setItems(updateOrderItems(base.getItems(), newValues.getItems()));
        }
        base.setTotalQuantity(newValues.getTotalQuantity());
        base.setTotalPrice(newValues.getTotalPrice());
    }

    public ResponseEntity<PedidoResponse> find(String id) throws IllegalArgumentException {
        List<String> status = validate(new PedidoPayload(id), null);
        if (!status.isEmpty()) {
            throw new IllegalArgumentException(status.toString());
        } else {
            Orders orders = findOrder(id);
            if (orders != null) {
                return new ResponseEntity<>(ModelConverter.convertsTableToResponse(orders), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    public ResponseEntity<PedidoResponse> create(PedidoPayload pedido) throws IllegalArgumentException {
        if (pedido != null) {
            List<String> status = validate(pedido, null);
            if (!status.isEmpty()) {
                throw new IllegalArgumentException(status.toString());
            } else {
                if (findOrder(pedido.getPedido()) == null) {
                    Orders order = ModelConverter.convertsPayloadToTable(pedido);
                    order.setStatus("CREATED");
                    ordersRepository.save(order);
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            }
        } else {
            throw new IllegalArgumentException("The payload can't be null");
        }
    }

    public ResponseEntity<PedidoResponse> update(PedidoPayload pedido) throws IllegalArgumentException {
        if (pedido != null) {
            List<String> status = validate(pedido, null);
            if (!status.isEmpty()) {
                throw new IllegalArgumentException(status.toString());
            } else {
                Orders orders = findOrder(pedido.getPedido());
                if (orders != null) {
                    updateOrders(orders, ModelConverter.convertsPayloadToTable(pedido));
                    ordersRepository.save(orders);
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    Orders order = ModelConverter.convertsPayloadToTable(pedido);
                    ordersRepository.save(order);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
            }
        } else {
            throw new IllegalArgumentException("The payload can't be null");
        }
    }

    public ResponseEntity<HttpStatus> delete(String id) {
        List<String> status = validate(new PedidoPayload(id), null);
        if (!status.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Orders orders = findOrder(id);
            if (orders != null) {
                ordersRepository.delete(orders);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

}
