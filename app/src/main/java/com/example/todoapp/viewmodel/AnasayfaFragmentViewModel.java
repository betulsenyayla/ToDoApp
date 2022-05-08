package com.example.todoapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.todoapp.entity.Yapilacaklar;
import com.example.todoapp.repo.YapilacaklarDaoRepository;

import java.util.List;

public class AnasayfaFragmentViewModel extends AndroidViewModel {
    private YapilacaklarDaoRepository krepo;
    public MutableLiveData<List<Yapilacaklar>> yapilacaklarListesi = new MutableLiveData();

    public AnasayfaFragmentViewModel(@NonNull Application application) {
        super(application);
        krepo = new YapilacaklarDaoRepository(application);
        yapilacaklariYukle();
        yapilacaklarListesi = krepo.yapilacaklariGetir();
    }

    public void ara(String aramaKelimesi) {
        krepo.yapilacakAra(aramaKelimesi);
    }

    public void sil(int yapilacak_id) {
        krepo.yapilacakSil(yapilacak_id);
    }

    public void yapilacaklariYukle() {
        krepo.tumYapilacaklariAl();
    }

}
