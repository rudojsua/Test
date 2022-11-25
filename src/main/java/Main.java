import java.util.List;

public class Main {


    public static void main(String[] args) {
        OrderStruct ob = new OrderStruct(12, "Jan", "Kowalski", List.of("pl", "dost"));

        InvoiceStructure is=new InvoiceStructure().mapToInvoices(
                new OrderStruct(12, "Jan", "Kowalski", List.of("pl", "dost")));

        System.out.println(is);
    }
}
