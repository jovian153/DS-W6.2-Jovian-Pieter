import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

// BLOK 1: Membuat "Bungkusan" Data (Class Surat)
class Surat implements Comparable<Surat> {
    String nama;
    int durasi;
    int prioritas;

    public Surat(String nama, int durasi, int prioritas) {
        this.nama = nama;
        this.durasi = durasi;
        this.prioritas = prioritas;
    }

    // Aturan pengurutan: Prioritas angka terkecil didahulukan
    @Override
    public int compareTo(Surat suratLain) {
        return this.prioritas - suratLain.prioritas;
    }
}

public class question1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Surat> antreanUtama = new PriorityQueue<>();

        System.out.println("=== SISTEM PENJADWALAN SURAT ===");

        // --- BLOK 2: Meminta Jumlah Surat dengan Validasi Ketat ---
        int batasSistem = 0;
        while (true) {
            System.out.print("Masukkan jumlah surat yang akan dikirim (1-100): ");
            try {
                batasSistem = Integer.parseInt(sc.nextLine());
                
                // Cek batas 1 hingga 100
                if (batasSistem < 1 || batasSistem > 100) {
                    System.out.println("ERROR: Jumlah surat tidak boleh kurang dari 1 dan lebih dari 100! Silakan input ulang.\n");
                } else {
                    break; // Jika valid, keluar dari loop penjaga ini
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Harap masukkan angka bulat yang valid!\n");
            }
        }
        
        System.out.println("\nSistem akan memproses " + batasSistem + " surat.");
        
        // --- BLOK 3: Proses Input Data ---
        int jumlahSurat = 0;

        // Loop berjalan persis sebanyak batasSistem yang diinputkan user
        while (jumlahSurat < batasSistem) {
            System.out.println("\nMasukkan surat ke-" + (jumlahSurat + 1) + " (Format: Nama Durasi Prioritas)");
            System.out.print("Input: ");
            
            String input = sc.nextLine();
            String[] arrInput = input.split(" "); 

            // Validasi format (harus 3 kata)
            if (arrInput.length != 3) {
                System.out.println("ERROR: Format salah! Harus terdiri dari 3 bagian (Nama Durasi Prioritas). Silakan input ulang.");
                continue;
            }
            
            String nama = arrInput[0];
            int durasi = 0;
            int prioritas = 0;

            // Validasi tipe data (harus angka)
            try {
                durasi = Integer.parseInt(arrInput[1]);
                prioritas = Integer.parseInt(arrInput[2]);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Durasi dan Prioritas harus berupa angka! Silakan input ulang.");
                continue;
            }
            
            // Validasi Constraint Durasi (1 hingga 100)
            if (durasi < 1 || durasi > 100) {
                System.out.println("ERROR: Durasi harus antara 1 hingga 100! Silakan input ulang.");
                continue;
            }
            
            // Validasi Constraint Prioritas (0 hingga 100)
            if (prioritas < 0 || prioritas > 100) {
                System.out.println("ERROR: Prioritas harus antara 0 hingga 100! Silakan input ulang.");
                continue;
            }
            
            // Jika lolos semua validasi, masukkan ke antrean dan tambah counter
            antreanUtama.add(new Surat(nama, durasi, prioritas));
            jumlahSurat++;
        }

        // --- BLOK 4: Eksekusi Antrean dan Format Output ---
        // --- BLOK 4: Eksekusi Antrean dan Format Output ---
        System.out.println("\n=== PROSES PENGIRIMAN DIMULAI ===");
        int waktuSaatIni = 0;
        List<String> suratTerkirim = new ArrayList<>(); // Wadah untuk status 'sent'

        // 1. Cetak status awal (Waktu 0, semua masih pending)
        System.out.print("0 pending ");
        PriorityQueue<Surat> salinanAwal = new PriorityQueue<>(antreanUtama);
        while (!salinanAwal.isEmpty()) {
            System.out.print(salinanAwal.poll().nama + " ");
        }
        System.out.println("| queued | sent ");

        // 2. Loop utama proses pengiriman
        while(!antreanUtama.isEmpty()) {
            Surat suratDiproses = antreanUtama.poll(); 
            
            // CETAK STATUS SAAT INI (Surat baru masuk queued)
            System.out.print(waktuSaatIni + " pending ");
            
            PriorityQueue<Surat> salinanPending = new PriorityQueue<>(antreanUtama);
            while (!salinanPending.isEmpty()) {
                System.out.print(salinanPending.poll().nama + " ");
            }
            
            System.out.print("| queued " + suratDiproses.nama + " | sent ");
            
            for (String s : suratTerkirim) {
                System.out.print(s + " ");
            }
            System.out.println(); // Pindah baris
            
            // WAKTU BERJALAN (Setelah dicetak, tambahkan waktu dan pindahkan ke sent)
            waktuSaatIni += suratDiproses.durasi;
            suratTerkirim.add(suratDiproses.nama);
        }
        
        // 3. CETAK STATUS FINAL (Semua antrean kosong, semua surat di sent)
        System.out.print(waktuSaatIni + " pending | queued | sent ");
        for (String s : suratTerkirim) {
            System.out.print(s + " ");
        }
        
        System.out.println("\n\nOnce the queue is empty, the postmen are to rest."); 
        sc.close();
    }
}