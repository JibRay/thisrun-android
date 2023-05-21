package com.jib.ThisRun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jib.ThisRun.ui.theme.ThisRunTheme

enum class Mode {
    WALKING, RUNNING
}

val labelFontSize = 20.sp
val valueFontSize = 38.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThisRunTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth(), content = {
                            // This column contains displayed values.
                            Column {
                                // This row contains average pace pace and last mile.
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Column(modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()) {
                                        DisplayMinutesSeconds("Average Pace", 965.0)
                                    }
                                    Column(modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()) {
                                        DisplayMinutesSeconds("Last Mile", 861.0)
                                    }
                                }

                                // This row contains elapse time and estimated time to finish.
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Column(modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()) {
                                        DisplayHoursMinutesSeconds("Elapsed Time", 3681.0)
                                    }
                                    Column(modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()) {
                                        DisplayHoursMinutesSeconds("Finish Time", 12658.0)
                                    }
                                }

                                // This row contains distance and finish distance.
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Column(modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()) {
                                        DisplayMiles("Distance", 4.53)
                                    }
                                    Column(modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()) {
                                        DisplayMiles("Finish Distance", 26.2)
                                    }
                                }

                                // This row contains walking pace and running pace.
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Column(modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()) {
                                        DisplayMinutesSeconds("Walking Pace", 1065.0)
                                    }
                                    Column(modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()) {
                                        DisplayMinutesSeconds("Running Pace", 761.0)
                                    }
                                }
                                DisplayMode(mode = Mode.WALKING, timeRemaining = 29.0)
                            }
                        }
                    )
                    //Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun DisplayMode(mode: Mode, timeRemaining: Double) {
    val modeText: String
    var color: Color = Color.Red
    when(mode) {
        Mode.RUNNING -> { modeText = "Running"; color = Color(0xFF008000) } // Dark green.
        Mode.WALKING -> { modeText = "Walking"; color = Color(0xFF900000) } // Dark red.
    }
    Text(
        String.format("%s: %s", modeText, secondsToMinutesSecondsString(timeRemaining)),
        color = color,
        textAlign = TextAlign.Center,
        fontSize = 42.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.border(BorderStroke(3.dp, Color.Gray)).fillMaxWidth())
}

// Display a time value as minutes:seconds. valueLabel is displayed along the top. value
// is in seconds.
@Composable
fun DisplayMinutesSeconds(valueLabel: String, value: Double) {
    Column(modifier =
        Modifier.border(BorderStroke(3.dp, Color.Gray)).fillMaxWidth(),
           horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            valueLabel, fontSize = labelFontSize, fontWeight = FontWeight.Bold
        )
        Text(
            secondsToMinutesSecondsString(value), fontSize = valueFontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

// Display a time value as hours:minutes:seconds. valueLabel is displayed along the top. value
// is in seconds.
@Composable
fun DisplayHoursMinutesSeconds(valueLabel: String, value: Double) {
    var t = (value + 0.5).toInt()
    val hours = t / 3600
    t -= hours * 3600
    val minutes = t / 60
    val seconds = t % 60
    val valueText = String.format("%2d:%02d:%02d", hours, minutes, seconds)
    Column(modifier = Modifier.border(BorderStroke(3.dp, Color.Gray)).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            valueLabel, fontSize = labelFontSize, fontWeight = FontWeight.Bold
        )
        Text(
            valueText, fontSize = valueFontSize, fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun DisplayMiles(valueLabel: String, value: Double) {
    val valueText = String.format("%.2f mi", value) // FIXME: %0.2f crashes.
    Column(modifier = Modifier.border(BorderStroke(3.dp, Color.Gray)).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            valueLabel, fontSize = labelFontSize, fontWeight = FontWeight.Bold
        )
        Text(
            valueText, fontSize = valueFontSize, fontWeight = FontWeight.Bold
        )
    }
}

fun secondsToMinutesSecondsString(time: Double): String {
    val t = (time + 0.5).toInt()
    val minutes = t / 60
    val seconds = t % 60
    return String.format("%02d:%02d", minutes, seconds)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ThisRunTheme {
        Greeting("Android")
    }
}