import { Component } from '@angular/core';
import { User } from '../dataType/user';
import { UserSignupService } from '../services/user-signup.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {


  usersList!:User[];

  constructor(private userSignupService: UserSignupService){}

  ngOnInit(){
    this.userSignupService.findAllUsers().subscribe(res=>{
      this.usersList = res;
    })
  }
  delete(id: number) {
    this.userSignupService.deleteUser(id).subscribe();
  }

}
