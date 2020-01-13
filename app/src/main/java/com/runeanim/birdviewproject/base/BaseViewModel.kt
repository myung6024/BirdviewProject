package com.runeanim.birdviewproject.base

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.runeanim.birdviewproject.util.Event
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel(){
    protected val compositeDisposable : CompositeDisposable = CompositeDisposable()

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    protected fun showSnackbarMessage(@StringRes message: Int) {
        _snackbarText.value = Event(message)
    }
}