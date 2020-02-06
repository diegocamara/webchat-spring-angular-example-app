import { Component, OnInit, Input } from "@angular/core";

@Component({
  selector: "chat-users",
  templateUrl: "./chat-users.component.html",
  styleUrls: ["./chat-users.component.css"]
})
export class ChatUsersComponent implements OnInit {
  @Input()
  users: any[];

  ngOnInit() {}
}
