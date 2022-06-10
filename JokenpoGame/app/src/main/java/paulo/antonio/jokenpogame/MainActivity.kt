package paulo.antonio.jokenpogame
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import paulo.antonio.jokenpogame.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Está instacia tem que sobreviver ao ciclo de vida da activity
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Vai sendo obsevado, independente do ciclo de vida da activity

        mainViewModel.pontJ.observe(this) {
            binding.pontosJogador.text = it.toString()
        }
        mainViewModel.pontM.observe(this) {
            binding.pontosMaquina.text = it.toString()
        }
        /*
        mainViewModel.pontJ.observe(this){
            binding.pontosJogador.text = it.toString()

        }*/
        var cont = 0
        var rodada = 7

        binding.btnPedra.setOnClickListener {
            game(1, cont, rodada)
            ++cont
            rodada--

        }
        binding.btnPapel.setOnClickListener {
            game(2, cont, rodada)
            ++cont
            rodada--
        }
        binding.btnTesoura.setOnClickListener {
            game(3, cont, rodada)
            ++cont
            rodada--

        }


    }

 




    @SuppressLint("SetTextI18n")
    fun game(mao: Int, cont: Int, r: Int) {

        val maquina = Random.nextInt(3)



        if (cont in 1..7) {


            if (mao == 1 && maquina == 1 || mao == 2 && maquina == 2 || mao == 3 && maquina == 3) {
                mainViewModel.pontJ.value = mainViewModel.pontJ.value?.plus(0)
                mainViewModel.pontM.value = mainViewModel.pontM.value?.plus(0)
                binding.resultJogador.text = "Empate"
                binding.resultMaquina.text = "Empate"
                binding.rodadas.text = r.toString()
            } else if (mao == 1 && maquina == 2 || mao == 2 && maquina == 3 || mao == 3 && maquina == 1) {
                mainViewModel.pontM.value = mainViewModel.pontM.value?.plus(1)
                binding.resultJogador.text = "Perdeu"
                binding.resultMaquina.text = "Ganhou"
                binding.rodadas.text = r.toString()

            } else if (mao == 1 && maquina == 3 || mao == 2 && maquina == 1 || mao == 3 && maquina == 2) {
                mainViewModel.pontJ.value = mainViewModel.pontJ.value?.plus(1)
                binding.resultJogador.text = "Ganhou"
                binding.resultMaquina.text = "Perdeu"
                binding.rodadas.text = r.toString()


            }

            when (maquina) {
                1 -> binding.gifImageView.setImageResource(R.drawable.pedra)
                2 -> binding.gifImageView.setImageResource(R.drawable.papel)
                3 -> binding.gifImageView.setImageResource(R.drawable.tesoura)
            }

            if (cont ==7){

                if (mainViewModel.pontJ.value!! > mainViewModel.pontM.value!!){
                    binding.gifImageView.setImageResource(R.drawable.comemora__o)
                    binding.resultJogador.text = "Ganhou"
                    binding.resultMaquina.text = "Perdeu"


                }else if(mainViewModel.pontJ.value!! == mainViewModel.pontM.value!!){
                    binding.gifImageView.setImageResource(R.drawable.dios_mio)
                    binding.gifImageView2.setImageResource(R.drawable.empate_bg)
                    binding.resultJogador.text = "Empate"
                    binding.resultMaquina.text = "Empate"
                }else{
                    binding.gifImageView.setImageResource(R.drawable.triste)
                    binding.gifImageView2.setImageResource(R.drawable.game_over__1_)
                    binding.resultJogador.text = "Perdeu"
                    binding.resultMaquina.text = "Ganhou"
                }


            }
        }


    }

}