import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  constructor(private http: HttpClient) { }

  userDetailApi: string = 'http://localhost:8082/Rp/auth/userTable?groupid=0'

  bookingDetailsApi: string = 'http://localhost:8082/Rp/auth/showBookingTable'

  getUserDetail(): Observable<any>{
    return this.http.get(this.userDetailApi);
  }

  getBookingDetail(): Observable<any>{
    return this.http.get(this.bookingDetailsApi);
  }
}
