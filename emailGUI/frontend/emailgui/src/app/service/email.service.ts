import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmailService {
  // url jispe hamko API call karna hai
  private baseUrl:String = "http://localhost:8080"
  
  constructor(private httpClient:HttpClient) { }

  sendEmail(data:any){
    // basically observable yaha se return kar dega
    // ye request ko send kar dega or send karne ke baad promise return kar dega to hame usse subscribe karna padega
    return this.httpClient.post(`${this.baseUrl}/sendEmail`,data)
  }
}
