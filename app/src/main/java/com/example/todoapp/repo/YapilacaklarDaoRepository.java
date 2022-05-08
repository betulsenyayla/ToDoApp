package com.example.todoapp.repo;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.todoapp.entity.Yapilacaklar;
import com.example.todoapp.room.Veritabani;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class YapilacaklarDaoRepository {
    private MutableLiveData<List<Yapilacaklar>> yapilacaklarListesi;
    private Veritabani vt;

    public YapilacaklarDaoRepository(Application application) {
        yapilacaklarListesi = new MutableLiveData();
        vt = Veritabani.veritabaniErisim(application);
    }

    public MutableLiveData<List<Yapilacaklar>> yapilacaklariGetir() {
        return yapilacaklarListesi;
    }

    public void yapilacakEkle(String yapilacak_is) {
        Yapilacaklar yeniYapilacak = new Yapilacaklar(0, yapilacak_is);
        vt.yapilacaklarDao().yapilacakEkleme(yeniYapilacak).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                Log.e("Yapılacak İş Kayıt","Başarılı");
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

    public void yapilacakGuncelle(int yapilacak_id, String yapilacak_is) {
        Yapilacaklar guncellenenYapilacakIs = new Yapilacaklar(yapilacak_id, yapilacak_is);
        vt.yapilacaklarDao().yapilacakGuncelle(guncellenenYapilacakIs).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                Log.e("Yapılacak İş Güncelleme","Başarılı");
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

    public void yapilacakAra(String aramaKelimesi) {
        Log.e("Yapılacak İş Arama", aramaKelimesi);
        Disposable disposable = vt.yapilacaklarDao().yapilacakArama(aramaKelimesi).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(liste -> {
                    yapilacaklarListesi.setValue(liste);
                });
    }

    public void yapilacakSil(int yapilacak_id) {
        Yapilacaklar silinenYapilacakIs = new Yapilacaklar(yapilacak_id, "");
        vt.yapilacaklarDao().yapilacakSilme(silinenYapilacakIs).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                Log.e("Yapılacak İş Silme","Başarılı");
                tumYapilacaklariAl();
            }

            @Override
            public void onError(Throwable e) {
            }
        });

    }

    public void tumYapilacaklariAl() {
        Disposable disposable = vt.yapilacaklarDao().tumYapilacaklar()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(liste -> {
                    yapilacaklarListesi.setValue(liste);
                });
    }


}
