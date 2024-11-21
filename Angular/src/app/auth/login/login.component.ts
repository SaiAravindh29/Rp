import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginServiceService } from '../login-service.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'
import { CommonModule } from '@angular/common';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  signupData: any[] = []
  loginData: any[] = []

  constructor(private service: LoginServiceService, private router: Router) { }

  signupForm = new FormGroup({
    userName: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    confirmPassword: new FormControl('', Validators.required),
  })

  loginForm = new FormGroup({
    userInput: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  })

  signupSubmit() {
    if (this.signupForm.invalid) {
      Swal.fire({
        icon: "error",
        title: "Signup Failed",
        text: "Please fill in all the required fields.",
        confirmButtonText: "Retry",
      });
    } else {
      const data = {
        "username": this.signupForm.value.userName,
        "email": this.signupForm.value.email,
        "password": this.signupForm.value.password,
        "confirmPassword": this.signupForm.value.confirmPassword
      }

      this.service.signupData(data).subscribe((res: any) => {
        this.signupData = Object(res);
        console.log(JSON.stringify(data));

        if (res.message === 'Username already exists') {
          Swal.fire({
            icon: "error",
            title: "Username Taken",
            text: `The username you entered is already taken. Please choose another one.`,
          });
        } else if (res.message === 'Email already exists') {
          Swal.fire({
            icon: "error",
            title: "Email Taken",
            text: `The email address is already registered. Please use a different email.`,
          });
        } else if (res.message === 'Password and confirm password does not match') {
          Swal.fire({
            icon: 'error',
            title: 'Password Mismatch',
            text: 'Your passwords do not match. Please ensure both passwords are the same.',
          });
        } else if (res.success) {
          // If the signup was successful, show a success message
          Swal.fire({
            icon: "success",
            title: "Account Created",
            text: `Your account has been successfully created!`,
            confirmButtonText: "Go To Login",
          }).then((result: any) => {
            if (result.isConfirmed) {
              this.signupForm.reset();

              // this.switchtoLogin();
            }
          })
        }
      })
    }
  }

  loginSubmit() {
    // alert(this.loginForm.value.userInput)
    if (this.loginForm.invalid) {
      Swal.fire({
        icon: "error",
        title: "Login Failed",
        text: "Invalid username or password. Please try again.",
        confirmButtonText: "Try Again",
      });
    } else {
      const data = {
        "username": this.loginForm.value.userInput,
        "password": this.loginForm.value.password,
      }

      this.service.loginData(data).subscribe((res: any) => {
        this.loginData = Object(res);
        console.log(JSON.stringify(data));
        console.log(res.groupId);


        if (res.message === 'Invalid username or email') {
          Swal.fire({
            icon: 'error',
            title: 'Invalid Username or Email',
            text: 'The username or email you entered is incorrect. Please check and try again.',
          });
        } else if (res.message === 'Invalid password') {
          Swal.fire({
            icon: 'error',
            title: 'Invalid Password',
            text: 'The password you entered is incorrect. Please try again.',
          });
        } else {
          Swal.fire({
            icon: 'success',
            title: 'Login Successful!',
            text: 'You have successfully logged in. Welcome back!',
            confirmButtonText: "Explore Now!",
          }).then(() => {
            // Redirect based on groupId
            if (res.groupId === 1) {
              // alert(this.loginForm.value.userInput)
              sessionStorage.setItem('Username',String(this.loginForm.value.userInput));

              this.router.navigate(['/home']); //Redirect to user page
            }
            else if (res.groupId === 0) {
              sessionStorage.setItem('Username',String(this.loginForm.value.userInput));
              this.router.navigate(['/admin']); //Redirect to admin page
            }

          });
        }
      })
    }

  }

}
