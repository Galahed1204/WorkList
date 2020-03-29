package com.galinc.worklist.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.galinc.worklist.R
import com.galinc.worklist.domain.entity.MainTask
import com.galinc.worklist.ui.adapter.MainTaskAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val items = listOf(
            MainTask(textOfTask = "Александр", completed = false),
            MainTask(textOfTask = "Михаил", completed = false),
            MainTask(textOfTask = "Николай", completed = false),
            MainTask(textOfTask = "Фёдор", completed = false),
            MainTask(textOfTask = "Сергей", completed = true)
        )

        val myAdapter = MainTaskAdapter(items, object:MainTaskAdapter.Callback {
            override fun onItemClicked(item: MainTask) {
                //TODO(" Сюда придёт элемент, по которому кликнули. Можно дальше с ним работать")
            }

            override fun onItemChecked(item: MainTask) {
                item.completed = true
            }
        })

        homeList.adapter = myAdapter

        fab_home.setOnClickListener {_ ->
            Navigation.findNavController(view!!).navigate(R.id.action_nav_home_to_nav_send)


        }

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
        homeViewModel.text.observe(this as LifecycleOwner, Observer {
            //textView.text = it
        })

        return root
    }
}