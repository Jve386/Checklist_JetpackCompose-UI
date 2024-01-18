# Checklist w/ Jetpack Compose UI :rocket:

## :information_source: Overview

Simple Android application built using Jetpack Compose. 
Input names, add them to a list, and display the list with checkboxes next to each name.

## :gear: Key Features

- **User Input**: Utilizes an `OutlinedTextField` for users to input names.
- **List Display**: Displays a list of names using `LazyColumn` with each name accompanied by a checkbox.
- **Checkbox Interaction**: Users can toggle the checkbox to mark/unmark names in the list.
- **Dynamic UI**: The UI updates in real-time as users interact with the app.


## :computer: Code Snippets

### MainActivity
```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeUITheme {
                var name by remember {
                    mutableStateOf("")
                }
                var names by remember {
                    mutableStateOf(listOf<String>())
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { text ->
                                name = text
                            },
                            modifier = Modifier
                                .weight(1f)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(onClick = {
                            if (name.isNotBlank()) {
                                names = names + name
                                name = ""
                            }
                        }) {
                            Text(text = "Add")
                        }
                    }
                    NameList(names = names)
                }
            }
        }
    }
}
```
### NameList Composable
```kotlin
@Composable
fun NameList(
    names: List<String>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(names) { currentName ->
            val isChecked = remember { mutableStateOf(false) }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = {
                        isChecked.value = it
                    }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = currentName,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Divider()
        }
    }
}
```

Note: Commented code inside.

## :camera: Output
![Jetpack Compose Image](/img/JetPackCompose.jpg)