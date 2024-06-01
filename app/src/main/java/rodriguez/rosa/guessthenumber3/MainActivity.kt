package rodriguez.rosa.guessthenumber3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var minValue = 0
    var maxValue = 100
    var num: Int = 0
    var won: Boolean = false

    private val textoGano: String = "Ganaste"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val guessings: TextView = findViewById(R.id.guessings)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)
        val up: Button = findViewById(R.id.up)
        val down: Button = findViewById(R.id.down)

        generate.setOnClickListener {
            num = Random.nextInt(minValue, maxValue)
            guessings.text = num.toString()
            generate.visibility = TextView.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener {
            minValue = num

            if (!rangeAreSame()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.text = num.toString()
            } else {
                guessings.text = textoGano
            }

        }

        down.setOnClickListener {
            maxValue = num

            if (!rangeAreSame()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.text = num.toString()
            } else {
                guessings.text = textoGano
            }

        }

        guessed.setOnClickListener {

            if(won) {
                generate.visibility = View.VISIBLE
                guessings.text = "Tap on generate to start"
                guessed.text = "Guessed"
                guessed.visibility = View.GONE
                resetValues()
            } else {
                guessings.text = "Adivine el numero, es el $num"
                guessed.text = "Volver a jugar"
                won = true
            }

        }

    }

    private fun resetValues() {
        minValue = 0
        maxValue = 100
        num = 0
        won = false
    }

    private fun rangeAreSame(): Boolean {
        return minValue == maxValue
    }


}


