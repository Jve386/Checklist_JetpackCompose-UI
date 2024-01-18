package com.jve386.jetpackcomposeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jve386.jetpackcomposeui.ui.theme.JetpackComposeUITheme

// MainActivity class responsible for the main activity in the application
class MainActivity : ComponentActivity() {
    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        // Call the superclass implementation of onCreate
        super.onCreate(savedInstanceState)

        // Set the content of the activity using Jetpack Compose
        setContent {
            // Apply the JetpackComposeUITheme to the content
            JetpackComposeUITheme {
                // State to hold the current input name
                var name by remember {
                    mutableStateOf("")
                }

                // State to hold the list of names
                var names by remember {
                    mutableStateOf(listOf<String>())
                }

                // Column layout to organize UI elements vertically
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Row layout to organize UI elements horizontally
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Input field for entering a name
                        OutlinedTextField(
                            value = name,
                            onValueChange = { text ->
                                name = text
                            },
                            modifier = Modifier
                                .weight(1f)
                        )

                        // Spacer to add some space between the input field and the button
                        Spacer(modifier = Modifier.width(16.dp))

                        // Button to add a name to the list
                        Button(onClick = {
                            if (name.isNotBlank()) {
                                names = names + name
                                name = ""
                            }
                        }) {
                            Text(text = "Add")
                        }
                    }

                    // Display the list of names using the NameList composable
                    NameList(names = names)
                }
            }
        }
    }
}

// Composable function to display a list of names with checkboxes
@Composable
fun NameList(
    names: List<String>,
    modifier: Modifier = Modifier
) {
    // LazyColumn to efficiently display a scrolling list
    LazyColumn(modifier) {
        // Iterate through the list of names
        items(names) { currentName ->
            // State to hold the checked state of the checkbox
            val isChecked = remember { mutableStateOf(false) }

            // Row layout to organize UI elements horizontally
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Checkbox to toggle the checked state
                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = {
                        isChecked.value = it
                    }
                )

                // Spacer to add some space between the checkbox and the text
                Spacer(modifier = Modifier.width(16.dp))

                // Text to display the current name
                Text(
                    text = currentName,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            // Divider to separate each row in the list
            Divider()
        }
    }
}