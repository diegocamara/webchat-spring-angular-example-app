import { NgModule } from "@angular/core";
import { Route } from "@angular/compiler/src/core";
import { Routes, RouterModule } from "@angular/router";
import { ChatComponent } from "./chat.component";

const routes: Routes = [
  {
    path: '',
    component: ChatComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChatRoutingModule {}
