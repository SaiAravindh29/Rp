import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AdminServiceService } from '../admin-service.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-deatils',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './user-deatils.component.html',
  styleUrl: './user-deatils.component.css'
})
export class UserDeatilsComponent implements OnInit {

  getUser: any;

  constructor(private service: AdminServiceService) { }

  ngOnInit(): void {
    this.getUserDetails();
  }

  getUserDetails() {
    this.service.getUserDetail().subscribe(res => {
      this.getUser = res;

      console.log(res)

    })
  }

}
