package com.abrahamlay.contactsapp.network

import com.abrahamlay.contactsapp.ui.BaseView
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import org.json.JSONObject
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by Abraham on 18/10/2018.
 */
abstract class CallbackWrapper<T>(private var view: BaseView) : DisposableObserver<T>() {

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(t: Throwable) {
        var code = 0
        when (t) {
            is HttpException -> {

                val responseBody = t.response().errorBody()
                code = t.response().code()
                view.showLoading(false)
                view.onUnknownError(code, getErrorMessage(responseBody))
            }
            is SocketTimeoutException -> {
                view.showLoading(false)
                view.onTimeout()
            }
            is IOException -> {
                view.showLoading(false)
                view.onNetworkError()
            }
            else -> {
                view.showLoading(false)
                view.onUnknownError(code, t.message)
            }
        }
    }

    override fun onComplete() {

    }

    protected abstract fun onSuccess(t: T)

    private fun getErrorMessage(responseBody: ResponseBody?): String? {
        return try {
            val jsonObject = JSONObject(responseBody!!.string())
            jsonObject.getString("message")
        } catch (e: Exception) {
            e.message
        }

    }
}