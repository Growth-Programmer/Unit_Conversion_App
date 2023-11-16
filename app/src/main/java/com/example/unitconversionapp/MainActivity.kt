package com.example.unitconversionapp

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconversionapp.ui.theme.UnitConversionAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConversionAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    UnitConverter()
                }
            }
        }
    }


    @Composable
    fun UnitConverter() {

        var inputValue by remember { mutableStateOf("") }
        var outputValue by remember { mutableStateOf("0.0") }
        var inputUnit by remember { mutableStateOf("Input") }
        var outputUnit by remember { mutableStateOf("Output") }
        var resultUnit by remember { mutableStateOf("") }
        var iExpanded by remember { mutableStateOf(false) }
        var oExpanded by remember { mutableStateOf(false) }
        val conversionFactor = remember { mutableStateOf(1.00) }
        val outputConversionFactor = remember { mutableStateOf(1.00) }

        fun convertUnits() {

            // ?: elvis operator
            val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
            val result =
                ((inputValueDouble * conversionFactor.value) * (100.0 / outputConversionFactor.value)).roundToInt() / 100.0
            outputValue = result.toString()
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text("Unit Converter",
                style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it
                    convertUnits()
                },
                label = { Text("Enter Value") })
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                //Input Select Button
                Box {
                    Button(onClick = { iExpanded = true }) {
                        Text("${inputUnit}")
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = ""
                        )
                    }
                    DropdownMenu(
                        expanded = iExpanded,
                        onDismissRequest = { iExpanded = false })

                    {
                        DropdownMenuItem(
                            text = { Text("Centimeters") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Centimeters"
                                conversionFactor.value = 0.01
                                convertUnits()
                            })

                        DropdownMenuItem(
                            text = { Text("Inches") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Inches"
                                conversionFactor.value = 0.0254
                                convertUnits()
                            })

                        DropdownMenuItem(
                            text = { Text("Feet") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Feet"
                                conversionFactor.value = 0.3048
                                convertUnits()
                            })

                        DropdownMenuItem(
                            text = { Text("Meters") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Meters"
                                conversionFactor.value = 1.0
                                convertUnits()
                            })
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                //Output button selector
                Box {
                    Button(onClick = { oExpanded = true }) {
                        Text("${outputUnit}")
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = ""
                        )
                    }
                    DropdownMenu(
                        expanded = oExpanded,
                        onDismissRequest = { oExpanded = false })
                    {
                        DropdownMenuItem(
                            text = { Text("Centimeters") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Centimeters"
                                resultUnit = "Centimeters"
                                outputConversionFactor.value = 0.01
                                convertUnits()
                            })

                        DropdownMenuItem(
                            text = { Text("Inches") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Inches"
                                resultUnit = "Inches"
                                outputConversionFactor.value = 0.0254
                                convertUnits()
                            })

                        DropdownMenuItem(
                            text = { Text("Feet") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Feet"
                                resultUnit = "Feet"
                                outputConversionFactor.value = 0.3048
                                convertUnits()
                            })

                        DropdownMenuItem(
                            text = { Text("Meters") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "Meters"
                                resultUnit = "Meters"
                                outputConversionFactor.value = 1.00
                                convertUnits()
                            })
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Result: ${outputValue} ${resultUnit}",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun UnitConverterPreview() {
        UnitConverter()

    }
}