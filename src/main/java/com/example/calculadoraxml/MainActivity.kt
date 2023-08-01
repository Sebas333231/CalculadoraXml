package com.example.calculadoraxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculadoraxml.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button1.setOnClickListener{ appendOnExpression("1", true)}
        binding.button2.setOnClickListener{ appendOnExpression("2", true)}
        binding.button3.setOnClickListener{ appendOnExpression("3", true)}
        binding.button4.setOnClickListener{ appendOnExpression("4", true)}
        binding.button5.setOnClickListener{ appendOnExpression("5", true)}
        binding.button6.setOnClickListener{ appendOnExpression("6", true)}
        binding.button7.setOnClickListener{ appendOnExpression("7", true)}
        binding.button8.setOnClickListener{ appendOnExpression("8", true)}
        binding.button9.setOnClickListener{ appendOnExpression("9", true)}
        binding.button0.setOnClickListener{ appendOnExpression("0", true)}
        binding.buttonDot.setOnClickListener{ appendOnExpression(".", true)}



        binding.buttonPlus.setOnClickListener{ appendOnExpression("+", false)}
        binding.buttonMinus.setOnClickListener{ appendOnExpression("-", false)}
        binding.buttonMultiply.setOnClickListener{ appendOnExpression("*", false)}
        binding.buttonDivide.setOnClickListener{ appendOnExpression("/", false)}
        binding.buttonOpenBracket.setOnClickListener{ appendOnExpression("(", false)}
        binding.buttonCloseBracket.setOnClickListener{ appendOnExpression(")", false)}


        binding.buttonC.setOnClickListener{
            binding.input.text = ""
            binding.output.text = ""
        }

        binding.buttonAC.setOnClickListener{
            val string = binding.input.text.toString()
            if (string.isNotEmpty()){
                binding.input.text = string.substring(0, string.length-1)
            }
            binding.output.text = ""
        }

        binding.buttonEquals.setOnClickListener{
            val expression = ExpressionBuilder(binding.input.text.toString()).build()
            val result = expression.evaluate()
            val longResult = result.toLong()
            if (result==longResult.toDouble()){
                binding.output.text = longResult.toString()
            }else{
                binding.output.text = result.toString()
            }
        }
    }

    private fun appendOnExpression(string: String, canClear: Boolean){
        if (binding.output.text.isNotEmpty()){
            binding.input.text = ""
        }

        if(canClear){
            binding.output.text = ""
            binding.input.append(string)
        }else{
            binding.input.append(binding.output.text)
            binding.input.append(string)
            binding.output.text = ""
        }
    }
}