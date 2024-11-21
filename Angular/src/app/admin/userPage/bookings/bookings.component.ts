import { CommonModule } from '@angular/common';
import { Component, HostListener, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-bookings',
  standalone: true,
  imports: [RouterModule, CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './bookings.component.html',
  styleUrl: './bookings.component.css'
})
export class BookingsComponent implements OnInit{

  indoor: boolean = false;
  indoorType: boolean = false;
  outdoor: boolean = false;
  private: boolean = false;
  events: boolean = false;
  eventsType: boolean = false;
  birthday: boolean = false;
  anniversary: boolean = false;
  business: boolean = false;
  reunion: boolean = false;
  lunch: boolean = false;
  mealTime: boolean = true;
  dinner: boolean = false;
  selectedMealTime: string = '';

  lunchSlots: any[] = [];
  lunchIndoor: any;
  lunchOutdoor:any;
  lunchPrivate:any;

  DinnerSlots: any[] = [];
  dinnerIndoor: any;
  dinnerOutdoor: any;
  dinnerPrivate:any;

  constructor(private service: UserServiceService) { }


  ngOnInit(): void {

  }

 

  bookingForm = new FormGroup({
    username: new FormControl(''),
    email: new FormControl(''),
    phone: new FormControl(''),
    date: new FormControl(''),
    cuisines: new FormControl(''),
    noOfGuests: new FormControl(''),
    mealTime: new FormControl(''),
    tableType: new FormControl(''),
    indoorType: new FormControl(''),
    eventsType: new FormControl(''),
  })

  onItemClick(event: Event): void {
    const selectedValue = (event.target as HTMLSelectElement).value;

    if (selectedValue == 'dinner') {
      this.showDinner();
      this.dinner =true;
      this.lunch = false;
      this.indoor = true;
      this.indoorType = true;
      this.outdoor = true;
      this.private = true;
      this.events = false;
      this.eventsType = false;
    }

    if (selectedValue == "lunch") {
      this.showLunch();
      this.lunch = true;
      this.dinner =false;
      this.indoor = true;
      this.indoorType = true;
      this.outdoor = true;
      this.private = true;
      this.events = false;
      this.eventsType = false;
    }

    if (selectedValue == "indoor") {
      this.indoor = true;
      this.indoorType = true;
      this.outdoor = false;
      this.private = false;
      this.events = false;
      this.eventsType = false;
      this.mealTime = true;
    }

    if (selectedValue == "outdoor") {
      this.indoor = false;
      this.indoorType = false;
      this.outdoor = true;
      this.private = false;
      this.events = false;
      this.eventsType = false;
      this.mealTime = true;
    }

    if (selectedValue == "private") {
      this.indoor = false;
      this.indoorType = false;
      this.events = false;
      this.eventsType = false;
      this.outdoor = false;
      this.private = true;
      this.mealTime = true;
    }

    if (selectedValue == "events") {
      this.indoor = false;
      this.indoorType = false;
      this.events = true;
      this.eventsType = true;
      this.outdoor = false;
      this.private = false;
      this.mealTime = false;
    }

    if (selectedValue == "birthday") {
      this.birthday = true;
      this.anniversary = false;
      this.business = false;
      this.reunion = false;
    }

    if (selectedValue == "anniversary") {
      this.birthday = false;
      this.anniversary = true;
      this.business = false;
      this.reunion = false;
    }

    if (selectedValue == "business") {
      this.birthday = false;
      this.anniversary = false;
      this.business = true;
      this.reunion = false;
    }

    if (selectedValue == "reunion") {
      this.birthday = false;
      this.anniversary = false;
      this.business = false;
      this.reunion = true;
    }
  }


  showLunch(){
    this.service.showLunchSlots().subscribe(res =>{
      this.lunchSlots = Array.isArray(res) ? res : Object.values(res);
      // this.DinnerSlots = res;
      console.log( this.lunchSlots);


      this.lunchIndoor = this.lunchSlots[0];
       this.lunchOutdoor = this.lunchSlots[1];
       this.lunchPrivate = this.lunchSlots[2].filter((time: any) => time.trim() !== "");;
      
      // console.log(JSON.stringify(this.lunchSlots));
      
    })
  }

  showDinner(){
    this.service.showDinnerSlots().subscribe(res =>{
      this.DinnerSlots = Array.isArray(res) ? res : Object.values(res);
      // this.DinnerSlots = res;
      console.log( this.DinnerSlots);


      this.dinnerIndoor = this.DinnerSlots[0];
       this.dinnerOutdoor = this.DinnerSlots[1];
       this.dinnerPrivate = this.DinnerSlots[2].filter((time: any) => time.trim() !== "");;
      
      // console.log(JSON.stringify(this.DinnerSlots));
      
    })
  }

  selectedTime: string = ''; 
  isSelected: boolean = false;
  bookingData: any[] = [];

  selectTime(time: string, event: MouseEvent): void{
    event.stopPropagation(); 
    this.selectedTime = this.selectedTime == time ? '' : time;
    console.log('selected time: ' , this.selectedTime);
    
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent): void {
    // Check if the click is outside the container of time slots
    const isClickInside = event.target instanceof HTMLElement && event.target.closest('.selected');
    if (!isClickInside) {
      this.selectedTime = '';  // Deselect if click is outside any time-slot
    }
  }


  submitSelection(){
    alert("working");
    if(this.bookingForm.valid){
      const data = {
        "name": this.bookingForm.value.username,
        "email": this.bookingForm.value.email,
        "phone_number": this.bookingForm.value.phone,
        "bookingDate": this.bookingForm.value.date,
        "bookingTime": this.selectedTime,
        "cuisine_type": this.bookingForm.value.cuisines,
        "NumberofGuest": this.bookingForm.value.noOfGuests,
        "foodTiming": this.bookingForm.value.mealTime,
        "tableType": this.bookingForm.value.tableType,
        "indoor": this.bookingForm.value.indoorType, 
        "event": this.bookingForm.value.eventsType
      }

      console.log(data);

      this.service.insertBooking(data).subscribe(res =>{
        this.bookingData = Object(res);
        alert("submit successfull");
        this.bookingForm.reset();
        console.log(this.bookingData);
        
      })
    }
  }

}
