package br.com.ulbra.exemplorecycler.presentation.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.ulbra.exemplorecycler.data.Product
import br.com.ulbra.exemplorecycler.R
import com.bumptech.glide.Glide

class ProductAdapter(
    private val list: MutableList<Product>,
    val goToDetails: (product: Product) -> Unit,
    val removeItem: (index: Int) -> Unit,
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context)
            .inflate(R.layout.product_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            goToDetails(list[position])
        }

        holder.itemView.rootView.setOnLongClickListener {
            showPopUpMenu(it, position, context)
            true
        }

        Glide
            .with(context)
            .load(list[position].urlImage)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.img)

        holder.tvNome.text = list[position].name

        holder.tvPrice.text = list[position].price
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNome: TextView
        val img: ImageView
        val tvPrice: TextView

        init {
            tvNome = view.findViewById(R.id.tvProductName)
            img = view.findViewById(R.id.imgProduct)
            tvPrice = view.findViewById(R.id.tvProductPrice)
        }
    }

    private fun showPopUpMenu(view: View, position: Int, context: Context) {
        PopupMenu(context, view).apply {
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.remove -> {
                        removeItem(position)
                        notifyDataSetChanged()
                        true
                    }

                    else -> {
                        Log.i("erro", "erro")
                        false
                    }
                }
            }
            inflate(R.menu.menu_popup)
            show()
        }
    }
}

