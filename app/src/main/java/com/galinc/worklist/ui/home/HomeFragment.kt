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
import com.galinc.worklist.R
import com.galinc.worklist.domain.entity.MainTask
import com.galinc.worklist.ui.adapter.MainTaskAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val items = listOf(
            MainTask("Александр", false),
            MainTask("Михаил", false),
            MainTask("Николай", false),
            MainTask("Фёдор", false),
            MainTask("Сергей", true)
        )

        val myAdapter = MainTaskAdapter(items, object:MainTaskAdapter.Callback {
            override fun onItemClicked(item: MainTask) {
                //TODO Сюда придёт элемент, по которому кликнули. Можно дальше с ним работать
            }
        })

        homeList.adapter = myAdapter
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