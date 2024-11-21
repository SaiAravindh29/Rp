import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {

  selectedItem: string = '';
  starters: boolean = false;
  indian: boolean = false;
  continental: boolean = false;
  mexican: boolean = false;
  desserts: boolean = false;
  Popular: boolean = true;

  // Set selectedItem based on which button was clicked
  onItemClick(item: string) {
    // this.selectedItem = '';
    // this.selectedItem = item;
    
    if(item == "starters"){
      this.starters = true;
      this.indian = false;
      this.continental = false;
      this.mexican = false;
      this.desserts = false;
      this.Popular = false;
    }  
    if(item == "indian"){
      this.starters = false;
      this.indian = true;
      this.continental = false;
      this.mexican = false;
      this.desserts = false;
      this.Popular = false;
    }

    if(item == "continental"){
      this.starters = false;
      this.indian = false;
      this.continental = true;
      this.mexican = false;
      this.desserts = false;
      this.Popular = false;
    }

    if(item == "mexican"){
      this.starters = false;
      this.indian = false;
      this.continental = false;
      this.mexican = true;
      this.desserts = false;
      this.Popular = false;
    }

    if(item == "desserts"){
      this.starters = false;
      this.indian = false;
      this.continental = false;
      this.mexican = false;
      this.desserts = true;
      this.Popular = false;
    }


  }

}
