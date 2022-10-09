package com.awais.hilt.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.awais.hilt.ui.main.api.ApiInterface
import com.awais.hilt.ui.main.models.DataResponse
import com.awais.hilt.utils.Constants.TAG
import com.awais.hilt.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class MainRepository @Inject constructor(private val noteAPI: ApiInterface) {

    private val _notesLiveData = MutableLiveData<NetworkResult<DataResponse>>()
    val notesLiveData get() = _notesLiveData

    suspend fun getNotes() {
        _notesLiveData.postValue(NetworkResult.Loading())
        val response = noteAPI.getData()
        if (response.isSuccessful && response.body() != null) {
            Log.d(TAG, "getNotes: ${response.body().toString()}")
            _notesLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _notesLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _notesLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }

}