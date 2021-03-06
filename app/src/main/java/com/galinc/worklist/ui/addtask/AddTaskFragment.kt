package com.galinc.worklist.ui.addtask

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.galinc.worklist.R
import com.galinc.worklist.domain.entity.MainTask
import kotlinx.android.synthetic.main.fragment_send.*






class AddTaskFragment : Fragment() {

    private lateinit var addTaskViewModel: AddTaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addTaskViewModel =
            ViewModelProviders.of(this).get(AddTaskViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_send, container, false)
        //val textView: TextView = root.findViewById(R.id.text_send)
        //addTaskViewModel.text.observe(this as LifecycleOwner, Observer {
            //textView.text = it
        //})
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        add_task_button.setOnClickListener {
            if (addTaskViewModel.guidLiveData.value != ""){
                addTaskViewModel.updateTask(
                    MainTask(addTaskViewModel.guidLiveData.value,
                        editText.text.toString(),
                        false))
            } else
            addTaskViewModel.addTask(editText.text.toString())
            (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(editText.applicationWindowToken,0)
            Navigation.findNavController(view!!).navigate(R.id.nav_home)
        }

        val guid = arguments?.getString("fromHomeToEdit")
        if (guid != null){
            addTaskViewModel.getTask(guid)!!.observe(this as LifecycleOwner, Observer {
                editText.setText(it.textOfTask,TextView.BufferType.EDITABLE)
            })
            addTaskViewModel.guidLiveData.value =guid
            add_task_button.text = getText(R.string.edit_task_button)
        }
//        addTaskViewModel._guid.value = guid
    }
}