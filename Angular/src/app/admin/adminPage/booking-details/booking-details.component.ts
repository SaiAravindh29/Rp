import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AdminServiceService } from '../admin-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-booking-details',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './booking-details.component.html',
  styleUrl: './booking-details.component.css'
})
export class BookingDetailsComponent implements OnInit{
  
  getBooking: any;

  constructor(private service: AdminServiceService){}
  
  
  ngOnInit(): void {
    this.getBookingDetails();
  }


  getBookingDetails() {
    this.service.getBookingDetail().subscribe(res => {
      this.getBooking = res;

      console.log(this.getBooking);

    })
  }

}
