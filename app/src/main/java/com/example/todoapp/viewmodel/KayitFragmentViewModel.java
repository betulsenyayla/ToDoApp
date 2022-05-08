package com.example.todoapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.todoapp.repo.YapilacaklarDaoRepository;

public class KayitFragmentViewModel extends AndroidViewModel {
    private YapilacaklarDaoRepository krepo;

    public KayitFragmentViewModel(@NonNull Application application) {
        super(application);
        krepo = new YapilacaklarDaoRepository(application);
    }

    public void kayit(String yapilacak_is) {
        krepo.yapilacakEkle(yapilacak_is);
    }

}
