package com.example.ddcharacterapp

import android.app.Activity
import android.app.BackgroundServiceStartNotAllowedException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
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
import org.w3c.dom.Text

class TraitsFragment : Fragment() {

    private var characterImage : Uri? = null
    var name = ""
    var characterClass = ""
    var level = ""
    var race = ""
    var alignment = ""
    var personality = ""
    var ideals = ""
    var bonds = ""
    var flaws = ""
    var background = ""

    lateinit var nameEditText : EditText
    lateinit var classEditText : EditText
    lateinit var levelEditText : EditText
    lateinit var raceEditText : EditText
    lateinit var alignmentEditText : EditText
    lateinit var personalityEditText: EditText
    lateinit var idealsEditText: EditText
    lateinit var bondsEditText: EditText
    lateinit var flawsEditText: EditText
    lateinit var backgroundEditText: EditText

    /*
    var nameTW = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            if (p0 != null) {
                name = p0.toString()
                Log.d("name2", name)
            }
        }
    }

     */

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

        mainAct.supportActionBar?.title = mainAct.characterListGlobal[mainAct.characterIndex].charName

        nameEditText = view.findViewById<EditText>(R.id.charcter_name_editText)
        nameEditText.isSaveEnabled = false
        //nameEditText.text = Editable.Factory.getInstance().newEditable(name)
        Log.d("name", name)
        nameEditText.setText(name)
        //nameEditTextTest = nameEditText
        Log.d("name2", name)

        //nameEditText.addTextChangedListener(nameTW)
        /*
        nameEditText.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    name = p0.toString()
                    Log.d("name2", name)
                }
            }
        })

         */


        classEditText = view.findViewById<EditText>(R.id.class_editText)
        classEditText.isSaveEnabled = false
        //classEditText.text = Editable.Factory.getInstance().newEditable(characterClass)
        classEditText.setText(characterClass)
        /*
        classEditText.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    characterClass = p0.toString()
                }
            }
        })
        */

        levelEditText = view.findViewById<EditText>(R.id.level_editText)
        //levelEditText.text = Editable.Factory.getInstance().newEditable(level)
        levelEditText.isSaveEnabled = false
        levelEditText.setText(level)
        /*
        levelEditText.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    level = p0.toString()
                }
            }
        })

         */

        raceEditText = view.findViewById<EditText>(R.id.race_editText)
        //raceEditText.text = Editable.Factory.getInstance().newEditable(race)
        raceEditText.isSaveEnabled = false
        raceEditText.setText(race)
        /*
        raceEditText.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    race = p0.toString()
                }
            }
        })
         */

        alignmentEditText = view.findViewById<EditText>(R.id.alignment_editText)
        //alignmentEditText.text = Editable.Factory.getInstance().newEditable(alignment)
        alignmentEditText.isSaveEnabled = false
        alignmentEditText.setText(alignment)
        /*
        alignmentEditText.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    alignment = p0.toString()
                }
            }
        })

         */

        personalityEditText = view.findViewById<EditText>(R.id.personality_editText)
        //personalityEditText.text = Editable.Factory.getInstance().newEditable(personality)
        personalityEditText.isSaveEnabled = false
        personalityEditText.setText(personality)
        /*
        personalityEditText.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    personality = p0.toString()
                }
            }
        })

         */

        idealsEditText = view.findViewById<EditText>(R.id.ideals_editText)
        //idealsEditText.text = Editable.Factory.getInstance().newEditable(ideals)
        idealsEditText.isSaveEnabled = false
        idealsEditText.setText(ideals)
        /*
        idealsEditText.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    ideals = p0.toString()
                }
            }
        })

         */

        bondsEditText = view.findViewById<EditText>(R.id.bonds_editText)
        //bondsEditText.text = Editable.Factory.getInstance().newEditable(bonds)
        bondsEditText.isSaveEnabled = false
        bondsEditText.setText(bonds)
        /*
        bondsEditText.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    bonds = p0.toString()
                }
            }
        })

         */

        flawsEditText = view.findViewById<EditText>(R.id.flaws_editText)
        //flawsEditText.text = Editable.Factory.getInstance().newEditable(flaws)
        flawsEditText.isSaveEnabled = false
        flawsEditText.setText(flaws)
        /*
        flawsEditText.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    flaws = p0.toString()
                }
            }
        })

         */

        backgroundEditText = view.findViewById<EditText>(R.id.background_editText)
        //backgroundEditText.text = Editable.Factory.getInstance().newEditable(background)
        backgroundEditText.isSaveEnabled = false
        backgroundEditText.setText(background)
        /*
        backgroundEditText.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    background = p0.toString()
                }
            }
        })

         */

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