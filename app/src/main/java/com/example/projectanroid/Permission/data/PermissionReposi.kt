package com.example.projectanroid.Permission.data

import androidx.compose.runtime.Composable
import com.example.projectanroid.Permission.StatusPermission
import java.util.concurrent.Flow

interface PermissionReposi {
    fun CheckPermission(manifest_permission: String):Boolean

    @Composable
    fun CallPermission()

}