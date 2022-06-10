package paulo.antonio.jokenpogame

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import paulo.antonio.jokenpogame.databinding.ActivityMainBinding
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private lateinit var binding: ActivityMainBinding


    var pontM = MutableLiveData(0)
    var pontJ =  MutableLiveData(0)

    fun cont(){
        pontJ.value = pontJ.value?.plus(1)
        pontM.value = pontM.value?.plus(1)
    }
}





