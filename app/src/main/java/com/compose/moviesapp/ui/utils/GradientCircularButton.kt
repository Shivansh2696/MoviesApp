package com.compose.moviesapp.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.compose.moviesapp.R

@Composable
fun GradientCircularButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonSize: Dp = 56.dp,
    iconTint: Color = Color.White
) {
    Box(
        modifier = modifier
            .size(buttonSize)
            .clip(CircleShape) // Clip the Box to a circle
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF6A1B9A), Color(0xFF1E88E5))
                )
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = stringResource(R.string.next),
            tint = iconTint,
            modifier = Modifier.size(buttonSize / 2)
        )
    }
}