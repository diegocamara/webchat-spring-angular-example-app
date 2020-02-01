import { NgModule } from '@angular/core';
import { MatSliderModule } from '@angular/material/slider';
import { MatInputModule } from '@angular/material/input'
import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';

@NgModule({
    imports: [HomeRoutingModule, MatSliderModule, MatInputModule],
    declarations: [HomeComponent],
    providers: []
})
export class HomeModule { }