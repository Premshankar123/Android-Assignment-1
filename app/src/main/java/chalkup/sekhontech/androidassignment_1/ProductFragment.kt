package chalkup.sekhontech.androidassignment_1
import androidx.recyclerview.widget.RecyclerView
import chalkup.sekhontech.androidassignment_1.models.ProductModel
import chalkup.sekhontech.androidassignment_1.viewmodels.ProductViewModel
import chalkup.sekhontech.androidassignment_1.adapters.ProductAdapter
import android.widget.TextView
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.lifecycle.ViewModelProviders
import java.util.*

class ProductFragment :Fragment() {
    var recview: RecyclerView? = null
    var movielist: List<ProductModel>? = null
    var listViewModel: ProductViewModel? = null
    var adapter: ProductAdapter? = null
    var noresfound: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.product_fragment, container, false)
        recview =view.findViewById(R.id.recview)
        noresfound = view.findViewById(R.id.noresult)
        recview!!.setLayoutManager(LinearLayoutManager(context))
        recview!!.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        adapter = ProductAdapter(movielist)
        recview!!.setAdapter(adapter)
        listViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        listViewModel!!.movielistObserver.observe(viewLifecycleOwner) { movieModels ->
            if (movieModels != null) {
                movielist = movieModels
                adapter!!.updatemovielist(movieModels)
                noresfound!!.setVisibility(View.GONE)
            }
            if (movieModels == null) {
                recview!!.setVisibility(View.GONE)
                noresfound!!.setVisibility(View.VISIBLE)
            }
        }
        listViewModel!!.makeApiCall()
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.search_item,menu)
        val item = menu.findItem(R.id.search)
        val searchView = MenuItemCompat.getActionView(item) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText)
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<ProductModel> = ArrayList()

        // running a for loop to compare elements.
        for (item in movielist!!) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.product_name.toLowerCase().contains(text.lowercase(Locale.getDefault()))) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(context, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter?.filterList(filteredlist)
        }
    }
}