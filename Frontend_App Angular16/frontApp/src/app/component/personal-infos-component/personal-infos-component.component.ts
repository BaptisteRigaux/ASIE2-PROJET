import {Component} from '@angular/core';
import {MatExpansionModule} from '@angular/material/expansion';


@Component({
  selector: 'app-personal-infos-component',
  templateUrl: './personal-infos-component.component.html',
  styleUrls: ['./personal-infos-component.component.css']
})
export class PersonalInfosComponentComponent {
  panelOpenState = false;
}
