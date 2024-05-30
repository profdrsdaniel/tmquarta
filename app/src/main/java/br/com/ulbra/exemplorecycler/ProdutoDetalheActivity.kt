package br.com.ulbra.exemplorecycler

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import br.com.ulbra.exemplorecycler.data.Product
import com.bumptech.glide.Glide

@Suppress("DEPRECATION")
class ProdutoDetalheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto_detalhe)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar).apply {
            title = "Detalhe"
        }

        val productBundle = if (Build.VERSION.SDK_INT >= 33) {
            intent?.getSerializableExtra("data", Product::class.java)
        } else {
            intent?.getSerializableExtra("data") as? Product
        }

        val productName = findViewById<TextView>(R.id.tvProductName)
        val productPrice = findViewById<TextView>(R.id.tvProductPrice)
        val productImg = findViewById<ImageView>(R.id.imgProduct)

        productName.text = productBundle?.name
        productPrice.text = productBundle?.price

        Glide
            .with(this)
            .load(productBundle?.urlImage)
            .into(productImg)
    }

}