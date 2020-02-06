import { NgModule } from "@angular/core";
import { MatGridListModule } from "@angular/material/grid-list";
import { MatDialogModule } from "@angular/material/dialog";
import { MatInputModule } from "@angular/material/input";
import { MatButtonModule } from "@angular/material/button";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import {
  InjectableRxStompConfig,
  RxStompService,
  rxStompServiceFactory
} from "@stomp/ng2-stompjs";
import { rxStompConfig } from "./config/rx-stomp.config";
import { ChatRoutingModule } from "./chat-routing.module";
import { ChatComponent } from "./chat.component";
import { ChatUsersComponent } from "./chat-users/chat-users.component";
import { ChatMessagesComponent } from "./chat-messages/chat-messages.component";
import { UsernameDialogComponent } from "./username-dialog/username-dialog.component";
import { FormsModule } from "@angular/forms";
import { CommonModule } from "@angular/common";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ChatRoutingModule,
    MatGridListModule,
    MatDialogModule,
    MatInputModule,
    MatButtonModule,
    MatSnackBarModule
  ],
  declarations: [
    ChatComponent,
    ChatUsersComponent,
    ChatMessagesComponent,
    UsernameDialogComponent
  ],
  providers: [
    {
      provide: InjectableRxStompConfig,
      useValue: rxStompConfig
    },
    {
      provide: RxStompService,
      useFactory: rxStompServiceFactory,
      deps: [InjectableRxStompConfig]
    }
  ],
  entryComponents: [UsernameDialogComponent]
})
export class ChatModule {}
