package com.example.fcmchatapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fcmchatapp.Utils
import com.example.fcmchatapp.modal.Messages
import com.example.fcmchatapp.modal.RecentChats
import com.example.fcmchatapp.modal.Users
import com.example.fcmchatapp.notifications.entity.Token
import com.google.firebase.firestore.FirebaseFirestore

class UsersRepo {

    private val firestore = FirebaseFirestore.getInstance()


    fun getUsers(): LiveData<List<Users>> {

        val users = MutableLiveData<List<Users>>()

        firestore.collection("Users").addSnapshotListener { snapshot, exception ->

            if (exception != null) {

                return@addSnapshotListener
            }

            val usersList = mutableListOf<Users>()
            snapshot?.documents?.forEach { document ->

                val user = document.toObject(Users::class.java)

                if (user!!.userid != Utils.getUidLoggedIn()) {
                    user.let {


                        usersList.add(it)
                    }


                }


                users.value = usersList
            }


        }

        return users


    }





}