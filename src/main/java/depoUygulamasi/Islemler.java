package depoUygulamasi;
/*
2-) methodlar olusturacagiz.
urunTanimlama   ==>  urunun ismi, ureticisi ve birimi girilecek. id  alınacak.
urunListele     ==> tanimlanan urunler listelenecek. urunun adeti ve raf numarasi tanimlama yapilmadiysa default deger gorunsun.
urunGirisi      ==> giris yapmak istedigimiz urnunun id numarasi ile girecegiz.
urunuRafaKoy    ==> listeden urunu sececegiz ve id numarasina gore urunu rafa koyacagiz.
urunCikisi      ==> listeden urunu sececegiz ve urunun cikis yapcagiz. burada urun listesinden sadece miktarda degisiklik yapilacak.
urun adedi 0 dan az olamaz. 0 olunca urun tanimlamasi silinmesin. sadece miktari 0 olsun.
===> yaptigimiz tum degisiklikler listede de gorunsun.

 */


import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Islemler {

    UrunTanimlama urunTanimlama = new UrunTanimlama();

    public static int urunId = 1000;

    Scanner scan = new Scanner(System.in);
    HashMap<Integer, UrunTanimlama> urunlistesi = new HashMap<>();


    //2-) methodlar olusturacagiz.
    public void start() {

        int select;

        do {
            System.out.println("---DEPO UYGULAMASI--");
            System.out.println("Lütfen yapmak istediğiniz işlemi seçiniz" +
                    "\n 1 Ürün Tanımlama" +
                    "\n 2 Ürün Listeleme" +
                    "\n 3 Ürün Girişi" +
                    "\n 4 Ürünü Rafa Koy" +
                    "\n 5 Ürün Çıkışı" +
                    "\n 0 Çıkış");
            System.out.println();
            System.out.println();

            try {
                select = scan.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Geçersiz giriş. Lütfen bir rakam girin.");
                scan.next(); // Geçersiz girişi tüket
                select = -1;
                continue;    // Döngüyü tekrar başlat
            }
            // String secim = scan.next().toUpperCase(Locale.ROOT);
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


    //urunTanimlama   ==>  urunun ismi, ureticisi ve birimi girilecek. id  alınacak.


    public void urunTanimlama(String urunIsmi, String uretici, String birim) {

        urunId++;
        System.out.println("Ürün Adını Giriniz");
        urunIsmi = scan.next().toUpperCase();
        System.out.println("Üretici Giriniz");
        uretici = scan.next().toUpperCase();
        System.out.println("Ürünün Dağıtım Birimini Giriniz");
        birim = scan.next().toLowerCase();
        UrunTanimlama yeniUrun = new UrunTanimlama(urunIsmi, uretici, 0, birim, null);
        urunlistesi.put(urunId, yeniUrun);
    }

    //urunListele     ==> tanimlanan urunler listelenecek. urunun adeti ve raf numarasi tanimlama yapilmadiysa default deger gorunsun.

    public void urunListele() {

        System.out.printf("%-10s %-15s %-15s %-10s %-10s %-10s\n", "Ürün ID", "Ürün İsmi", "Üretici", "Miktarı", "Birimi", "Raf");
        System.out.println("--------------------------------------------------------------------------");

        for (Integer urunId : urunlistesi.keySet()) {

            UrunTanimlama urunTanimlama = urunlistesi.get(urunId);

            System.out.printf("%-10d %-15s %-15s %-10d %-10s %-10s\n", urunId, urunTanimlama.getUrunIsmi(), urunTanimlama.getUretici(),
                    urunTanimlama.getMiktar(), urunTanimlama.getBirim(), urunTanimlama.getRaf());
        }
        System.out.println();
        System.out.println();

    }


    //urunGirisi      ==> giris yapmak istedigimiz urnunun id numarasi ile girecegiz.

    public void urunGirisi(int urunId) {

        if (urunlistesi.get(urunId) == null) {
            System.out.println("tanımlı ürün yok");
            //scan.next(); // Hatalı girişi temizle
            //start();
        } else {
            urunListele();
            System.out.println("Lütfen Giriş yapmak istediğiniz ürünün ID'sini giriniz");

            try {
                urunId = scan.nextInt();
                UrunTanimlama urunTanimlama = urunlistesi.get(urunId);

                if (urunTanimlama != null) {
                    System.out.println("Lütfen Giriş yapmak istediğiniz ürünün Miktarını giriniz");
                    int miktar = scan.nextInt();

                    if (miktar > 0) {
                        int yeniMiktar = urunTanimlama.getMiktar() + miktar;
                        urunTanimlama.setMiktar(yeniMiktar);


                        System.out.print("\nÜrün ID: " +urunId+"  ");
                        System.out.println("Miktar = " + yeniMiktar +" "+urunTanimlama.getBirim()+"\n");
                        urunListele();

                    } else {
                        System.out.println("Ürün miktarı 0 ya da eksi olamaz");
                    }

                    System.out.println();

                } else {
                    System.err.println("Hatalı ID girdizi");
                    //scan.next(); // Hatalı girişi temizle
                    urunGirisi(urunId);
                }

            } catch (java.util.InputMismatchException e) {
                System.err.println("hatalı giriş");
                scan.next(); // Hatalı girişi temizle
                urunGirisi(urunId); // Metodu tekrar çağır
                return;
            }
        }

    }

    //urunuRafaKoy    ==> listeden urunu sececegiz ve id numarasina gore urunu rafa koyacagiz.

    public void urunuRafaKoy(int urunId, String raf) {
        urunListele();
        System.out.println("Lütfen Rafa Koymak istediğiniz ürünün ID'sini giriniz");
        try {
            urunId = scan.nextInt();
            UrunTanimlama urunTanimlama = urunlistesi.get(urunId);

            if (urunTanimlama != null) {
                System.out.println("Lütfen Ürünü yerleştirmek istediğiniz Raf Numarasını giriniz");
                raf = scan.next();
                urunTanimlama.setRaf("Raf- " + raf);

                System.out.print("\nÜrün ID: " +urunId+"  ");
                System.out.println("Raf = " + raf +"\n");
                urunListele();
            } else {
                System.err.println("Girdiğiniz ID ile eşleşen ürün bulunamadı");
                System.out.println();
                urunuRafaKoy(urunId, raf);
            }

        } catch (java.util.InputMismatchException e) {
            System.err.println("Hatalı ID numarası girdiniz. ID Numarası rakamlardan oluşmaktadır.");
            scan.next(); // Hatalı girişi temizle
            System.out.println();
            urunuRafaKoy(urunId, raf); // Metodu tekrar çağır
            return;
        }


    }

    //urunCikisi      ==> listeden urunu sececegiz ve urunun cikis yapcagiz. burada urun listesinden sadece miktarda degisiklik yapilacak.

    public void urunCikisi(int urunId) {
        urunListele();
        System.out.println("Lütfen Çıkış yapmak istediğiniz ürünün ID'sini giriniz");

        try {
            urunId = scan.nextInt();
            UrunTanimlama urunTanimlama = urunlistesi.get(urunId);
            if (urunTanimlama != null) {
                System.out.println("Lütfen Çıkış yapmak istediğiniz ürünün Miktarını giriniz");
                int miktar = scan.nextInt();

                if (miktar > urunTanimlama.getMiktar()) {
                    System.out.println("Çıkış için yeterli miktar yok" +
                            "\nMevcut Miktar: " + urunTanimlama.getMiktar());

                } else if (miktar > 0) {
                    int yeniMiktar = urunTanimlama.getMiktar() - miktar;
                    urunTanimlama.setMiktar(yeniMiktar);
                    System.out.print("\nÜrün ID: " +urunId+"  ");
                    System.out.println("Ürün Çıkış = " + miktar +" "+urunTanimlama.getBirim()+" (Kalan Miktar: "+yeniMiktar+" "+urunTanimlama.getBirim()+" "+urunTanimlama.getUrunIsmi().toLowerCase()+")\n");
                    urunListele();
                } else {
                    System.out.println("Ürün miktarı 0 ya da eksi olamaz");
                }

            } else {
                System.err.println("Hatalı ID girdizi");
                urunCikisi(urunId);
            }

        } catch (java.util.InputMismatchException e) {
            System.err.println("Hatalı giriş");
            scan.next(); // Hatalı girişi temizle
            urunCikisi(urunId); // Metodu tekrar çağır
            return;
        }


    }


}
