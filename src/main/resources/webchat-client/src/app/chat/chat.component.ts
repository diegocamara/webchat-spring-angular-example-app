import { Component, OnInit } from "@angular/core";
import { RxStompService } from "@stomp/ng2-stompjs";
import { MatDialog } from "@angular/material/dialog";
import { Message } from "@stomp/stompjs";
import { UsernameDialogComponent } from "./username-dialog/username-dialog.component";
import { FormBuilder, FormGroup } from "@angular/forms";

@Component({
  selector: "chat-component",
  templateUrl: "./chat.component.html",
  styleUrls: ["./chat.component.css"]
})
export class ChatComponent implements OnInit {
  sender: string;
  chatUsers: any[];
  chatMessages: any[] = [];
  messageForm: FormGroup;

  constructor(
    private rxStompService: RxStompService,
    private dialog: MatDialog,
    private formBuilder: FormBuilder
  ) {
    this.messageForm = formBuilder.group({
      message: ""
    });
  }

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
        .watch("/topic/messages")
        .subscribe((message: Message) => {
          let response = JSON.parse(message.body);
          if (response.type != "ERROR") {
            if (response.type == "NEW_USER") {
              this.chatUsers = response.chatUsers;
            }
            this.chatMessages.push(response);
          }
        });
    });
  }

  onSubmit(messageFormData) {
    this.rxStompService.publish({
      destination: "/app/chat/messages",
      body: JSON.stringify({
        sender: this.sender,
        text: messageFormData.message
      })
    });
    this.messageForm.reset();
  }
}
