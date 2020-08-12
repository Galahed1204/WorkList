package com.galinc.worklist.ui.addheader

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
import com.galinc.worklist.domain.entity.MainTaskWithHeader
import kotlinx.android.synthetic.main.fragment_send.*

class AddHeaderFragment: Fragment() {

    private lateinit var addHeaderViewModel: AddHeaderViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addHeaderViewModel =
            ViewModelProviders.of(this).get(AddHeaderViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_send, container, false)
        //val textView: TextView = root.findViewById(R.id.text_send)
        //addTaskViewModel.text.observe(this as LifecycleOwner, Observer {
        //textView.text = it
        //})
        return root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        add_task_button.text =  getText(R.string.add_header_button)
        add_task_button.setOnClickListener {
            if (addHeaderViewModel.guidLiveData.value != "") {
                addHeaderViewModel.updateHeader(
                    MainTaskWithHeader(
                        addHeaderViewModel.guidLiveData.value,
                        "",
                        false,
                        editText.text.toString(),
                        true
                    )
                )
            } else
                addHeaderViewModel.addHeader(editText.text.toString())
            (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(editText.applicationWindowToken, 0)
            Navigation.findNavController(view!!).navigate(R.id.nav_home)
        }

        val guid = arguments?.getString("fromHomeToEdit")
        if (guid != null) {
            addHeaderViewModel.getHeader(guid)!!.observe(this as LifecycleOwner, Observer {
                editText.setText(it.textOfTask, TextView.BufferType.EDITABLE)
            })
            addHeaderViewModel.guidLiveData.value = guid
            add_task_button.text = getText(R.string.edit_header_button)
        }
    }
}