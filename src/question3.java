import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class question3 {
    static class Student {
        String name;
        int chances;

        public Student(String name, int chances) {
            this.name = name;
            this.chances = chances;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Masukkan jumlah antrian harus >= 5 atau <= 20 : ");
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            
            // CEK CONSTRAINT 1: 5 < line < 20
            // Jika n bernilai 5 ke bawah ATAU 20 ke atas, program langsung ditutup
            if (n < 5 || n > 20) {
                System.out.println("Error: Panjang antrean harus lebih dari 5 dan kurang dari 20.");
                scanner.close();
                return; // Langsung keluar dari program
            }
            
            System.out.println("Masukkan nama yang mengantri :");
            String[] names = new String[n];
            for (int i = 0; i < n; i++) {
                names[i] = scanner.next();
            }
            
            System.out.println("Masukkan jumlah antri tiap orang ");
            int[] chances = new int[n];
            for (int i = 0; i < n; i++) {
                chances[i] = scanner.nextInt();
                
                // CEK CONSTRAINT 2: 1 <= number of chances <= 10
                if (chances[i] < 1 || chances[i] > 10) {
                    System.out.println("Error: Jumlah chances untuk " + names[i] + " harus antara 1 sampai 10.");
                    scanner.close();
                    return; // Langsung keluar dari program
                }
            }
            
            // Inisialisasi Queue dan memasukkan semua mahasiswa ke dalam antrean
            Queue<Student> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                queue.add(new Student(names[i], chances[i]));
            }
            
            // Simulasi antrean berjalan
            while (!queue.isEmpty()) {
                // Keluarkan mahasiswa dari barisan paling depan
                Student current = queue.poll();
                
                // Kurangi kesempatannya
                current.chances--;
                
                // Cek sisa kesempatan
                if (current.chances > 0) {
                    System.out.println(current.name + " | Try Again | " + current.chances);
                    // Masukkan kembali ke barisan paling belakang
                    queue.add(current);
                } else {
                    System.out.println(current.name + " | Get Out | 0");
                }
            }
        }
        
        scanner.close();
    }
}