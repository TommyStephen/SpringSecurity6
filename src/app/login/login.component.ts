import { Component, booleanAttribute } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserSignupService } from '../services/user-signup.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {


  myform!: FormGroup;
  email!: FormControl;
  password!: FormControl;
  

  constructor(private router:Router,
    private userSignupService:UserSignupService){}

  ngOnInit(){
    this.createFormControls();
    this.createForm();
  }

  createFormControls() {
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
      email: this.email,
      password: this.password,
      
    });
}
onSubmit() {
  if(this.myform.valid){   
    this.userSignupService.login(this.myform.value).subscribe(res=>{
      if(res==true){
        alert("User Authenticated");
        this.router.navigateByUrl("/"); 
      }
      else{
        alert("Invalid Credentials")
      }
    })
        
    
   
  }
}
onCancel() {
  this.myform.reset();
}
navigateToHome() {
  this.router.navigateByUrl("/");
  }

}
