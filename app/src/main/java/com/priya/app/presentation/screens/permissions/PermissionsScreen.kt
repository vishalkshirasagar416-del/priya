package com.priya.app.presentation.screens.permissions

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private data class PermissionItem(
    val title: String,
    val description: String,
    val permissions: List<String>,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionsScreen(
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val permissionItems = remember {
        listOf(
            PermissionItem(
                "Microphone",
                "Required for voice conversations.",
                listOf(Manifest.permission.RECORD_AUDIO),
            ),
            PermissionItem(
                "Notifications",
                "Required for reminders and scheduled tasks.",
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                    listOf(Manifest.permission.POST_NOTIFICATIONS)
                } else {
                    emptyList()
                },
            ),
            PermissionItem(
                "Contacts and phone",
                "Required when Priya looks up a contact or places a call.",
                listOf(Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE),
            ),
            PermissionItem(
                "Location",
                "Required for location and navigation commands.",
                listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            ),
            PermissionItem(
                "Calendar",
                "Required for calendar actions.",
                listOf(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR),
            ),
        )
    }
    val permissionState = remember { mutableStateMapOf<String, Boolean>() }
    var requestedPermissions by remember { mutableStateOf<List<String>>(emptyList()) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { results ->
            results.forEach { (permission, granted) -> permissionState[permission] = granted }
            requestedPermissions = emptyList()
        },
    )

    fun isGranted(permission: String): Boolean {
        if (permission.isBlank()) return true
        return permissionState[permission]
            ?: (context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Permissions") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = "Required app permissions",
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = "Grant only the permissions needed for the features you use. Android will show the system confirmation for each request.",
                style = MaterialTheme.typography.bodyLarge,
            )
            items(permissionItems) { item ->
                val granted = item.permissions.all(::isGranted)
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(item.title, style = MaterialTheme.typography.titleMedium)
                        Text(item.description, style = MaterialTheme.typography.bodyMedium)
                        Button(
                            onClick = {
                                requestedPermissions = item.permissions
                                if (item.permissions.isNotEmpty()) {
                                    permissionLauncher.launch(item.permissions.toTypedArray())
                                }
                            },
                            enabled = !granted && requestedPermissions.isEmpty() && item.permissions.isNotEmpty(),
                        ) {
                            Text(if (granted) "Granted" else "Grant permission")
                        }
                    }
                }
            }
        }
    }
}
