package com.example.projectanroid.Permission.data

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.ContextCompat
import com.example.projectanroid.common.StatusPermession
import kotlinx.coroutines.flow.MutableStateFlow

class Permissionllmt( val contextPermission: Context): PermissionReposi {
    override fun CheckPermission(manifest_permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            contextPermission,
            manifest_permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    @Composable
    override fun CallPermission() {
        val valuePermission = MutableStateFlow("")
        val  _valuePermission = valuePermission
       val locationPermissionRequest = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    valuePermission.value=   StatusPermession.Fine
                }

                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    valuePermission.value = StatusPermession.Auto
                }

                else -> {
                    valuePermission.value = StatusPermession.No
                }
            }
        }
        LaunchedEffect(Unit) {
                locationPermissionRequest.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
        }
    }
}