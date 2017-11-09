package kmitl.kawin58070006.horyuni;


import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private ImageView imageView;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private EditText editText;
    private EditText editTextZone;
    private EditText editTextMoreDetai;
    private Uri imguri1;
    private Uri imguri2;
    private Uri imguri3;
    private Uri imguri4;
    private Uri imguri5;
    private Uri imguri6;
    private Button btnBrowse_Click;
    private Button btnUpload;
    private Button btnShowListImage;

    public static final String FB_Storage_Path = "image/";
    public static final String FB_Database_Path = "image";
    public static final int Request_Code = 1234;
    public static final int Request_Code2 = 2;
    public static final int Request_Code3 = 3;
    public static final int Request_Code4 = 4;
    public static final int Request_Code5 = 5;
    public static final int Request_Code6 = 6;

    private List<Uri> listUri = new ArrayList<>();
    private List<String> listUriString = new ArrayList<>();
    private List<StorageReference> storageReferencesList = new ArrayList<>();

    private int numCheck = 0;
    private int numCheckList = 0;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(FB_Database_Path);

        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        imageView2 = (ImageView) rootView.findViewById(R.id.imageView2);
        imageView3 = (ImageView) rootView.findViewById(R.id.imageView3);
        imageView4 = (ImageView) rootView.findViewById(R.id.imageView4);
        imageView5 = (ImageView) rootView.findViewById(R.id.imageView5);
        imageView6 = (ImageView) rootView.findViewById(R.id.imageView6);
        editText = (EditText) rootView.findViewById(R.id.txtImageName);
        editTextZone = (EditText) rootView.findViewById(R.id.txtZone);
        editTextMoreDetai = (EditText) rootView.findViewById(R.id.txtMoreDetail);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImg(Request_Code);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImg(Request_Code2);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImg(Request_Code3);
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImg(Request_Code4);
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImg(Request_Code5);
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImg(Request_Code6);
            }
        });

        btnUpload = rootView.findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imguri1 != null) {
                    final ProgressDialog dialog = new ProgressDialog(getActivity());
                    dialog.setTitle("Uploading image");
                    dialog.show();

                    //Get the storage reference
                    StorageReference ref = storageReference.child(FB_Storage_Path + System.currentTimeMillis() + "." + getImageExt(imguri1));
                    StorageReference ref2 = storageReference.child(FB_Storage_Path + System.currentTimeMillis() + "." + getImageExt(imguri2));
                    StorageReference ref3 = storageReference.child(FB_Storage_Path + System.currentTimeMillis() + "." + getImageExt(imguri3));
                    StorageReference ref4 = storageReference.child(FB_Storage_Path + System.currentTimeMillis() + "." + getImageExt(imguri4));
                    StorageReference ref5 = storageReference.child(FB_Storage_Path + System.currentTimeMillis() + "." + getImageExt(imguri5));
                    StorageReference ref6 = storageReference.child(FB_Storage_Path + System.currentTimeMillis() + "." + getImageExt(imguri6));
                    storageReferencesList.add(ref);
                    storageReferencesList.add(ref2);
                    storageReferencesList.add(ref3);
                    storageReferencesList.add(ref4);
                    storageReferencesList.add(ref5);
                    storageReferencesList.add(ref6);


                    //Add file to reference
                    for (int i = 0; i < storageReferencesList.size(); i++) {
                        storageReferencesList.get(i).putFile(listUri.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                numCheck++;
                                //Dimiss dialog when success
                                dialog.dismiss();
                                //Display success toast msg
                                Toast.makeText(getContext(), "Image uploaded", Toast.LENGTH_SHORT).show();
                                listUriString.add(taskSnapshot.getDownloadUrl().toString());
                                if (numCheck == storageReferencesList.size()) {
                                    ImageUpload imageUpload = new ImageUpload(editText.getText().toString(), editTextZone.getText().toString(), editTextMoreDetai.getText().toString(), listUriString.get(5), listUriString.get(4), listUriString.get(3), listUriString.get(2), listUriString.get(1), listUriString.get(0));
                                    //Save image info in to firebase database
                                    String uploadId = databaseReference.push().getKey();
                                    databaseReference.child(uploadId).setValue(imageUpload);
                                }
                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        //Dimiss dialog when error
                                        dialog.dismiss();
                                        //Display err toast msg
                                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                                    @Override
                                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                                        //Show upload progress

                                        double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                        dialog.setMessage("Uploaded " + (int) progress + "%");
                                    }
                                });
                    }
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragmentContainer, HomeFragment.newInstance())
                            .addToBackStack(null)
                            .commit();
                } else {
                    Toast.makeText(getContext(), "Please select image", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnShowListImage = rootView.findViewById(R.id.btnShowListImage);
        btnShowListImage.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, HomeFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        setImguri(requestCode, resultCode, data, Request_Code, imguri1, imageView);
//        setImguri(requestCode, resultCode, data, Request_Code2, imguri2, imageView2);
//        setImguri(requestCode, resultCode, data, Request_Code3, imguri3, imageView3);
//        setImguri(requestCode, resultCode, data, Request_Code4, imguri4, imageView4);
//        setImguri(requestCode, resultCode, data, Request_Code5, imguri5, imageView5);
        setImguri(requestCode, resultCode, data, Request_Code6, imguri6, imageView6);
        if (requestCode == Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri1 = data.getData();
            listUri.add(imguri1);

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imguri1);
                imageView.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == Request_Code2 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri2 = data.getData();
            listUri.add(imguri2);

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imguri2);
                imageView2.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == Request_Code3 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri3 = data.getData();
            listUri.add(imguri3);

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imguri3);
                imageView3.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == Request_Code4 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri4 = data.getData();
            listUri.add(imguri4);

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imguri4);
                imageView4.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == Request_Code5 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri5 = data.getData();
            listUri.add(imguri5);

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imguri5);
                imageView5.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == Request_Code6 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri6 = data.getData();
            listUri.add(imguri6);

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imguri6);
                imageView6.setImageBitmap(bm);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public String getImageExt(Uri uri) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void selectImg(final int Request_Code) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select image"), Request_Code);
    }

    private void setImguri(int requestCode, int resultCode, Intent data, int Request_Code, Uri UriImg, ImageView imageView) {
//        if (requestCode == Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            UriImg = data.getData();
//            listUri.add(UriImg);
//
//            try {
//                Bitmap bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), UriImg);
//                imageView.setImageBitmap(bm);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }


}
