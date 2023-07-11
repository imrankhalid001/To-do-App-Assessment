package com.astutesol.notesappmvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.astutesol.notesappmvvm.api.NotesApi
import com.astutesol.notesappmvvm.models.GetCallListModelResopnseItem
import com.astutesol.notesappmvvm.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class NoteRepository @Inject constructor(private val notesApi: NotesApi) {

    private val _callListLiveData = MutableLiveData<NetworkResult<List<GetCallListModelResopnseItem>>>()
    val callListLiveData: LiveData<NetworkResult<List<GetCallListModelResopnseItem>>>
        get() = _callListLiveData




    private val _buyListLiveData = MutableLiveData<NetworkResult<List<GetCallListModelResopnseItem>>>()
    val buyListLiveData: LiveData<NetworkResult<List<GetCallListModelResopnseItem>>>
        get() = _buyListLiveData





    private val _statusLiveData = MutableLiveData<NetworkResult<String>>()
    val statusLiveData: LiveData<NetworkResult<String>>
        get() = _statusLiveData




    suspend fun getCallList() {
        _callListLiveData.postValue(NetworkResult.Loading())

        val response = notesApi.getCallListData()
        if (response.isSuccessful && response.body() != null) {
            _callListLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())

            _callListLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _callListLiveData.postValue(NetworkResult.Error("Something went wrong"))

        }

    }


    suspend fun getBuyList() {
        _buyListLiveData.postValue(NetworkResult.Loading())

        val response = notesApi.getBuyListData()
        if (response.isSuccessful && response.body() != null) {
            _buyListLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())

            _buyListLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _buyListLiveData.postValue(NetworkResult.Error("Something went wrong"))

        }

    }




    private fun handleResponse(response: Response<GetCallListModelResopnseItem>, message : String) {
        if (response.isSuccessful && response.body() != null) {
            _statusLiveData.postValue(NetworkResult.Success(message))
        } else {
            _statusLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

}