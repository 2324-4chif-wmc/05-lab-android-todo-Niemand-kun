package at.htl.todo;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.ComponentActivity;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;

import javax.inject.Inject;

import at.htl.todo.ui.layout.MainView;
import at.htl.todo.util.Config;
import dagger.hilt.android.AndroidEntryPoint;

/** Our main activity implemented in java.
 * We separate the application logic from the view. This is our controller.
 * The View is implemented in Jetpack compose in a separate file (separation of concerns).
 * For the views we use Kotlin in order to keep the nice design preview.
 */
@AndroidEntryPoint
public class MainActivity extends ComponentActivity {
    public static final String TAG = MainActivity.class.getName();

    @Inject
    MainView mainView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Config.load(this);
        String baseUrl = Config.getProperty("json.placeholder.baseurl");

        if (baseUrl == null) {
            Log.e(TAG, "Base URL is not defined.");
        } else {
            Log.i(TAG, "onCreate - Base URL: " + baseUrl);
        };

        mainView.buildContent(this);
    }
}