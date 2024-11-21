import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http : HttpClient) { }

  // userapi: string = 'http://localhost:8082/Rp/auth';

  lunchSlots: string = 'http://localhost:8082/Rp/auth/showLunchSlots';

  dinnerSlots: string = 'http://localhost:8082/Rp/auth/showDinnerSlots'

  bookingApi: string = 'http://localhost:8082/Rp/auth/booking'

  showLunchSlots(){
    return this.http.get<any>(this.lunchSlots);
  }
  
  showDinnerSlots(){
    return this.http.get<any>(this.dinnerSlots);
  }

  insertBooking(data: any){
    return this.http.post(this.bookingApi, data);
  }
}
