package com.example.todoapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.todoapp.repo.YapilacaklarDaoRepository;

public class DetayFragmentViewModel extends AndroidViewModel {
    private YapilacaklarDaoRepository krepo;

    public DetayFragmentViewModel(@NonNull Application application) {
        super(application);
        krepo = new YapilacaklarDaoRepository(application);
    }

    public void guncelle(int yapilacak_id, String yapilacak_is) {
        krepo.yapilacakGuncelle(yapilacak_id, yapilacak_is);
    }

}
