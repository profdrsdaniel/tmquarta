package br.com.ulbra.exemplorecycler.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.ulbra.exemplorecycler.presentation.viewmodels.MainViewModel
import br.com.ulbra.exemplorecycler.data.Product
import br.com.ulbra.exemplorecycler.presentation.adapters.ProductAdapter
import br.com.ulbra.exemplorecycler.R
import br.com.ulbra.exemplorecycler.configureToolbar

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]


        (requireActivity() as AppCompatActivity).configureToolbar("Home", false)

        val adapter = ProductAdapter(list = mainViewModel.getProducts(),
            goToDetails = ::goToDetail,
            removeItem = { mainViewModel.removeItem(it) }
        )

        val recycler = view.findViewById<RecyclerView>(R.id.rcProduct)

        recycler.adapter = adapter
    }


    private fun goToDetail(product: Product) {
        Log.i("produto", product.toString())
        val bundle = bundleOf("data" to product)
        findNavController().navigate(R.id.action_homeFragment_to_produtoDetalheActivity, bundle)
    }
}