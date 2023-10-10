package com.juanfaria_jorgeseoane.proyectouf7juanyjorge

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juanfaria_jorgeseoane.proyectouf7juanyjorge.placeholder.PlaceholderContent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment() {

    private var columnCount = 1
    private val barSearch: MutableList<BarBodega> = mutableListOf()
    private val bares: MutableList<BarBodega> = mutableListOf()
      private lateinit var rv:RecyclerView
      private lateinit var db:AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch {
            db = AppDatabase.getInstance(requireActivity().applicationContext)!!
            val call = getRetrofit().create(APIBodega::class.java)
                .getListOfPlaces().execute()
            val post= call.body() as List<Bares>
            var contador:Int =0
            post.forEach{

                db.barBodegaDao().insert(BarBodega(it.name,it.address,it.city,it.speciality,it.phone,it.web))
                println("-------> ${it.name}")
            }
            val dbItems = db.barBodegaDao().loadAllBars()


            if(bares.isEmpty()){
               bares.addAll(dbItems)
            }
            barSearch.addAll(dbItems)

            MainScope().launch {
                if(view is RecyclerView){
                    with(view){
                        adapter?.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        rv = view.findViewById(R.id.list)
        rv.adapter
        // Set the adapter

            with(rv) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyItemRecyclerViewAdapter(barSearch)
            }

        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/jjbertran/eatdrink/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}