package com.example.swmandroid.util

import com.example.swmandroid.GlobalApplication
import com.example.swmandroid.model.login.UserProfile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun saveUserProfileAtDataStore(userProfile: UserProfile?) {
    CoroutineScope(Dispatchers.Main).launch {
        GlobalApplication.getInstance().getDataStore().setEmail(userProfile?.email ?: "")
        GlobalApplication.getInstance().getDataStore().setExp(userProfile?.exp ?: 0)
        GlobalApplication.getInstance().getDataStore().setNickName(userProfile?.nick_name ?: "")
        GlobalApplication.getInstance().getDataStore().setTechStack(userProfile?.tech_stack ?: "")
        GlobalApplication.getInstance().getDataStore().setTier(userProfile?.tier ?: "")
        GlobalApplication.getInstance().getDataStore().setUserRole(userProfile?.user_role ?: "")
        GlobalApplication.getInstance().getDataStore().setUserToken(userProfile?.user_token ?: "")
    }
}