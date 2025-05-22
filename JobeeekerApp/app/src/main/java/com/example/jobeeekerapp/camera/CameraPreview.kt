package com.example.jobeeekerapp.camera

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig


class MarkerView() {
    val bmp = mutableStateOf<ImageBitmap?>(
        ImageBitmap(500, 500, hasAlpha = true, config =
        ImageBitmapConfig.Argb8888)
    )

    @Composable
    fun CameraPreview(cameraLib: CameraLib) {
        // Key Point: Displaying the Camera Preview
        Image(
            bitmap = bmp.value!!,
            contentDescription = "marker camera preview"
        )
    }
}

class MonocularView() {
    val bmp = mutableStateOf<ImageBitmap?>(
        ImageBitmap(500, 500, hasAlpha = true, config =
        ImageBitmapConfig.Argb8888)
    )

    @Composable
    fun CameraPreview(cameraLib: CameraLib) {
        // Key Point: Displaying the Camera Preview
        Image(
            bitmap = bmp.value!!,
            contentDescription = "monocular camera preview"
        )
    }
}

