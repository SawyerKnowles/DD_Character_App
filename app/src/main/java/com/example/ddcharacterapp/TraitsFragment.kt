package com.example.ddcharacterapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.RectangleShape
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.*
import java.net.URI

class TraitsFragment : Fragment() {

    private var characterImage : Uri? = null

    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            // Use the returned uri.
            val uriContent = result.uriContent
            val uriFilePath = context?.let { result.getUriFilePath(it) } // optional usage
            setImage(uriContent)
        } else {
            // An error occurred.
            val exception = result.error
        }
    }

    private fun setImage(uriContent: Uri?) {
        val act = (activity as AppCompatActivity) // get activity
        Glide.with(this)
            .load(uriContent)
            .into(act.findViewById<ImageView>(R.id.character_image))

        characterImage = uriContent
    }

    private val imageFromGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if(it.resultCode == Activity.RESULT_OK) {
            it.data?.data?.let { uri ->
                launchImageCrop(uri)
            }
        }
        else {
            Log.d("Image Select Error", "Image URI was invalid")
        }
    }

    private fun launchImageCrop(uri: Uri?) {
        val cropOptions = CropImageOptions (
            fixAspectRatio = true,
            aspectRatioX = 1000,
            aspectRatioY = 1000,
            allowRotation = true,
            allowFlipping = true,
            guidelines = CropImageView.Guidelines.ON,
            cropShape = CropImageView.CropShape.RECTANGLE
        )
        val options = CropImageContractOptions(uri,cropOptions)
        cropImage.launch(options)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_traits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = (activity as AppCompatActivity) // get activity
        act.supportActionBar?.title = "Character Name"

        val characterImage = act.findViewById<ImageView>(R.id.character_image)
        characterImage.setOnClickListener() {
            pickFromGallery()
        }

    }

    private fun pickFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        val mimeTypes = arrayOf("image/jpeg", "image/png", "image/jpg")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        imageFromGallery.launch(intent)
    }
}