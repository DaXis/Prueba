package com.prueba.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.prueba.utils.parseError
import io.reactivex.disposables.CompositeDisposable
import okhttp3.ResponseBody
import retrofit2.adapter.rxjava2.HttpException

abstract class BaseViewModel : ViewModel() {
    protected val disposable = CompositeDisposable()

    protected val showProgress = BaseSingleLiveEvent<Boolean>()
    protected val showErrorMessage = BaseSingleLiveEvent<String>()

    fun getShowProgress(): LiveData<Boolean> = showProgress
    fun getShowErrorMessage(): LiveData<String> = showErrorMessage

    fun getServiceError(throwable: Throwable): String {
        val exception = throwable as HttpException
        val error = exception.response()?.errorBody() as ResponseBody
        val baseErrorResponse = parseError(error.string())
        baseErrorResponse?.let {
            return it.message.orEmpty()
        } ?: run {
            return "Error en el servicio"
        }
    }

    override fun onCleared() {
        disposable.clear()
    }
}