package com.example.specialnote.fragment.add


import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.specialnote.R
import com.example.specialnote.model.User
import com.example.specialnote.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

/**
 * A simple [Fragment] subclass.
 */
class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view =  inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        view.add_button.setOnClickListener{
            insertDataToDatabase()
        }


   return view
    }

    private fun insertDataToDatabase() {


        val firstName = add_first_name_et.text.toString()
        val lastName = add_last_name_et.text.toString()
        val age = addAge_et.text

        if (inputCheck(firstName,lastName,age)){

            //crewat user object
        val user = User(
            0,
            firstName,
            lastName,
            Integer.parseInt(age.toString())
        )
            //Add Data to Database
            mUserViewModel.addUser(user)

            Toast.makeText(requireContext(),"Successsfully Added !",Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(),"Please Fill out all filds",Toast.LENGTH_LONG).show()
        }

         }

    private fun inputCheck(firstName:String,lastName:String,age:Editable):Boolean{

        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())


    }


}
