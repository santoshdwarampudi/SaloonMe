package com.saloonme.ui.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;


import com.saloonme.R;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FilePickerBottomSheetFragment
        extends BaseBottomSheetDialogFragment
        implements View.OnClickListener {

    private static final String TAG = "FilePickerBottomSheetFragment";

    public static final String FOLDER_NAME = "cache";

    public static final int FILE_REQUEST_CODE = 200;
    public static final int FILE_ASK_MULTIPLE_PERMISSION_REQUEST_CODE = 13;

    private View view;

    private PhotoFileListener mPhotoFileListener;

    public boolean fileAllowed = false;

    public static FilePickerBottomSheetFragment start(PhotoFileListener photoFileListener) {
        FilePickerBottomSheetFragment filePickerBottomSheetFragment = new FilePickerBottomSheetFragment();
        filePickerBottomSheetFragment.mPhotoFileListener = photoFileListener;
        return filePickerBottomSheetFragment;
    }

    public static FilePickerBottomSheetFragment start(PhotoFileListener photoFileListener,
                                                      boolean isWithFiles) {
        FilePickerBottomSheetFragment filePickerBottomSheetFragment
                = FilePickerBottomSheetFragment.start(photoFileListener);
        filePickerBottomSheetFragment.fileAllowed = isWithFiles;
        return filePickerBottomSheetFragment;
    }

    public void display(FragmentManager fragmentManager) {
        this.show(fragmentManager, TAG);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment_file_picker_bottom_sheet, null);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onViewCreated( @NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.gallery).setOnClickListener(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            dismiss();
            return;
        }
        switch (requestCode) {
            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri resultUri = result.getUri();
                MultipartBody.Part galFile = prepareFileForUpload(resultUri.getPath());
                mPhotoFileListener.photoFile(galFile, resultUri, resultUri.getPath(),
                        "image/jpg", ".jpg");
                dismiss();
                break;
            case FILE_REQUEST_CODE:
                File file = onFileActivityResult(requestCode, resultCode, data);
                if (null == file) return;
                MultipartBody.Part pdfFile = prepareFileForUpload(file.getPath());
                mPhotoFileListener.photoFile(pdfFile, Uri.fromFile(file), file.getPath(),
                        "application/pdf", ".pdf");
                dismiss();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == FILE_ASK_MULTIPLE_PERMISSION_REQUEST_CODE) {
            startFileIntentWithPermissions();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gallery:
                if (null == getContext()) return;
                CropImage.activity()
                        .setOutputCompressFormat(CompressFormat.JPEG)
                        .setOutputCompressQuality(80)
                        .setCropMenuCropButtonTitle("OK")
                        .setAllowFlipping(false)
                        .start(getContext(), this);
                break;

        }
    }

    public MultipartBody.Part prepareFileForUpload(String filepath) {
        if (filepath == null) return null;
        File originalFile = new File(filepath);
        RequestBody filePart = RequestBody.create(originalFile, MediaType.parse("multipart/form-data"));
        return MultipartBody.Part.createFormData("file", originalFile.getName(), filePart);
    }

    public interface PhotoFileListener {
        void photoFile(MultipartBody.Part file, Uri uri, String compressedFilePath, String mimeType, String extension);
    }


    public void chooseFile() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        startActivityForResult(intent, FilePickerBottomSheetFragment.FILE_REQUEST_CODE);
    }

    public void startFileIntentWithPermissions() {
        if (getContext() == null) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                chooseFile();
            else {
                requestPermissions(new String[]{Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        FilePickerBottomSheetFragment.FILE_ASK_MULTIPLE_PERMISSION_REQUEST_CODE);
            }

        } else chooseFile();
    }

    public File onFileActivityResult(int requestCode, int resultCode, Intent data) {
        if (null == getContext()
                || requestCode != FilePickerBottomSheetFragment.FILE_REQUEST_CODE
                || resultCode != Activity.RESULT_OK
                || data == null) {
            return null;
        }
        File file = new File(getContext().getCacheDir(), System.currentTimeMillis() + ".pdf");
        Uri selectedImage = data.getData();
        if (null == selectedImage) return null;
        try {
            InputStream in = getContext().getContentResolver().openInputStream(selectedImage);
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}