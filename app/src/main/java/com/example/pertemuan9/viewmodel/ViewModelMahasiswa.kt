package com.example.pertemuan9.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pertemuan9.model.request.DataMahasiswa
import com.example.pertemuan9.model.request.Mahasiswa
import com.example.pertemuan9.model.response.*
import com.example.pertemuan9.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.NavigableMap

class ViewModelMahasiswa  : ViewModel() {

    private val getDataMahasiswa = MutableLiveData<List<DataMahasiswa>?>()
    private val getDetailDataMahasiswa = MutableLiveData<ResponseDetailMahasiswa?>()
    private val insertMahasiswa = MutableLiveData<ResponseAddMahasiswa?>()
    private val updateMahasiswa = MutableLiveData<ResponseUpdateMahasiswa?>()
    private val deleteMahasiswa = MutableLiveData<ResponseDeleteMahasiswa?>()
    fun getDataMahasiswa(): MutableLiveData<List<DataMahasiswa>?> {
        return getDataMahasiswa
    }

    fun getDetailDataMahasiswa(): MutableLiveData<ResponseDetailMahasiswa?> {
        return getDetailDataMahasiswa
    }
    fun insertMahasiswa(): MutableLiveData<ResponseAddMahasiswa?>{
        return insertMahasiswa
    }
    fun updateMahasiswa():MutableLiveData<ResponseUpdateMahasiswa?>{
        return updateMahasiswa
    }
    fun deleteMahasiswa() : MutableLiveData<ResponseDeleteMahasiswa?>{
        return deleteMahasiswa
    }

    fun showDataMahasiswa() {
        ApiClient.instance.getDataMahasiswa().enqueue(object :
            Callback<ResponseDataMahasiswa> {
            override fun onResponse(
                call: Call<ResponseDataMahasiswa>,
                response: Response<ResponseDataMahasiswa>
            ) {
                if (response.isSuccessful) {
                    getDataMahasiswa.postValue(response.body()?.data)
                } else {
                    getDataMahasiswa.postValue(null)
                }
            }

            override fun onFailure(
                call:
                Call<ResponseDataMahasiswa>, t: Throwable
            ) {
                getDataMahasiswa.postValue(null)
            }
        })
    }

    fun getDetailData(nim: String) {
        ApiClient.instance.getDetailMahasiswa(nim).enqueue(object
            : Callback<ResponseDetailMahasiswa> {
            override fun onResponse(
                call: Call<ResponseDetailMahasiswa>,
                response: Response<ResponseDetailMahasiswa>
            ) {
                if (response.isSuccessful) {
                    getDetailDataMahasiswa.postValue(response.body())
                } else {
                    getDetailDataMahasiswa.postValue(null)
                }
            }

            override fun onFailure(
                call:
                Call<ResponseDetailMahasiswa>, t: Throwable
            ) {
                getDetailDataMahasiswa.postValue(null)
            }
        })
    }
    fun addMahasiswa(nim: String, nama:String, telepon:String){
        ApiClient.instance.addDataMahasiswa(Mahasiswa(nim, nama, telepon)).enqueue(object : Callback<ResponseAddMahasiswa>{
            override fun onResponse(
                call: Call<ResponseAddMahasiswa>,
                response: Response<ResponseAddMahasiswa>
            ) {
                if (response.isSuccessful){
                    insertMahasiswa.postValue(response.body())
                }else{
                    insertMahasiswa.postValue(null)
                }
            }
            override fun onFailure(call:
                                   Call<ResponseAddMahasiswa>, t: Throwable) {
                insertMahasiswa.postValue(null)
            }
        })
    }
    fun updateDataMahasiswa(nim : String, nama :String,telepon :
    String){
        ApiClient.instance.updateDataMahasiswa(nim,Mahasiswa(nim,nama,telepon)).enqueue(object : Callback<ResponseUpdateMahasiswa>{
            override fun onResponse(
                call: Call<ResponseUpdateMahasiswa>,
                response: Response<ResponseUpdateMahasiswa>
            ) {
                if (response.isSuccessful){
                    updateMahasiswa.postValue(response.body())
                }else{
                    updateMahasiswa.postValue(null)
                }
            }
            override fun onFailure(call:
                                   Call<ResponseUpdateMahasiswa>, t: Throwable) {
                updateMahasiswa.postValue(null)
            }
        })
    }
    fun deleteMahasiswa(nim: String){
        ApiClient.instance.deleteDataMahasiswa(nim).enqueue(object : Callback<ResponseDeleteMahasiswa>{
            override fun onResponse(
                call: Call<ResponseDeleteMahasiswa>,
                response :  Response<ResponseDeleteMahasiswa>
            ) {
                if (response.isSuccessful){
                     deleteMahasiswa.postValue(response.body())
                }else{
                    deleteMahasiswa.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseDeleteMahasiswa>, t: Throwable) {
                deleteMahasiswa.postValue(null)
            }

        })
    }
}
