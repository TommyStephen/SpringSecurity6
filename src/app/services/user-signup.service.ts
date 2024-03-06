import { Injectable } from '@angular/core';
import { UserDto } from '../dataType/user-dto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../dataType/user';
import { LoginDto } from '../dataType/login-dto';

@Injectable({
  providedIn: 'root'
})
export class UserSignupService {
  

  constructor(private httpClient:HttpClient) { }

  addUser(userDto: UserDto):Observable<any> {
    return this.httpClient.post("http://localhost:8080/angular/signup", userDto);
  }
  findAllUsers(){
    return this.httpClient.get<User[]>("http://localhost:8080/angular/findAllUsers")
  }
  login(loginDto:LoginDto){
    return this.httpClient.post("http://localhost:8080/angular/login", loginDto);
  }
  deleteUser(id:number){
    return this.httpClient.delete(`http://localhost:8080/angular/deleteUser?id=${id}}`)
  }

}
