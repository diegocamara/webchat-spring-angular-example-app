import { Component, OnInit } from "@angular/core";
import { RxStompService } from "@stomp/ng2-stompjs";
import { MatDialog } from "@angular/material/dialog";
import { Message } from "@stomp/stompjs";
import { UsernameDialogComponent } from "./username-dialog/username-dialog.component";

@Component({
  selector: "chat-component",
  templateUrl: "./chat.component.html",
  styleUrls: ["./chat.component.css"]
})
export class ChatComponent implements OnInit {
  sender: string;
  chatUsers: any[];
  chatMessages: any[] = [];

  constructor(
    private rxStompService: RxStompService,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    const usernameDialog = this.dialog.open(UsernameDialogComponent, {
      width: "50vh",
      height: "40vh",
      disableClose: true
    });

    usernameDialog.afterClosed().subscribe(result => {
      this.sender = result.sender;
      this.chatUsers = result.chatUsers;
      this.rxStompService
        .watch("/chat/messages")
        .subscribe((message: Message) => {
          let response = JSON.parse(message.body);
          console.log(response.type);
          switch (response.type) {
            case "NEW_USER": {
              this.chatUsers = response.chatUsers;              
              break;
            }
            case "TEXT": {
              break;
            }
            case "ERROR": {
              break;
            }
            case "USER_LEAVE": {
              break;
            }
          }

          this.chatMessages.push(response);

          
        });
    });
  }
}
