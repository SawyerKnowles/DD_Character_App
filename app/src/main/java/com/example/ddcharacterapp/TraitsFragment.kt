package com.example.ddcharacterapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.*

class TraitsFragment : Fragment() {

    private var characterImage : Uri? = null
    var name = ""
    var characterClass = ""
    var level = ""
    private var race = ""
    private var alignment = ""
    private var personality = ""
    private var ideals = ""
    private var bonds = ""
    private var flaws = ""
    private var background = ""

    private lateinit var nameEditText : EditText
    private lateinit var classEditText : EditText
    private lateinit var levelEditText : EditText
    private lateinit var raceEditText : EditText
    private lateinit var alignmentEditText : EditText
    private lateinit var personalityEditText: EditText
    private lateinit var idealsEditText: EditText
    private lateinit var bondsEditText: EditText
    private lateinit var flawsEditText: EditText
    private lateinit var backgroundEditText: EditText

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
        return inflater.inflate(R.layout.fragment_traits, container, false)
    }

    override fun onDetach() {
        Log.d("TraitsFragment", "TraitsFragment destroyed.")
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = (activity as AppCompatActivity) // get activity

        readFromDataManager()

        val image = view.findViewById<ImageView>(R.id.character_image)
        image.setImageURI(characterImage)

        Log.d("TraitsFragment", "TraitsFragment created.")
        val mainAct =  (activity as MainActivity)

        nameEditText = view.findViewById(R.id.charcter_name_editText)
        nameEditText.isSaveEnabled = false
        nameEditText.setText(name)


        classEditText = view.findViewById(R.id.class_editText)
        classEditText.isSaveEnabled = false
        classEditText.setText(characterClass)

        levelEditText = view.findViewById(R.id.level_editText)
        levelEditText.isSaveEnabled = false
        levelEditText.setText(level)

        raceEditText = view.findViewById(R.id.race_editText)
        raceEditText.isSaveEnabled = false
        raceEditText.setText(race)

        alignmentEditText = view.findViewById(R.id.alignment_editText)
        alignmentEditText.isSaveEnabled = false
        alignmentEditText.setText(alignment)

        personalityEditText = view.findViewById(R.id.personality_editText)
        personalityEditText.isSaveEnabled = false
        personalityEditText.setText(personality)

        idealsEditText = view.findViewById(R.id.ideals_editText)
        idealsEditText.isSaveEnabled = false
        idealsEditText.setText(ideals)

        bondsEditText = view.findViewById(R.id.bonds_editText)
        bondsEditText.isSaveEnabled = false
        bondsEditText.setText(bonds)

        flawsEditText = view.findViewById(R.id.flaws_editText)
        flawsEditText.isSaveEnabled = false
        flawsEditText.setText(flaws)

        backgroundEditText = view.findViewById(R.id.background_editText)
        backgroundEditText.isSaveEnabled = false
        backgroundEditText.setText(background)

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

    private fun readFromDataManager() {
        val mainAct =  (activity as MainActivity)

        characterImage = mainAct.dataManager.traitsData.basicData.picture.toUri()

        name = mainAct.dataManager.traitsData.basicData.name

        characterClass = mainAct.dataManager.traitsData.basicData.characterClass

        level = mainAct.dataManager.traitsData.basicData.level

        race = mainAct.dataManager.traitsData.basicData.race

        alignment = mainAct.dataManager.traitsData.basicData.alignment

        personality = mainAct.dataManager.traitsData.complexData.personality

        ideals = mainAct.dataManager.traitsData.complexData.ideals

        bonds = mainAct.dataManager.traitsData.complexData.bonds

        flaws = mainAct.dataManager.traitsData.complexData.flaws

        background = mainAct.dataManager.traitsData.complexData.background
    }

    private fun writeToDataManager() {
        val mainAct =  (activity as MainActivity)
        mainAct.dataManager.traitsData.basicData.picture = characterImage.toString()

        name = nameEditText.text.toString()
        Log.d("name3", name)
        //nameEditText.removeTextChangedListener(nameTW)
        mainAct.dataManager.traitsData.basicData.name = name

        characterClass = classEditText.text.toString()
        mainAct.dataManager.traitsData.basicData.characterClass = characterClass

        level = levelEditText.text.toString()
        mainAct.dataManager.traitsData.basicData.level = level

        race = raceEditText.text.toString()
        mainAct.dataManager.traitsData.basicData.race = race

        alignment = alignmentEditText.text.toString()
        mainAct.dataManager.traitsData.basicData.alignment = alignment

        personality = personalityEditText.text.toString()
        mainAct.dataManager.traitsData.complexData.personality = personality

        ideals = idealsEditText.text.toString()
        mainAct.dataManager.traitsData.complexData.ideals = ideals

        bonds = bondsEditText.text.toString()
        mainAct.dataManager.traitsData.complexData.bonds = bonds

        flaws = flawsEditText.text.toString()
        mainAct.dataManager.traitsData.complexData.flaws = flaws

        background = backgroundEditText.text.toString()
        mainAct.dataManager.traitsData.complexData.background = background
    }

    override fun onPause() {
        Log.d("TraitsFragment", "onPause Called.")
        writeToDataManager()
        super.onPause()
    }
}