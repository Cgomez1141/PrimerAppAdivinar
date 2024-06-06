package com.example.appadivinar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.appadivinar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{

    lateinit var imagen:ImageView
    lateinit var textopunto:TextView
    lateinit var n1:EditText
    private var puntos: Int = 0
    private var numeroAleatorio: Int = 0
    private var numeroIngresado: Int = 0
    private val imagenes = arrayOf(
        R.drawable.uno,
        R.drawable.dos,
        R.drawable.tres,
        R.drawable.cuatro,
        R.drawable.cinco
    )


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val enlace = ActivityMainBinding.inflate(layoutInflater)

        imagen = enlace.imagen
        n1 = enlace.editTextuno
        textopunto = enlace.textresult
        setContentView(enlace.root)

        // llamo los metodos para determinar el numero que ingresa el usuario con el random de la imagen
        generarNumeroAleatorio()
        mostrarImagen(numeroAleatorio)
    }

    // Generar un # aleatorio entre 1 y 5
    private fun generarNumeroAleatorio() {
        numeroAleatorio = (1..5).random()
    }

    // Espacio en donde se visualiza la función del botón, cambio de imagen y determinar la suma del puntaje
    fun accion(view: View) {
        // Obtener el # ingresado por Usuario
        numeroIngresado = n1.text.toString().toIntOrNull() ?: return //usé el toIntOrNull() ?: return para convertir la cadena string a int y determinar si el # es nulo

        // Verificar si el número ingresado está dentro del rango válido (1 a 5)
        if (numeroIngresado in 1..5) { // si esto se cumple inicia el juego si no ps se detiene o no inicia
            // Si el número ingresado n1 es igual a numero aleatorio entonce suma 10 puntos
            if (numeroIngresado == numeroAleatorio) {
                // Aumentar puntos en 10 y mostrar en el TextView
                puntos += 10
                textopunto.text = "$puntos"  //llamo la variable textopunto para asignarle el valor de los puntos
            }
            // Generar un nuevo número aleatorio y mostrar la imagen correspondiente
            generarNumeroAleatorio()
            mostrarImagen(numeroAleatorio) //enlace del generador de numero random y la imagen random para poder determinar si adivina el numero que sale en pantalla con lo que el usuario ponga
        } else {
            // Muestro un mensaje de error al usuario si el número es menor a 1 y mayor a 5
            n1.error = "Por favor ingresa un número entre 1 y 5"
        }
    }

    // Espacio en la que se recorre el array que contiene las imagenes
    private fun mostrarImagen(numero: Int) {
        // Obtener el identificador de la imagen dentro del array, restando 1 al número para ajustar el índice
        val imagenAleatoria = imagenes[numero - 1] //asignar el nombre del arreglo y el recorrido para ajustar la posición en la que se encuentre
        // Establecer la imagen en el ImageView, con la variable imagen aleatoria
        imagen.setImageResource(imagenAleatoria)
    }
}