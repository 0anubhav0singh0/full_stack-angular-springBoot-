import { MatSnackBar } from '@angular/material/snack-bar';
import { EmailService } from './../../service/email.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.css'],
  providers:[EmailService]
})
export class EmailComponent implements OnInit {
  flag:boolean = false;

  data={
    to:"",
    subject:"",
    message:""
  }
  constructor(private emailService:EmailService, private snak:MatSnackBar) { }

  ngOnInit(): void {
  }
// service banayenge jo call karegi hamari backend ko 
  doSubmitForm(){
    // console.log("submitted")
    if(this.data.to==''||this.data.subject==''||this.data.message==''){
      this.snak.open("Fields cannot be empty !!","OK")
      return;
    }
    // subscribe ke andar do function pass karne hai, ek success ke liye or ek error ke liye
    this.flag = true
    this.emailService.sendEmail(this.data).subscribe(
      response=>{
        console.log(response);
        this.flag = false
        this.snak.open("Send Successfully", "OK")
      },
      error=>{
        console.log(error);
        this.flag = false
        this.snak.open("ERROR!!", "OK")
      })
  }

}
