import java.util.*;

public class Main {
    final static int M = Integer.MAX_VALUE;

    static List<String> city = Arrays.asList("Delhi", "Noida", "Gurgaon", "Chandigarh");
    static List<String> areas = Arrays.asList(
            "Kashmere Gate ISBT", "Rajiv Chowk", "India Gate", "Connaught Place", "Anand Vihar ISBT",
            "Sector 62", "Sector 128", "Noida Stadium", "ISKCON", "DLF Mall",
            "Ambience Mall", "Sultanpur National Park", "DLF Cyber Hub",
            "Rose Garden", "Sukhna Lake", "MC Zoological Park", "Elante Mall");

    static int[][] path = {
            { 0, 3, M, 2, 10, M, M, M, M, M, M, M, M, M, M, M, M },
            { 3, 0, M, 5, M, 10, 20, M, M, M, M, M, M, M, M, M, M },
            { M, M, 0, M, 7, M, M, M, 18, M, M, M, 7, M, M, M, M },
            { 2, 5, M, 0, 1, M, M, M, M, M, 10, M, M, M, M, M, M },
            { 10, M, 7, 1, 0, M, M, M, M, M, M, M, M, M, 50, M, M },
            { M, 10, M, M, M, 0, 5, M, 9, M, M, M, M, M, M, M, M },
            { M, 20, M, M, M, 5, 0, 2, M, 3, M, M, M, M, 70, M, M },
            { M, M, M, M, M, M, 2, 0, M, M, M, M, M, M, M, M, M },
            { M, M, 18, M, M, 9, M, M, 0, M, M, M, M, M, M, M, M },
            { M, M, M, M, M, M, 3, M, M, 0, M, 28, M, M, M, M, M },
            { M, M, M, 10, M, M, M, M, M, M, 0, M, 3, M, M, 30, M },
            { M, M, M, M, M, M, M, M, M, 28, M, 0, 1, M, M, M, M },
            { M, M, 7, M, M, M, M, M, M, M, 3, 1, 0, M, M, M, M },
            { M, M, M, M, M, M, M, M, M, M, M, M, M, 0, 1, 8, M },
            { M, M, M, M, 50, M, 70, M, M, M, M, M, M, 1, 0, M, 5 },
            { M, M, M, M, M, M, M, M, M, M, 30, M, M, 8, M, 0, 6 },
            { M, M, M, M, M, M, M, M, M, M, M, M, M, M, 5, 6, 0 }
    };

    static void FloydWarshall() {
        int n = 16;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (path[i][j] > (path[i][k] + path[k][j]) && (path[k][j] != M && path[i][k] != M)) {
                        path[i][j] = path[i][k] + path[k][j];
                    }
                }
            }
        }
    }

    static class Trip {
        int start, end, price, bus_type;
        String company_name, bus_id;
    }

    static class Bus {
        int index, bus_status, start, end;
        String company, bus_id;
    }

    static class Admin {
        String user_id, pass;
        Bus[] ob = new Bus[20];

        Admin() {
            user_id = "admin";
            pass = "test";
        }

        void login() {
            Scanner scanner = new Scanner(System.in);
            String username, password;
            while (true) {
                System.out.print("Enter Username: ");
                username = scanner.nextLine();
                System.out.print("Enter password: ");
                password = scanner.nextLine();
                if (user_id.equals(username) && pass.equals(password)) {
                    System.out.println("\n\n\t\tLOGGED IN SUCCESSFULLY\n\t\t\tWELCOME\n\n\n");
                    admin_menu();
                    break;
                } else {
                    System.out.println("Enter correct username and password\nPlease try again\n");
                }
            }
        }

        void change_password() {
            Scanner scanner = new Scanner(System.in);
            String new_pass, confirm_pass;
            while (true) {
                System.out.print("Enter new password: ");
                new_pass = scanner.nextLine();
                System.out.print("Confirm password: ");
                confirm_pass = scanner.nextLine();
                if (new_pass.equals(confirm_pass)) {
                    pass = new_pass;
                    System.out.println("Changed password successfully.\n");
                    break;
                } else {
                    System.out.println("Passwords did not match, please enter new password and confirm it.\n");
                }
            }
        }

        void bus_details() {
            System.out.println("----------------" + noofbus + " Buses Found----------------");
            for (int i = 0; i < noofbus; i++) {
                System.out.println("Company Name: " + ADM.ob[i].company);
                System.out.println("Bus ID: " + ADM.ob[i].bus_id);
                System.out.println("Bus Availability: " + ADM.ob[i].bus_status);
                if (i != noofbus - 1)
                    System.out.println();
            }
            System.out.println("--------------------------------------------");
            System.out.println();
        }

        void add_new_bus() {
            Scanner scanner = new Scanner(System.in);
            Bus B = new Bus();
            System.out.print("Enter company Name: ");
            B.company = scanner.nextLine();
            System.out.print("Enter BUS ID: ");
            B.bus_id = scanner.nextLine();
            B.bus_status = 1;
            ob[noofbus++] = B;
            System.out.println("Bus added successfully.\n");
        }

        void delete_bus() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("----------------" + noofbus + " Buses Found----------------");
            for (int i = 0; i < noofbus; i++) {
                System.out.println("Bus ID: " + ADM.ob[i].bus_id);
                System.out.println("Bus Availability: " + ADM.ob[i].bus_status);
                if (i != noofbus - 1)
                    System.out.println();
            }
            System.out.println("--------------------------------------------");
            System.out.print("Enter Bus ID to delete: ");
            String id = scanner.nextLine();
            int index = -1;
            for (int i = 0; i < noofbus; i++) {
                if (ADM.ob[i].bus_id.equals(id)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                for (int i = index; i < noofbus - 1; i++) {
                    ADM.ob[i] = ADM.ob[i + 1];
                }
                noofbus--;
                System.out.println("Bus Deleted Successfully.\n");
            } else {
                System.out.println("Bus not found\n");
            }
        }

        void update_bus_status() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("----------------" + noofbus + " Buses Found----------------");
            for (int i = 0; i < noofbus; i++) {
                System.out.println("Bus ID: " + ADM.ob[i].bus_id);
                System.out.println("Bus Availability: " + ADM.ob[i].bus_status);
                if (i != noofbus - 1)
                    System.out.println();
            }
            System.out.println("--------------------------------------------");
            System.out.print("Enter Bus ID to update status: ");
            String id = scanner.nextLine();
            int index = -1;
            for (int i = 0; i < noofbus; i++) {
                if (ADM.ob[i].bus_id.equals(id)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                System.out.print("Enter new status (0: Bus is not available, 1: Bus is available): ");
                int status = scanner.nextInt();
                ADM.ob[index].bus_status = status;
                System.out.println("Bus status updated successfully.\n");
            } else {
                System.out.println("Bus not found\n");
            }
        }

        void admin_menu() {
            while (true) {
                System.out.println("1. View Bus Details");
                System.out.println("2. Add New Bus");
                System.out.println("3. Delete Bus");
                System.out.println("4. Update Bus Status");
                System.out.println("5. Change Password");
                System.out.println("6. Logout");
                System.out.print("\nEnter your choice: ");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> bus_details();
                    case 2 -> add_new_bus();
                    case 3 -> delete_bus();
                    case 4 -> update_bus_status();
                    case 5 -> change_password();
                    case 6 -> {
                        System.out.println("Logging out...\n");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    static int noofbus = 0;

    static Admin ADM = new Admin();

    public static void main(String[] args) {
        FloydWarshall();
        Scanner scanner = new Scanner(System.in);
        ADM.login();
        while (true) {
            System.out.println("Enter source: ");
            String src = scanner.nextLine();
            int srcIndex = areas.indexOf(src);
            if (srcIndex == -1) {
                System.out.println("Invalid Source\n");
                continue;
            }
            System.out.println("Enter destination: ");
            String dest = scanner.nextLine();
            int destIndex = areas.indexOf(dest);
            if (destIndex == -1) {
                System.out.println("Invalid Destination\n");
                continue;
            }
            System.out.println(
                    "Cheapest path between " + city.get(srcIndex / 5) + " and " + city.get(destIndex / 5) + " is: ");
            printPath(srcIndex, destIndex);
            System.out.println("Would you like to continue (yes/no)?");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("yes")) {
                System.out.println("Exiting...");
                break;
            }
        }
    }

    static void printPath(int src, int dest) {
        int minPrice = M;
        int minIndex = -1;
        for (int i = 0; i < 4; i++) {
            if (path[src][i * 5 + 4 + dest % 5] < minPrice) {
                minPrice = path[src][i * 5 + 4 + dest % 5];
                minIndex = i * 5 + 4 + dest % 5;
            }
        }
        if (minIndex != -1) {
            System.out.println("Cheapest price: Rs." + minPrice);
            System.out.println("The path is: ");
            int current = minIndex;
            int next;
            while (current != src) {
                next = -1;
                for (int i = 0; i < 16; i++) {
                    if (path[src][current] == path[src][i] + path[i][current]) {
                        next = i;
                        break;
                    }
                }
                System.out.println(city.get(current / 5) + " -> " + areas.get(current) + " -> " + city.get(next / 5));
                current = next;
            }
            System.out.println(city.get(src / 5) + " -> " + areas.get(src) + " -> " + city.get(dest / 5));
        } else {
            System.out.println("No path found between " + city.get(src / 5) + " and " + city.get(dest / 5));
        }
    }
}
