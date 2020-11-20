package com.example.specialnote.fragment.update


import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.specialnote.R
import com.example.specialnote.model.User
import com.example.specialnote.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.fragment_update.view.update_first_name_et

class updateFragment : Fragment() {
private val args by navArgs<updateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.update_first_name_et.setText(args.CurrentUser.firstName)
        view.update_last_name_et.setText(args.CurrentUser.lastName)
        view.updateAge_et.setText(args.CurrentUser.age.toString())

        view.update_button.setOnClickListener {
                updateItem()
        }

        //add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){

        val firstName = update_first_name_et.text.toString()
        val lastName = update_last_name_et.text.toString()
        val age = Integer.parseInt(updateAge_et.text.toString())


        if (inputCheck(firstName,lastName,updateAge_et.text)){

            val updatedUser = User(args.CurrentUser.id,firstName,lastName,age)
            mUserViewModel.updateUser(updatedUser)

            Toast.makeText(requireContext(),"Update Successfully",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill out all Fields",Toast.LENGTH_SHORT).show()

        }

    }

    private fun inputCheck(firstName:String,lastName:String,age: Editable):Boolean{

        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->

            mUserViewModel.deleteUser(args.CurrentUser)
            Toast.makeText(requireContext(),"Successfully Remove:${args.CurrentUser.firstName}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }
        builder.setNegativeButton("No"){_,_-> }

        builder.setTitle("Delete ${args.CurrentUser.firstName}?")

        builder.setMessage("Are you sure you want to delete ${args.CurrentUser.firstName}")
        builder.create().show()



          }
}
