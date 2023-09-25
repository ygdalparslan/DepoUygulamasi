package depoUygulamasi;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
//Uygulamanın iş mantığının bulunduğu Depo işlemlerinin gerçekleştirildiği ana sınıftır.
public class Islemler {

    UrunTanimlama urunTanimlama = new UrunTanimlama(); //UrunTanimlama sınıfından bir nesne oluşturuldu.
    int urunId = 1000;// Ürünlere otomatik olarak atanacak başlangıç ID'si.
    Scanner scan = new Scanner(System.in);

    //Ürünlerin listesi bu HashMap içinde saklanır. Ürün ID'si ile eşleştirilmiş UrunTanimlama nesneleri tutulur.
    HashMap<Integer, UrunTanimlama> urunlistesi = new HashMap<>();


    //Kullanıcıya işlem seçeneklerini sunar ve seçimi işler. do-while ie menü sunuldu
    public void start() {

        //Kullanıcı 0 seçip çıkış yapana kadar döngünün devamı için oluşturuldu
        int select;
        do {
            System.out.println("---DEPO UYGULAMASI--");
            System.out.println("Lütfen yapmak istediğiniz işlemi seçiniz" +
                    "\n 1 Ürün Tanımlama" +
                    "\n 2 Ürün Listeleme" +
                    "\n 3 Ürün Girişi" +
                    "\n 4 Ürünü Rafa Koy" +
                    "\n 5 Ürün Çıkışı" +
                    "\n 0 Çıkış\n");

            try {
                select = scan.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Geçersiz giriş. Lütfen bir rakam girin.");
                scan.next(); // Geçersiz girişi tüket
                start();
                return;
            }

            //Kullanıcının seçtiği işleme göre ilgili method çağrılır.
            switch (select) {
                case 1:
                    urunTanimlama(urunTanimlama.getUrunIsmi(), urunTanimlama.getUretici(), urunTanimlama.getBirim());
                    break;
                case 2:
                    urunListele();
                    break;
                case 3:
                    urunGirisi(urunId);
                    break;
                case 4:
                    urunuRafaKoy(urunId, urunTanimlama.getRaf());
                    break;
                case 5:
                    urunCikisi(urunId);
                    break;
                case 0:
                    System.out.println("Uygulamadan Çıkılıyor");
                    break;
                default:
                    System.out.println("Yanlış Seçim yaptınız");
                    break;
            }
        } while (select != 0);
    }


    //Kullanıcıdan alınan bilgilerle yeni bir ürün tanımlar ve bu ürünü urunlistesi HashMap'ine ekler.
    public void urunTanimlama(String urunIsmi, String uretici, String birim) {

        urunId++; //Her urun icin benzersiz bir ID otomatik olarak atanır
        System.out.println("Ürün Adını Giriniz");
        urunIsmi = scan.next().toUpperCase();//standart listeleme için tümü büyük harf yapıldı
        System.out.println("Üretici Giriniz");
        uretici = scan.next().toUpperCase();
        System.out.println("Ürünün Dağıtım Birimini Giriniz");
        birim = scan.next().toUpperCase();
        UrunTanimlama yeniUrun = new UrunTanimlama(urunIsmi, uretici, 0, birim, null);
        urunlistesi.put(urunId, yeniUrun);
        urunListele();//urunListele() metodu çağrılarak güncellenmiş ürün listesi gösterilir.
    }


    //Depodaki ürünleri listelemek için kullanılır.
    public void urunListele() {

        //ürün bilgilerini düzenli bir şekilde göstermek için printf kullanıldı.
        System.out.printf("\n%-10s %-15s %-20s %-10s %-10s %-10s\n", "Ürün ID", "Ürün İsmi", "Üretici", "Miktarı", "Birimi", "Raf");
        System.out.println("--------------------------------------------------------------------------");

        //urunlistesi HashMap'ini dolaşarak her ürünün bilgilerini alır ve ekrana yazdırır.
        for (Integer urunId : urunlistesi.keySet()) {
            UrunTanimlama urunTanimlama = urunlistesi.get(urunId);
            System.out.printf("%-10d %-15s %-20s %-10d %-10s %-10s\n", urunId, urunTanimlama.getUrunIsmi(), urunTanimlama.getUretici(),
                    urunTanimlama.getMiktar(), urunTanimlama.getBirim(), urunTanimlama.getRaf());
        }
        System.out.println();
    }


    //Kullanıcıdan ürün ID'si alarak, belirtilen ürüne miktar ekler.
    public void urunGirisi(int urunId) {

        if (urunlistesi.get(urunId) == null) {
            System.out.println("Tanımlı ürün yok");//Tanımlı ürün olmadığı zaman hata mesajı verilir.

        } else {
            urunListele();
            System.out.println("Lütfen Giriş yapmak istediğiniz ürünün ID'sini giriniz");
            try {
                urunId = scan.nextInt();
                UrunTanimlama urunTanimlama = urunlistesi.get(urunId);

                if (urunTanimlama != null) {
                    //Eğer ürün mevcutsa, kullanıcıdan giriş miktarını alınır ve depoya eklenir.
                    System.out.println("Lütfen Giriş yapmak istediğiniz ürünün Miktarını giriniz");
                    int miktar = scan.nextInt();

                    //Miktarın 0'dan büyük olması kontrol edilir, aksi takdirde hata mesajı verilir.
                    if (miktar > 0) {
                        int yeniMiktar = urunTanimlama.getMiktar() + miktar;
                        urunTanimlama.setMiktar(yeniMiktar);

                        System.out.print("\nÜrün ID: " + urunId + "  ");
                        System.out.println("Miktar = " + yeniMiktar + " " + urunTanimlama.getBirim() + "\n");
                        urunListele();//güncellenmiş ürün listesini gösterir.

                    } else {
                        System.out.println("Ürün miktarı 0 ya da eksi olamaz");
                    }

                    System.out.println();

                } else {
                    System.err.println("Hatalı ID girdiniz");
                    urunGirisi(urunId);
                }

            } catch (java.util.InputMismatchException e) {
                System.err.println("Hatalı ID numarası girdiniz. ID Numarası rakamlardan oluşmaktadır.");
                scan.next(); // Hatalı girişi temizle
                urunGirisi(urunId); // Metodu tekrar çağır

            }
        }

    }


    //Kullanıcıdan bir ürün ID'si ve bir raf numarası alarak, ürünü ilgili rafa yerleştirir.
    public void urunuRafaKoy(int urunId, String raf) {

        if (urunlistesi.get(urunId) == null) {
            System.out.println("Tanımlı ürün yok");

        } else {
            urunListele();
            System.out.println("Lütfen Rafa Koymak istediğiniz ürünün ID'sini giriniz");
            try {
                urunId = scan.nextInt();
                UrunTanimlama urunTanimlama = urunlistesi.get(urunId);

                //Bu ID'ye sahip ürünün varlığı kontrol edilir.
                if (urunTanimlama != null) {
                    System.out.println("Lütfen Ürünü yerleştirmek istediğiniz Raf Numarasını giriniz");
                    raf = scan.next();

                    urunTanimlama.setRaf("Raf- " + raf);//Eğer ürün mevcutsa, kullanıcının belirttiği raf numarasını ilgili ürüne ekler.

                    System.out.print("\nÜrün ID: " + urunId + "  ");
                    System.out.println("Raf = " + raf + "\n");
                    urunListele();//güncellenmiş ürün listesini gösterir.
                } else {
                    System.err.println("Hatalı ID girdiniz");
                    urunuRafaKoy(urunId, raf);
                }

            } catch (java.util.InputMismatchException e) {
                System.err.println("Hatalı ID numarası girdiniz. ID Numarası rakamlardan oluşmaktadır.");
                scan.next(); // Hatalı girişi temizle
                System.out.println();
                urunuRafaKoy(urunId, raf); // Metodu tekrar çağır

            }
        }
    }


    //Kullanıcıdan bir ürün ID'si alarak, belirtilen üründen miktar çıkarır.
    public void urunCikisi(int urunId) {

        //Bu ID'ye sahip ürünün varlığını kontrol eder.
        if (urunlistesi.get(urunId) == null) {
            System.out.println("Tanımlı ürün yok");

        } else {
            urunListele();
            System.out.println("Lütfen Çıkış yapmak istediğiniz ürünün ID'sini giriniz");

            try {
                urunId = scan.nextInt();
                UrunTanimlama urunTanimlama = urunlistesi.get(urunId);

                if (urunTanimlama != null) {
                    System.out.println("Lütfen Çıkış yapmak istediğiniz ürünün Miktarını giriniz");
                    int miktar = scan.nextInt();//Eğer ürün mevcutsa, kullanıcıdan çıkış miktarını alır.

                    //Çıkış yapılacak miktarın depodaki miktardan fazla olup olmadığı kontrol edilir.
                    if (miktar > urunTanimlama.getMiktar()) {
                        System.out.println("Çıkış için yeterli miktar yok" +
                                "\nMevcut Miktar: " + urunTanimlama.getMiktar());

                    } else if (miktar > 0) {
                        int yeniMiktar = urunTanimlama.getMiktar() - miktar;
                        urunTanimlama.setMiktar(yeniMiktar);//yeterli miktar varsa, çıkış işlemi gerçekleştirilir.
                        System.out.print("\nÜrün ID: " + urunId + "  ");
                        System.out.println("Ürün Çıkış = " + miktar + " " + urunTanimlama.getBirim()
                                + " (Kalan Miktar: " + yeniMiktar + " "
                                + urunTanimlama.getBirim() + " "
                                + urunTanimlama.getUrunIsmi().toLowerCase() + ")\n");
                        urunListele();//güncellenmiş ürün listesini gösterir.
                    } else {
                        System.out.println("Ürün miktarı 0 ya da eksi olamaz");
                    }

                } else {
                    System.err.println("Hatalı ID girdiniz");
                    urunCikisi(urunId);
                }
            } catch (java.util.InputMismatchException e) {
                System.err.println("Hatalı ID numarası girdiniz. ID Numarası rakamlardan oluşmaktadır.");
                scan.next(); // Hatalı girişi temizle
                urunCikisi(urunId); // Metodu tekrar çağır

            }
        }
    }


}
