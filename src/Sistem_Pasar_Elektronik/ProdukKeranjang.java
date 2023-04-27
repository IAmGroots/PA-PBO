package Sistem_Pasar_Elektronik;

public class ProdukKeranjang extends Produk {
  private String username_pembeli;
  private int jumlah_produk;
  private int total_harga;

  public ProdukKeranjang(String username_penjual, String nama_produk, int harga_produk, int stok_produk, int jumlah_terjual, String username_pembeli, int jumlah_produk, int total_harga) {
    super(username_penjual, nama_produk, harga_produk, stok_produk, jumlah_terjual);
    this.username_pembeli = username_pembeli;
    this.jumlah_produk = jumlah_produk;
    this.total_harga = total_harga;
  }

  public String getUsername_pembeli() {
    return this.username_pembeli;
  }

  public void setUsername_pembeli(String username_pembeli) {
    this.username_pembeli = username_pembeli;
  }

  public int getJumlah_produk() {
    return this.jumlah_produk;
  }

  public void setJumlah_produk(int jumlah_produk) {
    this.jumlah_produk = jumlah_produk;
  }

  public int getTotal_harga() {
    return this.total_harga;
  }

  public void setTotal_harga(int total_harga) {
    this.total_harga = total_harga;
  }
}
