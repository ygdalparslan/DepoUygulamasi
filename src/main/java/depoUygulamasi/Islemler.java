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
import java.util.Scanner;

public class Islemler {

    UrunTanimlama urunTanimlama =new UrunTanimlama();

    Scanner scan = new Scanner(System.in);
    HashMap<Integer,UrunTanimlama> urunlistesi = new HashMap<>();


    public void start(){

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
            select =scan.nextInt();


            switch (select){
                case 1:
                   urunTanimlama(urunTanimlama.getUrunIsmi(), urunTanimlama.getUretici(), urunTanimlama.getBirim());
                    break;
                case 2:
                    //2 Ürün Listeleme
                    break;
                case 3:
                    //3 Ürün Girişi
                    break;
                case 4:
                    //4 Ürünü Rafa Koy
                    break;
                case 5:
                    //5 Ürün Çıkışı
                    break;
                case 0:
                    //0 Çıkış
                    break;
                default:
                    System.out.println("Yanlış Seçim yaptınız");
                    break;
            }

        }while (select!=0);
        System.out.println("Uygulamadan Çıkılıyor");

    }

    //urunTanimlama   ==>  urunun ismi, ureticisi ve birimi girilecek. id  alınacak.
    int urunIdBaslangıc =1000;
    public void urunTanimlama(String urunIsmi, String uretici, String birim){

        int urunId= urunIdBaslangıc++;
        System.out.println("Ürün Adını Giriniz");
        urunIsmi =scan.next();
        System.out.println("Üretici Giriniz");
        uretici = scan.next();
        System.out.println("Ürünün Dağıtım Birimini Giriniz");
        birim = scan.next();
        UrunTanimlama yeniUrun = new UrunTanimlama(urunIsmi,uretici,0,birim,null);
        urunlistesi.put(urunId, yeniUrun);
        urunListele();

    }

    //urunListele     ==> tanimlanan urunler listelenecek. urunun adeti ve raf numarasi tanimlama yapilmadiysa default deger gorunsun.

    public void urunListele(){
        System.out.println("Ürün ID\tÜrün İsmi\tÜretici\tÜrün Miktarı\tBirimi\tRaf");
        System.out.println("----------------------------------------------------------");

        for (Integer urunId: urunlistesi.keySet()){

            UrunTanimlama urunTanimlama = urunlistesi.get(urunId);

            System.out.println(urunId +"\t"+ urunTanimlama.getUrunIsmi()+"\t"+urunTanimlama.getUretici()+"\t"+urunTanimlama.getMiktar()
                    +"\t"+urunTanimlama.getRaf());

        }


    }

}
