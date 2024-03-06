import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserSignupService } from '../services/user-signup.service';
import { UserDto } from '../dataType/user-dto';
import { User } from '../dataType/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrl: './user-signup.component.css'
})
export class UserSignupComponent {

  myform!: FormGroup;
  firstName!: FormControl;
  lastName!: FormControl;
  email!: FormControl;
  password!: FormControl;
  

  constructor(private userSignup:UserSignupService,
              private router:Router){}

  ngOnInit(){
    this.createFormControls();
    this.createForm();
  }

  createFormControls() {
    this.firstName = new FormControl('', Validators.required);
    this.lastName = new FormControl('', Validators.required);
    this.email = new FormControl('', [
      Validators.required,
      Validators.pattern("[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{2,}[.]{1}[a-zA-Z]{2,}")
    ]);
    this.password = new FormControl('', [
      Validators.required,
      Validators.minLength(8)
    ]);
    
  }
  createForm() {
    this.myform = new FormGroup({
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      password: this.password,
      
    });
}
onSubmit() {
  if(this.myform.valid){   
    this.userSignup.addUser(this.myform.value).subscribe((res:UserDto) =>{
      alert(res.firstName+" "+res.lastName+" registerd successfully");
    this.router.navigateByUrl("/");     
    });
   
  }
}
onCancel() {
  this.myform.reset();
}
navigateToHome() {
  this.router.navigateByUrl("/");
  }
}
