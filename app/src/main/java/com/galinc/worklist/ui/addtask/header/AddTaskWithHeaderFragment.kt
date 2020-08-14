package com.galinc.worklist.ui.addtask.header

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.galinc.worklist.R
import com.galinc.worklist.domain.entity.MainTask
import com.galinc.worklist.domain.entity.MainTaskWithHeader
import kotlinx.android.synthetic.main.fragment_add_task_header.*
import kotlinx.android.synthetic.main.fragment_send.*
import kotlinx.android.synthetic.main.fragment_send.add_task_button
import kotlinx.android.synthetic.main.fragment_send.editText

class AddTaskWithHeaderFragment : Fragment() {

    private lateinit var addTaskWithHeaderViewModel: AddTaskWithHeaderViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addTaskWithHeaderViewModel =
            ViewModelProviders.of(this).get(AddTaskWithHeaderViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_add_task_header, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        add_task_button.setOnClickListener {
            if (addTaskWithHeaderViewModel.guidLiveData.value != ""){
                addTaskWithHeaderViewModel.updateTask(
                    MainTaskWithHeader(addTaskWithHeaderViewModel.guidLiveData.value,
                        editText.text.toString(),
                        false,
                        choiceHeader.selectedItem.toString(),
                        false
                    )
                )
            } else
                addTaskWithHeaderViewModel.addTask(editText.text.toString(),choiceHeader.selectedItem.toString())
            (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(editText.applicationWindowToken,0)
            Navigation.findNavController(view!!).navigate(R.id.nav_home)
        }

        val guid = arguments?.getString("fromHomeToEdit")
        if (guid != null){
            addTaskWithHeaderViewModel.getTask(guid)!!.observe(this as LifecycleOwner, Observer {
                editText.setText(it.textOfTask, TextView.BufferType.EDITABLE)
            })
            addTaskWithHeaderViewModel.guidLiveData.value =guid
            add_task_button.text = getText(R.string.edit_task_button)
        }
//        addTaskViewModel._guid.value = guid
    }
}