package com.amlan.rapidsplit.ui.presentation.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.amlan.rapidsplit.R
import com.amlan.rapidsplit.navigation.Screen
import com.amlan.rapidsplit.ui.theme.*
import org.koin.androidx.compose.get

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = get()
) {
    var phoneNumber by remember { mutableStateOf("") }
    var showOtpField by remember { mutableStateOf(false) }
    var otpValue by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RapidoWhite)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // App Logo
            Spacer(modifier = Modifier.height(60.dp))
            Image(
                painter = painterResource(id = R.drawable.rapido_logo),
                contentDescription = "Rapido Logo",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "RapidSplit",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = RapidoBlack
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Book rides & split fares easily",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = RapidoGrey
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Login Form
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = RapidoWhite)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Login or Signup",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Phone Input Field
                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = {
                            if (it.length <= 10 && it.all { char -> char.isDigit() }) {
                                phoneNumber = it
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Phone Number") },
                        leadingIcon = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "+91",
                                    modifier = Modifier.padding(start = 12.dp),
                                    fontWeight = FontWeight.Medium
                                )
                                Spacer(
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                        .width(1.dp)
                                        .height(24.dp)
                                        .background(RapidoLightGrey.copy(alpha = 0.5f))
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Done
                        ),
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = RapidoYellow,
                            unfocusedBorderColor = RapidoLightGrey
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // OTP Field
                    if (showOtpField) {
                        OutlinedTextField(
                            value = otpValue,
                            onValueChange = {
                                if (it.length <= 6 && it.all { char -> char.isDigit() }) {
                                    otpValue = it
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text("Enter OTP") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = RapidoYellow,
                                unfocusedBorderColor = RapidoLightGrey
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Resend OTP",
                            color = RapidoYellow,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Continue Button
                    Button(
                        onClick = {
                            if (!showOtpField) {
                                showOtpField = true
                            } else {
                                viewModel.email = phoneNumber // Just for demo purposes
                                viewModel.login()
                                if (viewModel.isLoggedIn) {
                                    navController.navigate(Screen.Home.route) {
                                        popUpTo(Screen.Login.route) { inclusive = true }
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = RapidoYellow,
                            contentColor = RapidoBlack
                        )
                    ) {
                        Text(
                            text = if (showOtpField) "Verify OTP" else "Continue",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Line separator
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp),
                    color = RapidoLightGrey.copy(alpha = 0.5f)
                )
                Text(
                    text = "OR",
                    color = RapidoGrey,
                    fontSize = 14.sp
                )
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp),
                    color = RapidoLightGrey.copy(alpha = 0.5f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Social Login Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Google Sign In
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(width = 1.dp, color = RapidoLightGrey),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = RapidoBlack
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google_icon),
                            contentDescription = "Google",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Google")
                    }
                }

                // Apple Sign In
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(width = 1.dp, color = RapidoLightGrey),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = RapidoBlack
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.apple_icon),
                            contentDescription = "Apple",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Apple")
                    }
                }
            }
        }

        // Terms and Privacy Policy
        Text(
            text = "By continuing, you agree to our Terms of Service and Privacy Policy",
            style = MaterialTheme.typography.bodySmall.copy(
                color = RapidoGrey,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp, start = 32.dp, end = 32.dp)
        )
    }
}
