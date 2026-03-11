import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class question4 {
    static class Visitor implements Comparable<Visitor> {
        String name;
        int money;

        public Visitor(String name, int money) {
            this.name = name;
            this.money = money;
        }
        @Override
        public int compareTo(Visitor other) {
            return Integer.compare(other.money, this.money);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan jumlah pengunjung: ");
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            scanner.nextLine();
            if (n <= 1 || n >= 1000) {
                System.out.println("Error: Jumlah pengunjung (n) harus > 1 dan < 1000.");
                scanner.close();
                return; 
            }
            System.out.println("Masukkan Nama pengunjung contoh (Mike, Phoenix, Ellie, John, Regina) :");
            String nameLine = scanner.nextLine();
            
            System.out.println("Masukkan jumlah uang tiap pengunjung contoh (3, 2, 2, 2, 10) :");
            String moneyLine = scanner.nextLine();

            String[] names = nameLine.split(",\\s*");
            String[] moneyStrs = moneyLine.split(",\\s*");

            if (names.length != n || moneyStrs.length != n) {
                System.out.println("Error: Jumlah nama atau jumlah data uang tidak sesuai dengan nilai n.");
                scanner.close();
                return;
            }
            PriorityQueue<Visitor> pq = new PriorityQueue<>();

            for (int i = 0; i < n; i++) {
                String currentName = names[i].trim();
                int currentMoney = Integer.parseInt(moneyStrs[i].trim());

                if (currentMoney <= 1 || currentMoney >= 100) {
                    System.out.println("Error: Uang milik " + currentName + " harus > 1 dan < 100.");
                    scanner.close();
                    return;
                }
                if (currentName.equalsIgnoreCase("Jeff")) {
                    continue;
                }
                pq.add(new Visitor(currentName, currentMoney));
            }
            List<String> result = new ArrayList<>();
            while (!pq.isEmpty()) {
                result.add(pq.poll().name);
            }
            System.out.println(result);
        }
        scanner.close();
    }
}