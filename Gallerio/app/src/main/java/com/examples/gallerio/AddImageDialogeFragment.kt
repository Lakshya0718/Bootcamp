package com.examples.gallerio

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.examples.gallerio.viewModel.FirebaseViewModel
import java.io.ByteArrayOutputStream


class AddImageDialogeFragment : DialogFragment() {

    private lateinit var mViewModel: FirebaseViewModel

    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mViewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)

        val positionBundle: Bundle? = arguments
        val categoryName: String = positionBundle?.get("categoryName") as String
        val rootView: View = inflater.inflate(R.layout.fragment_add_image_dialoge, container, false)

        val takePhotoBtn: AppCompatButton = rootView.findViewById(R.id.takePhoto)
        val selectPhotoBtn: AppCompatButton = rootView.findViewById(R.id.selectImage)

        val addBtn: AppCompatButton = rootView.findViewById(R.id.addDialogBtn)
        val cancelBtn: AppCompatButton = rootView.findViewById(R.id.cancelDialogBtn)

        cancelBtn.setOnClickListener {
            dialog?.dismiss()
        }

        takePhotoBtn.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 1)
            takePhotoBtn.isEnabled = false
            selectPhotoBtn.isEnabled = false
        }

        selectPhotoBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 2)
            selectPhotoBtn.isEnabled = false
            takePhotoBtn.isEnabled = false
        }

        addBtn.setOnClickListener {
            val imageName = "${getRandomString(10)}+.jpg"
            imageUri?.let { it1 -> mViewModel.addNewImage(categoryName, it1, imageName)
                Toast.makeText(MainActivity(), "Image Added", Toast.LENGTH_SHORT).show()}
        }

        return rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            when(requestCode){
                1 -> {
                        //code Working
//                        var photo: Bitmap = data?.extras?.get("data") as Bitmap
                    val photo: Bitmap = data?.extras?.get("data") as Bitmap
                    imageUri = getImageUri(context, photo)

                }
                2 -> {
                    imageUri = data?.data!!
                }
            }
        }
    }

    private fun getImageUri(context: Context?, photo: Bitmap): Uri {
        val bytes: ByteArrayOutputStream = ByteArrayOutputStream()
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path: String = MediaStore.Images.Media.insertImage(context?.contentResolver, photo, getRandomString(8), null)
        val image = Uri.parse(path)
        return image

    }

    fun getRandomString(length: Int) : String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    override fun onStart() {
        super.onStart()

        var dialog = dialog
        if (dialog!=null){
            val width: Int = ViewGroup.LayoutParams.MATCH_PARENT
            val height: Int = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width,height)
        }
    }
}
