import { Routes } from '@angular/router';
import { HomeComponent } from './admin/userPage/home/home.component';
import { MenuComponent } from './admin/userPage/menu/menu.component';
import { BookingsComponent } from './admin/userPage/bookings/bookings.component';
import { ContactComponent } from './admin/userPage/contact/contact.component';
import { LoginComponent } from './auth/login/login.component';
import { NavbarComponent } from './admin/navbar/navbar.component';
import { ServiceComponent } from './admin/userPage/service/service.component';
import { TeamsComponent } from './admin/userPage/teams/teams.component';
import { ReviewsComponent } from './admin/userPage/reviews/reviews.component';
import { AdminProfileComponent } from './admin/adminPage/admin-profile/admin-profile.component';
import { UserProfileComponent } from './admin/userPage/user-profile/user-profile.component';
import { AboutComponent } from './admin/userPage/about/about.component';
import { UserDeatilsComponent } from './admin/adminPage/user-deatils/user-deatils.component';
import { BookingDetailsComponent } from './admin/adminPage/booking-details/booking-details.component';
import { AdminHomeComponent } from './admin/adminPage/admin-home/admin-home.component';

export const routes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    {path: 'login', component: LoginComponent},
    {path: 'home',component: HomeComponent},
    {path: 'about', component: AboutComponent},
    {path: 'service', component: ServiceComponent},
    {path: 'menu', component: MenuComponent},
    {path: 'booking', component: BookingsComponent},
    {path: 'contact', component: ContactComponent},
    {path: 'teams', component: TeamsComponent},
    {path: 'review', component: ReviewsComponent},
    {path: 'navbar', component: NavbarComponent},
    {path: 'userProfile', component: UserProfileComponent},
    // Admin Routes
    {path: 'adminProfile', component: AdminProfileComponent},
    {path: 'admin', component: AdminHomeComponent},
    {path: 'userDetails', component: UserDeatilsComponent},
    {path: 'bookingDetails', component: BookingDetailsComponent},
];
