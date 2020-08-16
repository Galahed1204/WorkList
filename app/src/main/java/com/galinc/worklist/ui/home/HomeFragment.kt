package com.galinc.worklist.ui.home

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.galinc.worklist.R
import com.galinc.worklist.db.AppDatabase
import com.galinc.worklist.domain.entity.MainTask
import com.galinc.worklist.domain.entity.MainTaskWithHeader
import com.galinc.worklist.ui.adapter.MainTaskAdapter
import com.galinc.worklist.ui.adapter.MainTaskWithHeaderAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.fragment_home.*




class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        val items = AppDatabase.getInstance(context!!.applicationContext).mainTaskDao().getMainTaskDB()
        val myAdapter = MainTaskAdapter(listOf(), object:MainTaskAdapter.Callback {
            override fun onItemClicked(item: MainTask) {
                val myBundle = Bundle()
                myBundle.putString("fromHomeToEdit", item.guid)
                Navigation.findNavController(view!!).navigate(R.id.action_nav_home_to_nav_send,myBundle)
            }

            override fun onItemChecked(item: MainTask) {
                item.completed = true
                homeViewModel.updateMainTask(item)
            }
        })

        homeViewModel.getAllMainTask()!!
            .observe(this as LifecycleOwner, Observer {
                myAdapter.setItemList(it)
            } )

        val adapterWithHeaderAdapter = MainTaskWithHeaderAdapter(listOf(), object:MainTaskWithHeaderAdapter.Callback{
            override fun onItemClicked(item: MainTaskWithHeader) {
                val myBundle = Bundle()
                myBundle.putString("fromHomeToEdit", item.guid)
                Navigation.findNavController(view!!).navigate(R.id.action_nav_home_to_nav_send,myBundle)
            }

            override fun onItemChecked(item: MainTaskWithHeader) {
                item.completed = true
                homeViewModel.updateMainTaskWithHeader(item)
            }

        })

        homeViewModel.getAllMainTaskWithHeader()!!
            .observe(this as LifecycleOwner, Observer {
                adapterWithHeaderAdapter.setItemList(it
                    .sortedWith (compareBy<MainTaskWithHeader>{item -> item.header}
                        .thenBy{item -> !item.isHeader }) )
            } )



//        homeList.adapter = myAdapter
        homeList.adapter = adapterWithHeaderAdapter

        fab_home.setOnClickListener {
            Navigation.findNavController(view!!).navigate(R.id.action_nav_home_to_addTaskWithHeaderFragment)
//            Navigation.findNavController(view!!).navigate(R.id.action_nav_home_to_nav_send)


        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        //ViewModelProvider
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        //val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(this as LifecycleOwner, Observer {
//            //textView.text = it
//        })

        return root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_header)
        Navigation.findNavController(view!!).navigate(R.id.action_nav_home_to_addHeaderFragment)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_task,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}