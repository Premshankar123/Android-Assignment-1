package chalkup.sekhontech.androidassignment_1

import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.SearchView
import androidx.core.view.MenuItemCompat

class MainActivity : AppCompatActivity() {
    var addProduct: Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar=supportActionBar
        actionBar!!.title="Product & Services"
        val colorDrawable=ColorDrawable(getColor(R.color.purple_500))
        actionBar.setBackgroundDrawable(colorDrawable)
        actionBar.setDisplayHomeAsUpEnabled(true)
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.purple_700)
        }
        addProduct=findViewById(R.id.addProduct)
        val fragment = ProductFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, fragment, "")
        fragmentTransaction.commit()
        addProduct?.visibility= View.VISIBLE
        addProduct?.setOnClickListener {
            val fragment =AddProductFragment()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.content, fragment, "")
            fragmentTransaction.commit()
            addProduct?.visibility= View.GONE
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val fragment = ProductFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, fragment, "")
        fragmentTransaction.commit()
        addProduct?.visibility= View.VISIBLE
        return super.onOptionsItemSelected(item)
    }

}