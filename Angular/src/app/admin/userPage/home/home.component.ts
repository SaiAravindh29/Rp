import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { LoginServiceService } from '../../../auth/login-service.service';
import { AdminServiceService } from '../../adminPage/admin-service.service';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{

  isLoggedIn: boolean = false;
  getUser: any;
  username: any = '';
  user:any;
  
  constructor(private service: AdminServiceService){}
 
 
  ngOnInit(): void {

   this.user=  sessionStorage.getItem('Username');

    this.getUserDetails();
  }


  getUserDetails() {
    this.service.getUserDetail().subscribe(res => {
      // this.getUser = res;
      this.username = res;

      
      //const filteredBooks = this.username.filter((val: {username: any}) => val.username.includes(this.user));
      
      //alert(filteredBooks);
      
    })
  }

}
