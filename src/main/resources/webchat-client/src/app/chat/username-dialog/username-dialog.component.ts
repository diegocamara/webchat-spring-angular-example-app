import { Component } from "@angular/core";
import { MatDialogRef } from "@angular/material/dialog";
import { RxStompService } from "@stomp/ng2-stompjs";
import { Message } from "@stomp/stompjs";
import { MatSnackBar } from "@angular/material/snack-bar";

@Component({
  selector: "username-dialog",
  templateUrl: "./username-dialog.component.html"
})
export class UsernameDialogComponent {
  username: string;

  constructor(
    private dialogRef: MatDialogRef<UsernameDialogComponent>,
    private rxStompService: RxStompService,
    private _snackBar: MatSnackBar
  ) {}

  confirm() {
    const responseMessageSubscription = this.rxStompService
      .watch(`/chat/messages/${this.username}`)
      .subscribe((message: Message) => {
        let response = JSON.parse(message.body);
        if (response.error != undefined) {
          this._snackBar.open(response.error, "", {
            duration: 2000
          });
        } else {
          responseMessageSubscription.unsubscribe();
          this.dialogRef.close(response);
        }
      });
    this.rxStompService.publish({
      destination: `/app/chat/user/${this.username}`
    });
  }
}
