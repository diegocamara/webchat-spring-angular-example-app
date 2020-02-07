import { InjectableRxStompConfig } from "@stomp/ng2-stompjs";

export const rxStompConfig: InjectableRxStompConfig = {
  brokerURL: "ws://localhost:8080/chat",
  debug: message => {
    console.log(message);
  }
};
