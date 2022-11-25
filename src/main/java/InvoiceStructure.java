import java.util.List;

public class InvoiceStructure {

    public String recipientFullName;
    public String recipientOrderId;
    public String notesConcated;

    public InvoiceStructure() {
    }

    public InvoiceStructure(String recipientFullName, String recipientOrderId, String notesConcated) {
        this.recipientFullName = recipientFullName;
        this.recipientOrderId = recipientOrderId;
        this.notesConcated = notesConcated;
    }

    public InvoiceStructure mapToInvoices(OrderStruct ob) {
        this.recipientFullName = ob.getRecipientName() + " " + ob.getRecipientSurname();
        this.recipientOrderId = "ORDER-" + ob.getOrderId();
        this.notesConcated = ob.orderName.toString();
        return new InvoiceStructure(recipientFullName,recipientOrderId,notesConcated);
    }

    @Override
    public String toString() {
        return "recipientFullName='" + recipientFullName + '\'' +
                ", recipientOrderId='" + recipientOrderId + '\'' +
                ", notesConcated='" + notesConcated + '\'';
    }
}

