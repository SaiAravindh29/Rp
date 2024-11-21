import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  constructor(private http:HttpClient) { }

  loginApi: string = 'http://localhost:8082/Rp/auth/login'

  signupApi: string = 'http://localhost:8082/Rp/auth/signup'

  
  signupData(data:any){
    return this.http.post(this.signupApi,data)
  }
  
  loginData(data:any){
    return this.http.post(this.loginApi, data)
  }

}
