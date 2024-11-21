import { isPlatformBrowser } from '@angular/common';
import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{

// constructor(){
//   //nav-bar show script
//       const navMenu = document.getElementById('nav-menu') as HTMLElement;
//       const toggleMenu = document.getElementById('nav-toggle') as HTMLElement;
//       const closeMenu = document.getElementById('nav-close') as HTMLElement
    
//     // SHOW
//     toggleMenu.addEventListener('click', () => {
//       navMenu.classList.toggle('show');
//   });
// }

constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

ngOnInit() {
  if (isPlatformBrowser(this.platformId)) {
    // Access the DOM only when in the browser
    const navMenu = document.getElementById('nav-menu') as HTMLElement | null;
    const toggleMenu = document.getElementById('nav-toggle') as HTMLElement | null;
    const closeMenu = document.getElementById('nav-close') as HTMLElement | null;

    // Add event listeners for the menu toggle (Open and Close)
    if (toggleMenu && navMenu) {
      toggleMenu.addEventListener('click', () => {
        navMenu.classList.toggle('show');
      });
    }

    if (closeMenu && navMenu) {
      closeMenu.addEventListener('click', () => {
        navMenu.classList.remove('show');
      });
    }

    // Active menu link functionality
    const navLinks = document.querySelectorAll('.nav-link') as NodeListOf<HTMLElement>;

    // Function to remove 'active' class from all links and add to the clicked one
    const linkAction = (event: Event) => {
      const clickedLink = event.target as HTMLElement;

      // Remove 'active' class from all links
      navLinks.forEach((link) => link.classList.remove('active'));

      // Add 'active' class to the clicked link
      if (clickedLink) {
        clickedLink.classList.add('active');
      }

      // Close the menu on mobile after selecting a link (optional)
      if (navMenu) {
        navMenu.classList.remove('show');
      }
    };

    // Add click event to each nav-link
    navLinks.forEach((link) => {
      link.addEventListener('click', linkAction);
    });
  }
}
}


