package com.example.consumoapidungeondragons.viewmodel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.consumoapidungeondragons.api.Repository
import com.example.consumoapidungeondragons.model.details.MonsterDetails
import com.example.consumoapidungeondragons.model.listas.MonstersResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MonstersViewModel : ViewModel() {
    private val repository = Repository()

    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    private val _monsters = MutableLiveData<List<MonstersResult>>(emptyList())
    val monsters: LiveData<List<MonstersResult>> = _monsters

    private val _monsterDetails = MutableLiveData<MonsterDetails?>()
    val monsterDetails: LiveData<MonsterDetails?> = _monsterDetails

    private val _detailsLoading = MutableLiveData(false)
    val detailsLoading: LiveData<Boolean> = _detailsLoading

    fun getMonsters() {
        if (_monsters.value?.isNotEmpty() == true) {
            _loading.value = false
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllMonsters()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _monsters.value = response.body()?.results ?: emptyList()
                    _loading.value = false
                } else {
                    Log.e("API Error", response.message())
                }
            }
        }
    }

    fun getMonsterDetails(monsterIndex: String) {
        _detailsLoading.value = true
        _monsterDetails.value = null
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getMonsterDetails(monsterIndex)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _monsterDetails.value = response.body()
                } else {
                    Log.e("API Error", "Details: ${response.message()}")
                    _monsterDetails.value = null
                }
                _detailsLoading.value = false
            }
        }
    }
}
