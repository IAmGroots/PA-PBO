package Sistem_Pasar_Elektronik;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;

public abstract class Pengguna {
  protected String username;
  protected String password;
  protected String role;

  public Pengguna(String username, String password, String role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  // untuk inputan
  static InputStreamReader isr = new InputStreamReader(System.in);
  static BufferedReader input = new BufferedReader(isr);
  static String NORMAL = "\u001b[0m";
	static String RED = "\u001b[31m";
	static String GREEN = "\u001b[32m";

  // clearscreen hanya berlaku di vscode dan tidak berlaku di Netbeans
  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  // prosedur seperti "Press Enter To Continue...", jadi bisa dipakai di semua class turunan (Admin, Penjual, Pembeli)
  public void lanjutkan() throws IOException {
    System.out.println("");
    System.out.print("\tSilahkan tekan Enter untuk melanjutkan...");
    input.readLine();
  }

  static void lanjutkan(String kondisi, String kalimat) throws IOException {
		if (kondisi.equals("berhasil")) {
			System.out.println("");
			System.out.println("\t" + GREEN + kalimat + NORMAL);
			System.out.println("");
			System.out.print("\tSilahkan tekan Enter untuk melanjutkan...");
			input.readLine();
		}
		if (kondisi.equals("gagal")) {
			System.out.println("");
			System.out.println("\t" + RED + kalimat + NORMAL);
			System.out.println("");
			System.out.print("\tSilahkan tekan Enter untuk melanjutkan...");
			input.readLine();
		}
	}

  // untuk login seluruh pengguna
  static boolean login(ArrayList<Pembeli> ListPembeli, String username, String password) {
    for (Pembeli data : ListPembeli) {
      if (data.getUsername().equals(username) && data.getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }

  static boolean login(String username, ArrayList<Penjual> ListPenjual, String password) {
    for (Penjual data : ListPenjual) {
      if (data.getUsername().equals(username) && data.getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }

  static boolean login(String username, String password, ArrayList<Admin> ListAdmin) {
    for (Admin data : ListAdmin) {
      if (data.getUsername().equals(username) && data.getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }

  // untuk melihat riwayat pembelian/penjualan sesuai dengan rolenya
  public abstract void riwayat_transaksi() throws IOException;
}
