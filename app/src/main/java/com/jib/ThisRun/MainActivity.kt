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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jib.ThisRun.ui.theme.ThisRunTheme

/*
Main view components:
Information:
  Run average pace
  Last mile average pace
  Run elapse time
  Mode: run/walk
  Run pace
  Walk pace
 */
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
                            Column() {
                                // This row contains last mile pace and average pace.
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
                                        DisplayMinutesSeconds("Last Mile", 965.0)
                                    }
                                    Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
                                        DisplayMinutesSeconds("Average Pace", 861.0)
                                    }
                                }

                                // This row contains elapse time and estimated time to finish.
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
                                        DisplayHoursMinutesSeconds("Elapsed Time", 3681.0)
                                    }
                                    Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
                                        DisplayHoursMinutesSeconds("Finish Time", 12658.0)
                                    }
                                }

                                // This row contains walking pace and running pace.
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
                                        DisplayMinutesSeconds("Walking Pace", 965.0)
                                    }
                                    Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
                                        DisplayMinutesSeconds("Running Pace", 861.0)
                                    }
                                }
                            }
                        }
                    )
                    //Greeting("Android")
                }
            }
        }
    }
}

// Display a time value as minutes:seconds. valueLabel is displayed along the top. value
// is in seconds.
@Composable
fun DisplayMinutesSeconds(valueLabel: String, value: Double) {
    val t = (value + 0.5).toInt()
    val minutes = t / 60
    val seconds = t % 60
    val valueText = String.format("%02d:%02d", minutes, seconds)
    Column(modifier = Modifier.border(BorderStroke(3.dp, Color.Blue)).fillMaxWidth(),
           horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            valueLabel, fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            valueText, fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun DisplayHoursMinutesSeconds(valueLabel: String, value: Double) {
    var t = (value + 0.5).toInt()
    val hours = t / 3600
    t -= hours * 3600
    val minutes = t / 60
    val seconds = t % 60
    val valueText = String.format("%2d:%02d:%02d", hours, minutes, seconds)
    Column(modifier = Modifier.border(BorderStroke(3.dp, Color.Blue)).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            valueLabel, fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            valueText, fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ThisRunTheme {
        Greeting("Android")
    }
}