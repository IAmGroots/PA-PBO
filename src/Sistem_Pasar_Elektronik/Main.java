package Sistem_Pasar_Elektronik;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static ArrayList<Pembeli> ListPembeli = new ArrayList<>() {
		{
			add(new Pembeli("hadi", "058", "pembeli", "Hadi", "Jalan Jakarta"));
			add(new Pembeli("dhiya", "056", "pembeli", "Dhiya", "Jalan Samarinda"));
			add(new Pembeli("hafidz", "070", "pembeli", "Hafidz", "Jalan Samarinda"));
			add(new Pembeli("bagas", "071", "pembeli", "Bagas", "Jalan Samarinda"));
		}
	};

	public static ArrayList<Penjual> ListPenjual = new ArrayList<>() {
		{
			add(new Penjual("asep", "123", "penjual", "Toko Bunga", "Asep", "Tanaman"));
			add(new Penjual("budi", "321", "penjual", "Toko Buku", "Budi", "Buku"));
		}
	};

	public static ArrayList<Admin> ListAdmin = new ArrayList<>() {
		{
			add(new Admin("admin", "admin", "admin", "Admin"));
		}
	};

	public static ArrayList<Produk> ListProduk = new ArrayList<>() {
		{
			add(new Produk("asep", "Tanaman Anggrek", 50000, 10, 3));
			add(new Produk("budi", "Kamus", 20000, 15, 8));
			add(new Produk("asep", "Tanaman Melati", 30000, 20, 0));
			add(new Produk("budi", "Buku Tulis", 5000, 30, 10));
		}
	};

	public static ArrayList<ProdukKeranjang> ListKeranjang = new ArrayList<>() {
		{
			add(new ProdukKeranjang("asep", "Tanaman Melati", 30000, 20, 0, "hadi", 2, 60000));
			add(new ProdukKeranjang("budi", "Kamus", 20000, 15, 0, "dhiya", 1, 20000));
			add(new ProdukKeranjang("budi", "Buku Tulis", 5000, 30, 0, "hafidz", 10, 50000));
			add(new ProdukKeranjang("asep", "Tanaman Anggrek", 50000, 10, 0, "bagas", 2, 100000));
		}
	};

	public static ArrayList<Transaksi> ListTransaksi = new ArrayList<>() {
		{
			add(new Transaksi("hadi", "asep", "Tanaman Anggrek", 50000, 3, 150000, "29 Apr 2023 08:08 WITA"));
			add(new Transaksi("dhiya", "asep", "Tanaman Melati", 30000, 3, 90000, "29 Apr 2023 08:08 WITA"));
			add(new Transaksi("hafidz", "budi", "Kamus", 20000, 8, 160000, "29 Apr 2023 09:09 WITA"));
			add(new Transaksi("bagas", "budi", "Buku Tulis", 5000, 10, 50000, "29 Apr 2023 10:10 WITA"));
		}
	};

	static InputStreamReader isr = new InputStreamReader(System.in);
	static BufferedReader input = new BufferedReader(isr);

	static String NORMAL = "\u001b[0m";
	static String RED = "\u001b[31m";
	static String GREEN = "\u001b[32m";

	static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	static void lanjutkan() throws IOException {
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

	static void menu_pembeli(Pembeli pembeli) throws IOException {
		boolean loop = true;
		while (loop) {
			try {
				clearScreen();
				System.out.println("\tSelamat Datang " + pembeli.getNama_pembeli());
				System.out.println("\t[1] Lihat Produk");
				System.out.println("\t[2] Keranjang Belanja");
				System.out.println("\t[3] Pesan Produk");
				System.out.println("\t[4] Ubah Pesanan");
				System.out.println("\t[5] Hapus Pesanan");
				System.out.println("\t[6] Pembayaran");
				System.out.println("\t[7] Riwayat Transaksi");
				System.out.println("\t[8] Keluar");
				System.out.println("");
				System.out.print("\tSilahkan Pilih : ");
				int pilihan = Integer.parseInt(input.readLine());

				if (pilihan == 1) {
					pembeli.lihat_produk();
					lanjutkan();
				}
				else if (pilihan == 2) {
					pembeli.lihat_keranjang();
					lanjutkan();
				}
				else if (pilihan == 3) {
					pembeli.tambah_produk();
				}
				else if (pilihan == 4) {
					pembeli.ubah_produk();
				}
				else if (pilihan == 5) {
					pembeli.hapus_produk();
				}
				else if (pilihan == 6) {
					pembeli.pembayaran();
				}
				else if (pilihan == 7) {
					pembeli.riwayat_transaksi();
				}
				else if (pilihan == 8) {
					lanjutkan("berhasil", "Logout Berhasil");
					loop = false;
				}
				else {
					lanjutkan("gagal", "Pilihan tidak tersedia");
				}
			} catch (NumberFormatException nfe) {
				lanjutkan("gagal", "Pilihan harus Angka");
			}
		}
	}

	static void menu_penjual(Penjual penjual) throws IOException {
		boolean loop = true;
		while (loop) {
			try {
				clearScreen();
				System.out.println("\tSelamat Datang " + penjual.getNama_penjual());
				System.out.println("\t[1] Lihat Produk");
				System.out.println("\t[2] Tambah Produk");
				System.out.println("\t[3] Ubah Produk");
				System.out.println("\t[4] Hapus Produk");
				System.out.println("\t[5] Riwayat Penjualan");
				System.out.println("\t[6] Keluar");
				System.out.println("");
				System.out.print("\tSilahkan Pilih : ");
				int pilihan = Integer.parseInt(input.readLine());

				if (pilihan == 1) {
					penjual.lihat_produk();
					lanjutkan();
				}
				else if (pilihan == 2) {
					penjual.tambah_produk();
				}
				else if (pilihan == 3) {
					penjual.ubah_produk();
				}
				else if (pilihan == 4) {
					penjual.hapus_produk();
				}
				else if (pilihan == 5) {
					penjual.riwayat_transaksi();
				}
				else if (pilihan == 6) {
					lanjutkan("berhasil", "Logout Berhasil");
					loop = false;
				}
				else {
					lanjutkan("gagal", "Pilihan tidak tersedia");
				}
			} catch (NumberFormatException nfe) {
				lanjutkan("gagal", "Pilihan harus Angka");
			}
		}
	}

	static void menu_admin(Admin admin) throws IOException {
		boolean loop = true;
		while (loop) {
			try {
				clearScreen();
				System.out.println("\tSelamat Datang " + admin.getNama_admin());
				System.out.println("\t[1] List Pembeli");
				System.out.println("\t[2] List Penjual");
				System.out.println("\t[3] List Produk");
				System.out.println("\t[4] Riwayat Transaksi");
				System.out.println("\t[5] Keluar");
				System.out.println("");
				System.out.print("\tSilahkan Pilih : ");
				int pilihan = Integer.parseInt(input.readLine());

				if (pilihan == 1) {
					admin.lihat_pembeli();
				}
				else if (pilihan == 2) {
					admin.lihat_penjual();
				}
				else if (pilihan == 3) {
					admin.lihat_produk();
				}
				else if (pilihan == 4) {
					admin.riwayat_transaksi();
				}
				else if (pilihan == 5) {
					lanjutkan("berhasil", "Logout Berhasil");
					loop = false;
				}
				else {
					lanjutkan("gagal", "Pilihan tidak tersedia");
				}
			} catch (NumberFormatException nfe) {
				lanjutkan("gagal", "Pilihan harus Angka");
			}
		}
	}

	// Program Utama
	public static void main(String[] args) throws IOException {
		// ============================================================================================
		// UNTUK UJI COBA FITUR ADMIN - PEMBELI - PENJUAL
		// ============================================================================================
		// Admin p0 = new Admin("admin", "321", "admin", "Feby");
		// p0.lihat_pembeli();
		// p0.lihat_penjual();
		// p0.lihat_produk();
		// p0.lihat_transaksi();
		
		// Pembeli p1 = new Pembeli("asep", "123", "pembeli", "Gunawan", "Jalan Greenland");
		// Pembeli.daftar();
		// p1.lihat_produk();
		// p1.pesan_produk(p1);
		// p1.lihat_keranjang(p1);
		// p1.ubah_produk(p1);
		// p1.hapus_produk(p1);
		// p1.pembayaran(p1);
		// p1.lihat_keranjang(p1);
		// p1.lihat_produk();
		// p1.riwayat_transaksi(p1);

		// Penjual p2 = new Penjual("cahya", "123", "penjual", "Toko Bunga", "Cahya", 0, "Tanaman");
		// Penjual.daftar();
		// p2.lihat_produk();
		// p2.tambah_produk(p2);
		// p2.ubah_produk(p2);
		// p2.hapus_produk(p2);
		// p2.lihat_produk();
		// p2.riwayat_penjualan(p2);
		// ============================================================================================

		boolean loop = true;
		while (loop) {
			try {
				clearScreen();
				System.out.println("\tSelamat Datang");
				System.out.println("\t[1] Masuk");
				System.out.println("\t[2] Daftar sebagai Pembeli");
				System.out.println("\t[3] Daftar sebagai Penjual");
				System.out.println("\t[4] Keluar");
				System.out.println("");
				System.out.print("\tSilahkan Pilih : ");
				int pilihan = Integer.parseInt(input.readLine());
				if (pilihan == 1) {
					System.out.println("");
					System.out.print("\tUsername : ");
					String username = input.readLine();
					System.out.print("\tPassword : ");
					String password = input.readLine();

					Pembeli pembeli_aktif = null;
					Penjual penjual_aktif = null;
					Admin admin_aktif = null;

					if (Pengguna.login(ListPembeli, username, password)) {
						for (Pembeli data : ListPembeli) {
							if (data.getUsername().equals(username)) {
								pembeli_aktif = data;
							}
						}
						lanjutkan("berhasil", "Login Berhasil");
						Pembeli pembeli = new Pembeli(pembeli_aktif.getUsername(), pembeli_aktif.getPassword(), pembeli_aktif.getRole(), pembeli_aktif.getNama_pembeli(), pembeli_aktif.getAlamat());
						menu_pembeli(pembeli);

					} else if (Pengguna.login(username, ListPenjual, password)) {
						for (Penjual data : ListPenjual) {
							if (data.getUsername().equals(username)) {
								penjual_aktif = data;
							}
						}
						lanjutkan("berhasil", "Login Berhasil");
						Penjual penjual = new Penjual(penjual_aktif.getUsername(), penjual_aktif.getPassword(), penjual_aktif.getRole(), penjual_aktif.getNama_toko(), penjual_aktif.getNama_penjual(), penjual_aktif.getKategori());
						menu_penjual(penjual);

					} else if (Pengguna.login(username, password, ListAdmin)) {
						for (Admin data : ListAdmin) {
							if (data.getUsername().equals(username)) {
								admin_aktif = data;
							}
						}
						lanjutkan("berhasil", "Login Berhasil");
						Admin admin = new Admin(admin_aktif.getUsername(), admin_aktif.getPassword(), admin_aktif.getRole(), admin_aktif.getNama_admin());
						menu_admin(admin);

					} else {
						lanjutkan("gagal", "Username dan Password tidak terdaftar");
					}
				}
				else if (pilihan == 2) {
					Pembeli.daftar();
				}
				else if (pilihan == 3) {
					Penjual.daftar();
				}
				else if (pilihan == 4) {
					System.out.println("");
					System.out.println("\tThank You");
					System.out.println("");
					loop = false;
				}
				else {
					lanjutkan("gagal", "Pilihan tidak tersedia");
				}
			} catch (NumberFormatException nfe) {
				lanjutkan("gagal", "Pilihan harus Angka");
			}
		}
	}
}