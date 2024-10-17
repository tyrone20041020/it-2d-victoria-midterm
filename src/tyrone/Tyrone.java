package tyrone;

import java.util.Scanner;

public class Tyrone{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String response;

        do {
            System.out.println("1. ADD");
            System.out.println("2. VIEW");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");

            System.out.print("Enter Action: ");
            int action = sc.nextInt();

            Tyrone bill = new Tyrone();
            switch (action) {
                case 1:
                    bill.addBill();
                    break;
                case 2:
                    bill.viewBills();
                    break;
                case 3:
                    bill.viewBills();
                    bill.updateBill();
                    bill.viewBills();
                    break;
                case 4:
                    bill.viewBills();
                    bill.deleteBill();
                    bill.viewBills();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

            System.out.print("Do you want to continue? (yes/no): ");
            response = sc.next();
        } while (response.equalsIgnoreCase("yes"));

        System.out.println("Thank you, See you soon!");
    }

    public void addBill() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Bill Date (YYYY-MM-DD): ");
        String billDate = sc.next();
        System.out.print("Total Amount: ");
        double totalAmount = sc.nextDouble();
        System.out.print("Payment Status (Paid/Unpaid): ");
        String paymentStatus = sc.next();

        String sql = "INSERT INTO tbl_bill (bill_date, total_amount, payment_status) VALUES (?, ?, ?)";
        conf.addBill(sql, billDate, totalAmount, paymentStatus);
    }

    public void viewBills() {
        config conf = new config();
        String query = "SELECT * FROM tbl_bill";
        String[] headers = {"Bill ID", "Bill Date", "Total Amount", "Payment Status"};
        String[] columns = {"bill_id", "bill_date", "total_amount", "payment_status"};
        conf.viewBills(query, headers, columns);
    }

    public void updateBill() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Bill ID to update: ");
        int billId = sc.nextInt();
        System.out.print("New Bill Date (YYYY-MM-DD): ");
        String billDate = sc.next();
        System.out.print("New Total Amount: ");
        double totalAmount = sc.nextDouble();
        System.out.print("New Payment Status (Paid/Unpaid): ");
        String paymentStatus = sc.next();

        String qry = "UPDATE tbl_bill SET bill_date = ?, total_amount = ?, payment_status = ? WHERE bill_id = ?";
        config conf = new config();
        conf.updateBill(qry, billDate, totalAmount, paymentStatus, billId);
        System.out.println("Bill updated successfully.");
    }

    public void deleteBill() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Bill ID to delete: ");
        int billId = sc.nextInt();

        String sql = "DELETE FROM bills WHERE bill_id = ?";
        config conf = new config();
        conf.deleteBill(sql, billId);
        System.out.println("Bill deleted successfully.");
    }
}
