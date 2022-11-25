import java.util.List;

public class OrderStruct {
    public Integer orderId;
    public String recipientName;
    public String recipientSurname;
    public List<String> orderName;

    public OrderStruct(Integer orderId, String recipientName, String recipientSurname, List<String> orderName) {
        this.orderId = orderId;
        this.recipientName = recipientName;
        this.recipientSurname = recipientSurname;
        this.orderName = orderName;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientSurname() {
        return recipientSurname;
    }

    public List<String> getOrderName() {
        return orderName;
    }

    @Override
    public String toString() {
        return "OrderStruct{" +
                "orderId=" + orderId +
                ", recipientName='" + recipientName + '\'' +
                ", recipientSurname='" + recipientSurname + '\'' +
                ", orderName=" + orderName +
                '}';
    }
}
