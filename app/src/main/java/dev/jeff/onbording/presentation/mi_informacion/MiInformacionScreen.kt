package dev.jeff.onbording.presentation.mi_informacion

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.jeff.onbording.R


@Composable
fun MiInformacionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.user_placeholder),
            contentDescription = "Mi foto",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Jefferson Garay", style = MaterialTheme.typography.headlineSmall)
        Text("Usuario de la App", style = MaterialTheme.typography.bodyMedium)
        Text("Email: jefferson@empresa.com")
    }
}
