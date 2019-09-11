package co.devbeerloper.takingphotoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // ID of the request to deal with it
    static final int REQUEST_TAKE_PHOTO = 1;

    //
    private ImageView imageTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageTaken = findViewById(R.id.ivTakenPhoto);

    }


    /**
     * Create an intent to invoke the camera and capture an image
     * @param view
     */
    public void takePicture (View view){

        // Show a toast to the user
        Toast.makeText(this, "Taking picture", Toast.LENGTH_LONG).show();

        // Create the intent and send the request of the image capture action
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }


    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Verify if the request comes from a request take photo action and if the response was successful
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {

            // Show a toast to the user indicating that the image capture action was completed
            Toast.makeText(this, "Here is your picture", Toast.LENGTH_LONG).show();
            // Obtain the data from the request
            Bundle extras = data.getExtras();
            // Convert the data to a bitmap
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            // Set the bitmap image to the view
            imageTaken.setImageBitmap(imageBitmap);
        }


    }
}
